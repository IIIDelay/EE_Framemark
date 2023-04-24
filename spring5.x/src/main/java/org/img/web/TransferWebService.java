package org.img.web;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * TransferWebService
 *
 * @author IIIDelay
 * @createTime 2023年03月25日 13:43:00
 */
@WebServlet(name = "transfer", urlPatterns = "/")
public class TransferWebService extends HttpServlet {
    @Override
    public void init() throws ServletException {
        // servlet获取 springmvc 的bean
        WebApplicationContext webApplicationContext =
                WebApplicationContextUtils.getWebApplicationContext(getServletContext());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
