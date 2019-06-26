/**
 * Copyright (C), 2019, 义金(杭州)健康科技有限公司
 * FileName: TimesServiceImpl
 * Author:   CentreS
 * Date:     2019-06-20 16:40
 * Description: 会议室预约时间段
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.yjjk.reservation.service.impl;

import com.yjjk.reservation.controller.BaseController;
import com.yjjk.reservation.entity.Times;
import com.yjjk.reservation.service.BaseService;
import com.yjjk.reservation.service.TimesService;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @Description: 会议室预约时间段
 * @author CentreS
 * @create 2019-06-20
 */
@Service
public class TimesServiceImpl extends BaseService implements TimesService{


    @Override
    public List<Times> getTimesList() {
        return super.timesMapper.getTimesList();
    }
}
