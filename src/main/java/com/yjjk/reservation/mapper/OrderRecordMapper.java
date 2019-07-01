/**
 * Copyright (C), 2019, 义金(杭州)健康科技有限公司
 * FileName: ConferenceRoomMapper
 * Author:   CentreS
 * Date:     2019-06-21 15:30
 * Description: 会议室记录
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.yjjk.reservation.mapper;

import com.yjjk.reservation.entity.ConferenceRoom;
import com.yjjk.reservation.entity.OrderRecord;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 会议室记录
 * @author CentreS
 * @create 2019-06-21
 */
@Mapper
public interface OrderRecordMapper {

    /**
     * select---获取会议室记录
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
     * update---定时批量过期预约order
     * @param localTime
     * @return
     */
    int cancelOrderTask(String localTime);

    /**
     * select---使用timesIds查询预订条目
     * @param orderRecord
     * @return
     */
    int selectByTimesIds(OrderRecord orderRecord);
}
