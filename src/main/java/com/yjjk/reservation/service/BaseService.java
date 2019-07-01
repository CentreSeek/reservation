/**
 * Copyright (C), 2019, 义金(杭州)健康科技有限公司
 * FileName: BaseService
 * Author:   CentreS
 * Date:     2019-06-20 16:34
 * Description: base service
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.yjjk.reservation.service;

import com.yjjk.reservation.mapper.*;

import javax.annotation.Resource;

/**
 * @Description: base service
 * @author CentreS
 * @create 2019-06-20
 */
public class BaseService {
    @Resource
    protected TimesMapper timesMapper;
    @Resource
    protected UserMapper userMapper;
    @Resource
    protected ConferenceRoomMapper conferenceRoomMapper;
    @Resource
    protected OrderRecordMapper orderRecordMapper;
    @Resource
    protected ManagerMapper managerMapper;
}
