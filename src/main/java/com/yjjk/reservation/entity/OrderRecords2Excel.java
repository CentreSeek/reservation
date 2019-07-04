/**
 * Copyright (C), 2019, 义金(杭州)健康科技有限公司
 * FileName: OrderRecords2Excel
 * Author:   CentreS
 * Date:     2019/7/3 16:24
 * Description: 导出为excel的预约记录
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.yjjk.reservation.entity;

import lombok.Data;

/**
 * @Description: 导出为excel的预约记录
 * @author CentreS
 * @create 2019/7/3
 */
@Data
public class OrderRecords2Excel {

    private String orderDate;
    private String times;
    private String roomName;
    private String userName;
    private String theme;
    private Integer status;
    private String orderUser;
    private String createTime;

}
