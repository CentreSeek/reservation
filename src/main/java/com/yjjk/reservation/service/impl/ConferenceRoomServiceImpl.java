/**
 * Copyright (C), 2019, 义金(杭州)健康科技有限公司
 * FileName: ConferenceRoomServiceImpl
 * Author:   CentreS
 * Date:     2019-06-21 15:33
 * Description: 会议室信息
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.yjjk.reservation.service.impl;

import com.yjjk.reservation.entity.ConferenceRoom;
import com.yjjk.reservation.service.BaseService;
import com.yjjk.reservation.service.ConferenceRoomService;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;

/**
 * @Description: 会议室信息
 * @author CentreS
 * @create 2019-06-21
 */
@Service
public class ConferenceRoomServiceImpl extends BaseService implements ConferenceRoomService {

    @Override
    public List<ConferenceRoom> getRooms() {
        return super.conferenceRoomMapper.getRooms();
    }
}
