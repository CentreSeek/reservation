/**
 * Copyright (C), 2019, 义金(杭州)健康科技有限公司
 * FileName: Manager
 * Author:   CentreS
 * Date:     2019-06-19 17:15
 * Description: 用户管理员
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.yjjk.reservation.entity;

import lombok.Data;

/**
 * @Description: 用户管理员
 * @author CentreS
 * @create 2019-06-19
 */
@Data
public class Manager {
    private Integer managerId;
    private Integer userId;
    private Integer type;
    private String createTime;
    private Integer status;
}
