/**
 * Copyright (C), 2019, 义金(杭州)健康科技有限公司
 * FileName: OrderRecord
 * Author:   CentreS
 * Date:     2019-06-21 16:06
 * Description: 会议室预约记录
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.yjjk.reservation.entity;

import lombok.Data;

import java.util.List;

/**
 * @Description: 会议室预约记录
 * @author CentreS
 * @create 2019-06-21
 */
@Data
public class OrderRecord {

    private Integer orderId;
    private String orderDate;
    private Integer timesId;
    private Integer roomId;
    private Integer userId;
    private String userName;
    private String theme;
    private String createTime;
    /**
     * 0:未完成预约 1：完成预约 2：取消的预约
     */
    private Integer status;

    /**
     * 新增字段: timesId的拼接字符串
     */
    private String[] timesIds;
    private String name;
    private String path;
    private String startTime;
    private String endTime;
    private String phone;
    private String city;
    private String province;
}
