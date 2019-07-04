/**
 * Copyright (C), 2019, 义金(杭州)健康科技有限公司
 * FileName: OrderHistory
 * Author:   CentreS
 * Date:     2019-06-25 13:51
 * Description: 个人预约历史记录
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.yjjk.reservation.entity;

import lombok.Data;

/**
 * @Description: 个人预约历史记录
 * @author CentreS
 * @create 2019-06-25
 */
@Data
public class OrderHistory {

    /**
     * 会议室名称
     */
    private String name;
    /**
     * 预定用户名称
     */
    private String userName;
    private String path;
    private String week;
    private Integer roomId;
    private Integer status;
    private String orderDate;
    /**
     * 订单id拼接字串
     */
    private String orderIds;
    /**
     * 时间段信息
     */
    private String startTime;
    private String endTime;
    private String city;
    private String province;
    private String theme;
}
