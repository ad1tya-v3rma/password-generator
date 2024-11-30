package com.password_generator.api.filters;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.*;

//@Component
@Order(1)
public class RequestLoggingFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // Log the request URL
        String requestURI = httpRequest.getRequestURI();
        String method = httpRequest.getMethod();
        System.out.println("Incoming request: " + method + " " + requestURI);

        // Log headers
        System.out.println("Request Headers:");
        httpRequest.getHeaderNames().asIterator().forEachRemaining(headerName -> {
            System.out.println(headerName + ": " + httpRequest.getHeader(headerName));
        });

        // Log request body (only for POST, PUT, PATCH requests as they have bodies)
        if ("POST".equalsIgnoreCase(method) || "PUT".equalsIgnoreCase(method) || "PATCH".equalsIgnoreCase(method)) {
            String body = getRequestBody(httpRequest);
            System.out.println("Request Body: " + body);
        }
        ResponseWrapper responseWrapper = new ResponseWrapper(httpResponse);

        // Continue with the filter chain, allowing other filters and the controller to process the request
        chain.doFilter(request, responseWrapper);

        // Log the response body (after the request has been processed)
        String responseBody = responseWrapper.getResponseBody();
        System.out.println("Response Body: " + responseBody);

        // Log the response status code
        System.out.println("Response Status: " + httpResponse.getStatus());
    }

    private String getRequestBody(HttpServletRequest request) throws IOException {
        StringBuilder requestBody = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            requestBody.append(line);
        }
        return requestBody.toString();
    }

    private static class ResponseWrapper extends HttpServletResponseWrapper {
        private StringWriter stringWriter = new StringWriter();
        private PrintWriter printWriter = new PrintWriter(stringWriter);

        public ResponseWrapper(HttpServletResponse response) {
            super(response);
        }

        @Override
        public PrintWriter getWriter() {
            return printWriter;
        }

        public String getResponseBody() {
            printWriter.flush();
            return stringWriter.toString();
        }
    }
}
