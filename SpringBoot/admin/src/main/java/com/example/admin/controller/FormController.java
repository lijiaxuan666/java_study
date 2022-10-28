package com.example.admin.controller;

import com.example.admin.pojo.UserImg;
import com.example.admin.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * 文件上传测试
 */
@Controller
public class FormController {
    @Autowired
    IUserService userService;

    @GetMapping("/form_layouts")
    public String form_layouts(){
        return "form/form_layouts";
    }
    @PostMapping("/upload")
    public String upload(@RequestParam("username") String username,
                         @RequestPart("headerImg") MultipartFile headerImg,
                         @RequestPart("photos") MultipartFile[] photos) throws IOException {
        //MultipartFile自动封装上传来的表单文件
        System.out.println("username:"+username);
        System.out.println("headerImg的大小:"+headerImg.getSize());
        System.out.println("photos的个数:"+photos.length);

        //文件大小有限制1mb，参考MultipartAutoConfiguration，可以在配置文件中自己设置大小
        if(!headerImg.isEmpty()){//如果上传的图片不为空
            UserImg userImg=new UserImg();
            userImg.setUsername(username);
            userImg.setImg(headerImg.getBytes());
            userService.saveImg(userImg);
        }
        /*
        if(photos.length>0){
            for (MultipartFile photo : photos) {
                if(!photo.isEmpty()){
                    String originalFilename = photo.getOriginalFilename();
                    photo.transferTo(new File("E:\\myself\\"+originalFilename));
                }
            }
        }
        */
        return "main";
    }

    @PostMapping("/readImg")
    public String readImg(@RequestParam("username") String username) {
        byte[] bytes=userService.findImg(username);
        return "main";
    }
}
