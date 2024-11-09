package com.password_generator.api.filters;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;


import java.io.IOException;
import java.util.regex.Pattern;

@Component
public class SqlInjectionFilter implements Filter {

    private static final Pattern SQL_INJECTION_PATTERN = Pattern.compile(".*(\\b(select|insert|update|delete|drop|union|;|--|#|\\s+or\\s+|\\s+and\\s+)\\b).*", Pattern.CASE_INSENSITIVE);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // Validate request parameters
        if (isSqlInjectionPresent(httpRequest)) {
            httpResponse.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid request parameters");
            return;
        }

        // Proceed with the next filter or the requested resource
        chain.doFilter(request, response);
    }

    private boolean isSqlInjectionPresent(HttpServletRequest request) {
        // Check request parameters
        for (String param : request.getParameterMap().keySet()) {
            String value = request.getParameter(param);
            if (SQL_INJECTION_PATTERN.matcher(value).matches()) {
                return true;
            }
        }
        return false;
    }
}
