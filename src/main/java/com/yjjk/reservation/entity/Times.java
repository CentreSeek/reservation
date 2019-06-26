/**
 * Copyright (C), 2019, 义金(杭州)健康科技有限公司
 * FileName: Times
 * Author:   CentreS
 * Date:     2019-06-20 16:29
 * Description: 会议室预约时间段
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.yjjk.reservation.entity;

import lombok.Data;

/**
 * @Description: 会议室预约时间段
 * @author CentreS
 * @create 2019-06-20
 */
@Data
public class Times {

    private Integer timesId;
    private String startTime;
    private String endTime;

}
