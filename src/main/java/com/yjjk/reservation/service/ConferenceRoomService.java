/**
 * Copyright (C), 2019, 义金(杭州)健康科技有限公司
 * FileName: ConferenceRoomService
 * Author:   CentreS
 * Date:     2019-06-21 15:33
 * Description: 会议室信息
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.yjjk.reservation.service;

import com.yjjk.reservation.entity.ConferenceRoom;

import java.util.List;

/**
 * @Description: 会议室信息
 * @author CentreS
 * @create 2019-06-21
 */
public interface ConferenceRoomService {
    /**
     * 获取会议室信息列表
     * @return
     */
    public List<ConferenceRoom> getRooms();

    /**
     * 关闭会议室
     * @param roomId
     * @return
     */
    int cancleRoom(Integer roomId);
}
