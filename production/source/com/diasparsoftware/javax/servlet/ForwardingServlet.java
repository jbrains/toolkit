package com.diasparsoftware.javax.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

/***
 * A servlet that forwards to the location you specify. Use
 * this in conjunction with ServletUnit (http://httpunit.sourceforge.net)
 * to test page templates, such as JSPs.
 * 
 * Sample use.
 * 
 * <pre>
 * servletRunner = new ServletRunner(
 *     getWebContentPath("/WEB-INF/web.xml"),
 * 
 * servletRunner.registerServlet("/forward",
 *     ForwardingServlet.class.getName());
 * 
 * client = servletRunner.newClient();
 * 
 * ForwardingServlet servlet =
 *     (ForwardingServlet) invocationContext.getServlet();
 * 
 * servlet.setForwardUri("/shopcart.jsp");
 * 
 * HttpServletRequest request = invocationContext.getRequest();
 * request.setAttribute("shopcartDisplay", shopcartBean);
 * 
 * servlet.service(
 *     invocationContext.getRequest(),
 *     invocationContext.getResponse());
 * 
 * WebResponse response =
 *     invocationContext.getServletResponse();
 * 
 * assertEquals("....", response.getText());
 * </pre>
 * 
 * @version $Revision: 1.1 $
 * @author <a href="mailto:jbr@diasparsoftware.com">J. B. Rainsberger</a> 
 */
public class ForwardingServlet extends HttpServlet {
    private String forwardUri = "";

    protected void doGet(
        HttpServletRequest request,
        HttpServletResponse response)
        throws ServletException, IOException {

        handleRequest(request, response);
    }

    protected void doPost(
        HttpServletRequest request,
        HttpServletResponse response)
        throws ServletException, IOException {

        handleRequest(request, response);
    }

    protected void handleRequest(
        HttpServletRequest request,
        HttpServletResponse response)
        throws ServletException, IOException {

        getServletContext().getRequestDispatcher(
            getForwardUri()).forward(
            request,
            response);
    }

    /***
     * The URI to which this servlet ought to forward when
     * you invoke <code>service()</code>.
     * 
     * @param forwardUri
     */
    public void setForwardUri(String forwardUri) {
        this.forwardUri = forwardUri;
    }

    public String getForwardUri() {
        return forwardUri;
    }
}
