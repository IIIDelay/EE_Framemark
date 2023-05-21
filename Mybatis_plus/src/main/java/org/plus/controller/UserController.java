package org.plus.controller;


import org.apache.commons.io.IOUtils;
import org.plus.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * (User)表控制层
 *
 * @author makejava
 * @since 2023-05-16 22:35:44
 */
@RestController
@RequestMapping("user")
public class UserController {
    /**
     * 服务对象
     */
    @Autowired
    private UserService userService;

    @RequestMapping("down")
    public void test(HttpServletResponse response) throws IOException {
        down(response, "null_cell_test.xlsx");
    }

    private static void down(HttpServletResponse response, String name) throws IOException {
        ServletOutputStream outputStream = response.getOutputStream();
        response.setHeader("content-disposition","attachment;fileName="+ URLEncoder.encode(name,"UTF-8"));


        InputStream inputStream1 = Files.newInputStream(Paths.get("/template/null_cell_test.xlsx"));
        InputStream resourceAsStream = UserController.class.getResourceAsStream("/template/null_cell_test.xlsx");
        File file = ResourceUtils.getFile("template/null_cell_test.xlsx");
        ClassPathResource classPathResource = new ClassPathResource("template/null_cell_test.xlsx");
        InputStream inputStream = classPathResource.getInputStream();

        IOUtils.copy(inputStream,outputStream);
        IOUtils.closeQuietly(inputStream);
        IOUtils.closeQuietly(outputStream);
    }

}

