/**
 * Copyright (C), 2019, 义金(杭州)健康科技有限公司
 * FileName: FileUploadController
 * Author:   CentreS
 * Date:     2019/7/1 16:06
 * Description: 文件上传
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.yjjk.reservation.controller;

import com.yjjk.reservation.utility.FileNameUtils;
import com.yjjk.reservation.utility.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author CentreS
 * @Description: 文件上传
 * @create 2019/7/1
 */
@Controller
@RequestMapping("file")
public class FileUploadController extends BaseController {
    private final ResourceLoader resourceLoader;

    @Autowired
    public FileUploadController(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Value("${web.upload-path}")
    private String path;

    /**
     * 上传文件
     *
     * @param file
     * @return
     */
    @RequestMapping("fileUpload")
    public void upload(@RequestParam("file") MultipartFile file, HttpServletRequest request, HttpServletResponse response) {
        /********************** 参数初始化 **********************/
        long startTime = System.currentTimeMillis();
        boolean resultCode = false;
        String message = "";

        // 要上传的目标文件存放路径
        String localPath = path;
        // 生成新的文件名
        String fileName = FileNameUtils.getFileName(file.getOriginalFilename());
        if (!FileUtils.upload(file, localPath, fileName)) {
            message = "插入失败";
            returnResult(startTime, request, response, resultCode, message, "");
            return;
        }
        message = "插入成功";
        resultCode = true;
        returnResult(startTime, request, response, resultCode, message, path + "\\"+ fileName);
    }
}
