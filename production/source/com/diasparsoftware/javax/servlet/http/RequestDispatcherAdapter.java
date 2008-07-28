package com.diasparsoftware.javax.servlet.http;

import java.io.IOException;

import javax.servlet.*;

public class RequestDispatcherAdapter implements RequestDispatcher {
    public void forward(
        ServletRequest request,
        ServletResponse response)
        throws ServletException, IOException {
    }

    public void include(
        ServletRequest request,
        ServletResponse response)
        throws ServletException, IOException {
    }
}
