/**
 * Copyright (C), 2019, 义金(杭州)健康科技有限公司
 * FileName: ConferenceRoom
 * Author:   CentreS
 * Date:     2019-06-21 15:21
 * Description: 会议室信息
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.yjjk.reservation.entity;

import lombok.Data;

/**
 * @Description: 会议室信息
 * @author CentreS
 * @create 2019-06-21
 */
@Data
public class ConferenceRoom {
    private Integer roomId;
    private String name;
    private String description;
    private String path;
    private String province;
    private String city;
    private Integer companyId;
    private String createTime;
    private Integer status;

}
