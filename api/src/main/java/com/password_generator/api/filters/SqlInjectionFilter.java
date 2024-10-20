package com.password_generator.api.filters;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class SqlInjectionFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // Get the request parameters
        String queryString = httpRequest.getQueryString();
        String requestBody = httpRequest.getReader().lines().reduce("", (accumulator, actual) -> accumulator + actual);

        // Check for SQL injection patterns
        if (isSqlInjectionAttempt(queryString) || isSqlInjectionAttempt(requestBody)) {
            httpResponse.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid input detected.");
            return; // Stop further processing
        }

        // Continue the filter chain
        chain.doFilter(request, response);
    }

    private boolean isSqlInjectionAttempt(String input) {
        if (input == null) {
            return false;
        }
        String lowerInput = input.toLowerCase();
        return lowerInput.contains("select") || lowerInput.contains("insert") ||
                lowerInput.contains("update") || lowerInput.contains("delete") ||
                lowerInput.contains("drop") || lowerInput.contains("--") ||
                lowerInput.contains(";") || lowerInput.contains("'");
    }

    @Override
    public void destroy() {
        // Cleanup code, if needed
    }
}