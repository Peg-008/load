package com.Ricardo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/file")
public class FileList {
    @RequestMapping("/listFile")
    public void listFile(HttpServletRequest request, HttpServletResponse response)
            throws Exception{
        //获取文件的目录
        ServletContext context=request.getSession().getServletContext();
        System.out.println("下载文件目录："+context);
        //下载位置
        String filePath=context.getRealPath("/WEB-INF/upload/");
        System.out.println("下载位置:"+filePath);
        //存储要下载的文件名
        Map<String,String> fileNameMap=new HashMap<String,String >();
        //递归遍历filepath目录下所有文件和目录,将文件的文件名存储到map集合中
        listfile(new File(filePath),fileNameMap);
        System.out.println(fileNameMap);
        //将Map集合发送到listFile.jsp页面进行显示
        request.setAttribute("fileNameMap",fileNameMap);
        request.getRequestDispatcher("/listFile.jsp").forward(request, response);
    }
    private void listfile(File file, Map<String, String> fileNameMap) {
        //若file代表不是一个文件而是一个目录
        if(!file.isFile()){
            //列出该目录下的所有文件和目录
            File files[]=file.listFiles();
            //遍历files[]数组
            for(File f:files){
                listfile(f,fileNameMap);
            }
        }else{
            //获取文件原始名称
            String realName=file.getName().substring(file.getName().indexOf(" ")+1);
            fileNameMap.put(file.getName(),realName);
        }
    }
}
