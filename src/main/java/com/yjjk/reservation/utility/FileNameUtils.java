/**
 * Copyright (C), 2019, 义金(杭州)健康科技有限公司
 * FileName: FileNameUtils
 * Author:   CentreS
 * Date:     2019/7/1 15:43
 * Description: 文件名生成工具
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.yjjk.reservation.utility;

/**
 * @author CentreS
 * @Description: 文件名生成工具
 * @create 2019/7/1
 */
public class FileNameUtils {

    /**
     * 获取文件后缀
     * @param fileName
     * @return
     */
    public static String getSuffix(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }

    /**
     * 生成新的文件名
     * @param fileOriginName
     * @return
     */
    public static String getFileName(String fileOriginName) {
        return UUIDUtils.getUUID() + FileNameUtils.getSuffix(fileOriginName);
    }

}
