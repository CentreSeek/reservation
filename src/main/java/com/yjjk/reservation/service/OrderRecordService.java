/**
 * Copyright (C), 2019, 义金(杭州)健康科技有限公司
 * FileName: OrderRecordService
 * Author:   CentreS
 * Date:     2019-06-21 16:17
 * Description: 会议室预约记录
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.yjjk.reservation.service;

import com.yjjk.reservation.entity.OrderHistory;
import com.yjjk.reservation.entity.OrderRecord;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 会议室预约记录
 * @author CentreS
 * @create 2019-06-21
 */
public interface OrderRecordService {

    /**
     * 获取会议预约记录
     * @param orderRecord
     * @return
     */
    List<OrderRecord> getOrderRecords(OrderRecord orderRecord);
    /**
     * insert---插入会议室预约记录
     * @param orderRecord
     * @return
     */
    int insertSelective(OrderRecord orderRecord);

    /**
     * select---查询个人历史预约信息
     * @param paramMap
     * @return
     */
    List<OrderRecord> selectHistory(Map<String,Object> paramMap);

    /**
     * update---取消订单
     * @param orderRecord
     * @return
     */
    int cancelOrder(OrderRecord orderRecord);

    /**
     * 订单处理方法
     * @param list
     * @return
     */
    List<OrderHistory> historyProcessor(List<OrderRecord> list);
    /**
     * update---定时批量过期预约order
     * @param localTime
     * @return
     */
    int cancelOrderTask(String localTime);
}
