package com.Ricardo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.UUID;

@Controller
@RequestMapping("/file")
public class FileUpload{
    @RequestMapping("/upload")
    public void upload(@RequestParam("file")MultipartFile file, HttpServletRequest request,
                         HttpServletResponse response, ModelMap model) throws Exception {
        //获取原始文件名
        String fileName=file.getOriginalFilename();
        System.out.println("原始文件名："+fileName);
        //新文件
        String newfile= UUID.randomUUID()+fileName;
        //获取项目路径
        ServletContext context=request.getSession().getServletContext();
        //上传位置
        String savePath=context.getRealPath("/WEB-INF/upload/");
        File files=new File(savePath);
        if(files.exists()&&files.isDirectory()){
            files.mkdirs();
        }
        String message="";
        InputStream is=null;
        FileOutputStream fos=null;
        byte []buffer=new byte[100*1024];//根据上传文件大小设定
        try{
            is=file.getInputStream();
            fos=new FileOutputStream(savePath+newfile);
            int len=0;
            while((len=is.read(buffer))>0){
                fos.write(buffer,0,len);
            }
            fos.flush();
            is.close();
            fos.close();
            message="上传成功！"+ "<br>";
            message+="上传内容：" + newfile + "<br>";
            System.out.println("上传内容：" +newfile+ "<br>");
        } catch (IOException e) {
            message= "文件上传失败！";
            throw new RuntimeException(e);
        }
        System.out.print("上传文件到:"+savePath+newfile);
        request.setAttribute("message",message);
        request.getRequestDispatcher("/message.jsp").forward(request, response);
    }
}
