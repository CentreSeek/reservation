/**
 * Copyright (C), 2019, 义金(杭州)健康科技有限公司
 * FileName: FileUtils
 * Author:   CentreS
 * Date:     2019/7/1 15:48
 * Description: 文件上传工具类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.yjjk.reservation.utility;

import org.springframework.web.multipart.MultipartFile;
import sun.swing.plaf.synth.DefaultSynthStyle;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.WildcardType;

/**
 * @author CentreS
 * @Description: 文件上传工具类
 * @create 2019/7/1
 */
public class FileUtils {
    /**
     * 上传文件
     * @param file 文件
     * @param path 文件存放路径
     * @param fileName 源文件名
     * @return
     */
    public static boolean upload(MultipartFile file, String path, String fileName) {
        String realPath = path + "/" + fileName;
        File dest = new File(realPath);
        // 判断父级目录是否存在
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }

        try {
            // 保存文件
            file.transferTo(dest);
            return true;
        } catch (IllegalStateException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
