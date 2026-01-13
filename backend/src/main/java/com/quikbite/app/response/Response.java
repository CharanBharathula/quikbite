package com.quikbite.app.response;

import java.io.Serializable;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response<T> {
    private int statusCode; // 200 for success, 400 for client error, 500 for server error etc
    private String message; // A brief message about the response
    private T data;          // The actual payload being returned (if any)
    private Map<String, Serializable> meta;    // The actual data being returned (if any) 
}
