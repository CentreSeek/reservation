/**
 * Copyright (C), 2019, 义金(杭州)健康科技有限公司
 * FileName: ConferenceRoomMapper
 * Author:   CentreS
 * Date:     2019-06-21 15:30
 * Description: 会议室信息
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.yjjk.reservation.mapper;

import com.yjjk.reservation.entity.ConferenceRoom;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Description: 会议室信息
 * @author CentreS
 * @create 2019-06-21
 */
@Mapper
public interface ConferenceRoomMapper {

    /**
     * select---获取会议室信息列表
     * @return
     */
    List<ConferenceRoom> getRooms();

    /**
     * update---更新会议室信息
     * @param conferenceRoom
     * @return
     */
    int updateSelective(ConferenceRoom conferenceRoom);
}
