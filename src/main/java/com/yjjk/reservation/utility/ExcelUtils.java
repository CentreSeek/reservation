/**
 * Copyright (C), 2019, 义金(杭州)健康科技有限公司
 * FileName: ExcelUtils
 * Author:   CentreS
 * Date:     2019/7/3 15:10
 * Description: Excel工具类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.yjjk.reservation.utility;

/**
 * @author CentreS
 * @Description: Excel工具类
 * @create 2019/7/3
 */
public class ExcelUtils {
    /**
     * 是否是2003的excel，返回true是2003
     * @param filePath
     * @return
     */
    public static boolean isExcel2003(String filePath) {
        return filePath.matches("^.+\\.(?i)(xls)$");
    }

    /**
     * 是否是2007的excel，返回true是2007
     * @param filePath
     * @return
     */
    public static boolean isExcel2007(String filePath) {
        return filePath.matches("^.+\\.(?i)(xlsx)$");
    }

    /**
     * 验证EXCEL文件
     * @param filePath
     * @return
     */
    public static boolean validateExcel(String filePath) {
        if (filePath == null || !(isExcel2003(filePath) || isExcel2007(filePath))) {
            return false;
        }
        return true;
    }
}
