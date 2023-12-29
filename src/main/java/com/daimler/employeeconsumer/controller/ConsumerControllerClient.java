package com.daimler.employeeconsumer.controller;

import com.daimler.employeeconsumer.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;
import java.util.Random;

@RestController
public class ConsumerControllerClient {

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    EmployeeClient employeeClient;

    @RequestMapping(value = "/employee", method = RequestMethod.GET)
    public ResponseEntity<String> getEmployee() throws RestClientException, IOException {
        List<ServiceInstance> instances = discoveryClient.getInstances("employee-producer");
        ServiceInstance serviceInstance = instances.get(new Random().nextInt(instances.size()));

        String baseUrl = serviceInstance.getUri().toString();

        baseUrl = baseUrl + "/employee";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = null;
        try {
            response = restTemplate.exchange(baseUrl, HttpMethod.GET, getHeaders(), String.class);
        } catch (Exception ex) {
            System.out.println(ex);
        }
        System.out.println(response.getBody());
        return response;
    }

    @RequestMapping(value = "/emp", method = RequestMethod.GET)
    public ResponseEntity<Employee> getEmp() throws RestClientException, IOException {

        return new ResponseEntity<>(employeeClient.getEmployee(), HttpStatus.ACCEPTED);
    }

    private static HttpEntity<?> getHeaders() throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        return new HttpEntity<>(headers);
    }
}
