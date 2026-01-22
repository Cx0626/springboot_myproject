package org.example.springboot_project.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Controller
@CrossOrigin("*") //允许跨域请求
public class FileUploadController {

    @Value("${fileUploadsURL}")
    private String baseUploadPath;

    //获取文件上传保存路径
    public String getUploadsPath() {
        // 这里需要注意的是ApplicationHome是属于SpringBoot的类
        // 获取项目下resources/static/uploads 路径
        ApplicationHome applicationHome = new ApplicationHome(this.getClass());
        // 保存目录位置根据项目需求可随意更改
        String basePath = applicationHome.getDir().getParentFile().getParentFile().getAbsolutePath() + "\\src\\main\\resources\\static\\uploads";
        //方法二 System.getProperty("user.dir") +"\\src\\main\\resources\\static\\uploads\\";
        return basePath;
    }

    /**
     * 复制文件（自动关闭流，支持大文件）
     *
     * @param sourcePath 源文件路径（如：D:/test.jpg）
     * @param targetPath 目标文件路径（如：E:/test_copy.jpg）
     * @throws IOException 复制失败时抛出异常
     */
    public static void myCopyFile(String sourcePath, String targetPath) throws IOException {
        // Paths.get() 转换字符串路径为 Path 对象
        Path source = Paths.get(sourcePath);
        Path target = Paths.get(targetPath);
        // 核心方法：复制文件（自动覆盖已存在的目标文件）
        Files.copy(source, target);
    }

    @RequestMapping(value = "/uploadFile")
    @ResponseBody
    public Object uploadFile(MultipartFile file) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (file == null) {
            map.put("code", 500);
            map.put("msg", "文件不能为空");
            map.put("data", "文件不能为空");
            return map;
        }
        //后缀名 英文
        String fileSub = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")).toLowerCase();
        Random d = new Random();
        String img = System.currentTimeMillis() + "_" + d.nextInt(10) + "" + fileSub;
        // 保存目录位置根据项目需求可随意更改
        String basePath = getUploadsPath();
        String basePath_target = getUploadsPath().replace("\\src\\main\\resources", "\\target\\classes");
        System.out.println("上传文件路径 basePath = " + basePath);
        System.out.println("上传文件路径 basePath_target = " + basePath_target);
        String path = basePath;
        try {
            File f = new File(path);
            if (!f.exists()) {
                f.mkdirs();
            }
            File f_src = new File(basePath, img);
            File f_target = new File(basePath_target, img);
            file.transferTo(f_src);  //保存到src目录
            myCopyFile(f_src.getAbsolutePath(), f_target.getAbsolutePath()); //复制文件
            map.put("code", 200);
            map.put("msg", "文件上传成功");
            map.put("data", baseUploadPath + img);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("msg", "文件上传错误，文件夹不能创建");
            map.put("data", "文件上传错误，文件夹不能创建");
            map.put("code", 500);
            return map;
        }
        return map;
    }
}