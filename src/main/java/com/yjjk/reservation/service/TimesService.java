/**
 * Copyright (C), 2019, 义金(杭州)健康科技有限公司
 * FileName: TimesService
 * Author:   CentreS
 * Date:     2019-06-20 16:38
 * Description: 会议室预约时间段
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.yjjk.reservation.service;

import com.yjjk.reservation.entity.Times;

import java.util.List;

/**
 * @Description: 会议室预约时间段
 * @author CentreS
 * @create 2019-06-20
 */
public interface TimesService {


    /**
     * 获取时间段list
     * @return
     */
    List<Times> getTimesList();

}
