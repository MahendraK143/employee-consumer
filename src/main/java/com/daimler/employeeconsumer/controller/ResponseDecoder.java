package com.daimler.employeeconsumer.controller;

import feign.Response;
import feign.codec.ErrorDecoder;

public class ResponseDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String s, Response response) {
        return null;
    }
}