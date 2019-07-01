/**
 * Copyright (C), 2019, 义金(杭州)健康科技有限公司
 * FileName: OrderRecordServiceImpl
 * Author:   CentreS
 * Date:     2019-06-21 16:19
 * Description: 会议室预约记录
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.yjjk.reservation.service.impl;

import com.yjjk.reservation.entity.OrderHistory;
import com.yjjk.reservation.entity.OrderRecord;
import com.yjjk.reservation.service.BaseService;
import com.yjjk.reservation.service.OrderRecordService;
import com.yjjk.reservation.utility.DateUtil;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author CentreS
 * @Description: 会议室预约记录
 * @create 2019-06-21
 */
@Service
public class OrderRecordServiceImpl extends BaseService implements OrderRecordService {

    @Override
    public List<OrderRecord> getOrderRecords(OrderRecord orderRecord) {
        orderRecord.setStatus(0);
        return super.orderRecordMapper.getOrderRecords(orderRecord);
    }

    @Override
    public int insertSelective(OrderRecord orderRecord) {
        orderRecord.setStatus(0);
        return super.orderRecordMapper.insertSelective(orderRecord);
    }

    @Override
    public List<OrderRecord> selectHistory(Map<String, Object> paramMap) {
        return super.orderRecordMapper.selectHistory(paramMap);
    }

    @Override
    public int cancelOrder(OrderRecord orderRecord) {
        orderRecord.setStatus(0);
        return super.orderRecordMapper.cancelOrder(orderRecord);
    }

    @Override
    public List<OrderHistory> historyProcessor(List<OrderRecord> list) {
        List<OrderHistory> historyList = new LinkedList<>();
        OrderHistory orderHistory = null;
        String orderIds = "";

        for (int i = 0; i < list.size(); i++) {
            OrderRecord orderRecord = list.get(i);

            if (orderHistory == null) {
                orderHistory = new OrderHistory();
                orderHistory.setName(orderRecord.getName());
                orderHistory.setPath(orderRecord.getPath());
                orderHistory.setWeek(DateUtil.dateToWeek(orderRecord.getOrderDate()));
                orderHistory.setStartTime(orderRecord.getStartTime());
                orderHistory.setRoomId(orderRecord.getRoomId());
                orderHistory.setStatus(orderRecord.getStatus());
                orderHistory.setOrderDate(orderRecord.getOrderDate());
                orderHistory.setEndTime(orderRecord.getEndTime());
            }
            if ("".equals(orderIds)) {
                orderIds = orderRecord.getOrderId().toString();
            } else {
                orderIds = orderIds + "," + orderRecord.getOrderId().toString();
            }
            if (i + 1 == list.size() || !(list.get(i + 1).getOrderDate().equals(orderRecord.getOrderDate())
                    && orderRecord.getRoomId().equals(list.get(i + 1).getRoomId())
                    && orderRecord.getCreateTime().equals(list.get(i + 1).getCreateTime())
                    && orderRecord.getTimesId().equals(list.get(i + 1).getTimesId() - 1))) {
                orderHistory.setEndTime(orderRecord.getEndTime());
                orderHistory.setOrderIds(orderIds);
                historyList.add(orderHistory);
                orderHistory = null;
                orderIds = "";
            }
        }
        return historyList;
    }

    @Override
    public int cancelOrderTask(String localTime) {
        return super.orderRecordMapper.cancelOrderTask(localTime);
    }

    @Override
    public int selectByTimesIds(OrderRecord orderRecord) {
        return super.orderRecordMapper.selectByTimesIds(orderRecord);
    }
}
