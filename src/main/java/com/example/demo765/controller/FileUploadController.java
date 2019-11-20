package com.example.demo765.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@RestController
public class FileUploadController {
    @PostMapping("/upload")
        //MultipartFile 用来接收前端传来的文件
    public String upload(MultipartFile uploadFile, HttpServletRequest req) throws IOException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd/");
        //创建一个项目运行目录下的uploadFile文件夹
        String realPath = req.getSession().getServletContext().getRealPath("/uploadFile/");
        Date date = new Date();
        String format = sdf.format(date);
        //加上日期用于分类
        File file = new File(realPath+format);
        //目录如果不存在就建立一个
        if(!file.isDirectory()){
            file.mkdirs();
        }
        String oldName = uploadFile.getOriginalFilename();
        System.out.println(oldName);
        String newName = UUID.randomUUID().toString()+oldName.substring(oldName.lastIndexOf("."),oldName.length());
        System.out.println(newName);
//        //传上去
//        uploadFile.transferTo(new File(String.valueOf(folder)));
        try {
            uploadFile.transferTo(new File(file,newName));
            //req.getScheme():当前的协议传输方式
            //req.getServletName：当前的服务器
            //req.serverPort:当前的端口号
            String filePath = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() +
                    "/uploadFile/" +format+ oldName;
            return filePath;
        } catch (Exception e){
            e.printStackTrace();
        }
        return "上传失败";
    }
}
