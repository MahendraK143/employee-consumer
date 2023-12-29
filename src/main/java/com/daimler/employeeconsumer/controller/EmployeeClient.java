package com.daimler.employeeconsumer.controller;

import com.daimler.employeeconsumer.model.Employee;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "employee-producer")
public interface EmployeeClient {

    @RequestMapping(method= RequestMethod.GET, value="/employee")
    Employee getEmployee();
}
