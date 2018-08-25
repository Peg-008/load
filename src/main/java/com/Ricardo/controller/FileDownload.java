package com.Ricardo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

@Controller
@RequestMapping("/file")
public class FileDownload {
    @RequestMapping("/downFile")
    public void downFile(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        response.setContentType("text/html;charset=utf-8");
        //获取需要下载的文件名
        String fileName = request.getParameter("filename");
        System.out.println("下载的文件名:" + fileName);
        String message = "";
        try {
            fileName = new String(fileName.getBytes("iso8859-1"), "utf-8");
            //获取下载文件的目录
            ServletContext context = request.getSession().getServletContext();
            System.out.println("下载文件目录：" + context);
            //上传位置
            String fileSavePath = context.getRealPath("/WEB-INF/upload/");
            System.out.println(fileSavePath + "\\" + fileName);
            //获取需要下载的文件
            File file = new File(fileSavePath + "\\" + fileName);
            //若文件不存在
            if (!file.exists()) {
                message = "您要下的资源不存在！";
                request.setAttribute("message", message);
                System.out.println("您要下的资源不存在！");
                return;
            }
            //处理文件名
            String realName = fileName.substring(fileName.indexOf("_") + 1);
            //设置响应头，控制浏览器下载该文件
            response.setHeader("content-disposition", "attachment;filename="
                    + URLEncoder.encode(realName, "UTF-8"));
            //读取要下载的文件，保存到文件输入流
            FileInputStream fis = new FileInputStream(fileSavePath + "\\" + fileName);
            OutputStream os = response.getOutputStream();
            //创建缓冲区
            byte buffer[] = new byte[1024*1024];
            int len = 0;
            while ((len = fis.read(buffer)) > 0) {
                os.write(buffer, 0, len);
            }
            os.flush();
            os.close();
            fis.close();
            message = "下载成功！";
            message += "下载内容：" + realName + "<br>";
        } catch (Exception e) {
            message = "文件下载失败，请重新下载！";
            throw new Exception(e);
        }
        request.setAttribute("message", message);
        request.getRequestDispatcher("/message.jsp").forward(request, response);
    }

}
