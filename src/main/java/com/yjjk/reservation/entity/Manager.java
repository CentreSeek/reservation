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
import lombok.experimental.Accessors;

/**
 * @author CentreS
 * @Description: 用户管理员
 * @create 2019-06-19
 */
@Data
@Accessors(chain = true)
public class Manager {
    private Integer managerId;
    /** 用户类型0：高级管理员 1：健康助手 2：会议室管家 */
    private Integer userType;
    private String account;
    private String password;
    /** 加盐字串 */
    private String salt;
    /** 0：女 1：男 */
    private Integer sex;
    private String picture;
    private String name;
    private String phone;
    private String department;
    private String createTime;
    private Integer status;
    /** 用户角色 */
    private Integer roleId;
}
