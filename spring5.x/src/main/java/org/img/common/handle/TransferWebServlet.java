package org.img.common.handle;

import org.iiidev.utils.JsonUtil;
import org.img.common.resp.ServiceResponse;
import org.img.service.ITransferService;
import org.img.service.impl.TransferServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * TransferWebServlet
 *
 * @author IIIDelay
 * @createTime 2023年03月04日 17:00:00
 */
@WebServlet(urlPatterns = "/transferServlet")
public class TransferWebServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ITransferService transferService = new TransferServiceImpl();

        // 设置请求体的字符编码
        String fromCardNo = req.getParameter("fromCardNo");
        String toCardNo = req.getParameter("toCardNo");
        String moneyStr = req.getParameter("money");
        int money = Integer.parseInt(moneyStr);
        ServiceResponse<Void> result = new ServiceResponse();
        try {
            // 2. 调⽤service层⽅法
            transferService.transfer(fromCardNo, toCardNo, money);
            result.setCode(200);
        } catch (Exception e) {
            e.printStackTrace();
            result.setCode(201);
            result.setMsg(e.toString());
        }
        // 响应
        resp.setContentType("application/json;charset=utf-8");
        resp.getWriter().print(JsonUtil.objToStr(result));
    }
}
