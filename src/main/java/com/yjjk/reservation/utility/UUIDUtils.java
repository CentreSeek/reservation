/**
 * Copyright (C), 2019, 义金(杭州)健康科技有限公司
 * FileName: UUIDUtils
 * Author:   CentreS
 * Date:     2019/7/1 15:47
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.yjjk.reservation.utility;

import java.util.UUID;

/**
 * @author CentreS
 * @Description:
 * @create 2019/7/1
 */
public class UUIDUtils {
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
