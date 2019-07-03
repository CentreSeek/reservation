/**
 * Copyright (C), 2019, 义金(杭州)健康科技有限公司
 * FileName: User
 * Author:   CentreS
 * Date:     2019-06-19 17:03
 * Description: 员工信息
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.yjjk.reservation.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @Description: 员工信息
 * @author CentreS
 * @create 2019-06-19
 */
@Data
@Getter
@Setter
public class User {

    private Integer userId;
    private String name;
    private Long phone;
    /**
     * 员工类型 0：普通员工 1：员工管理员 2：业务人员
     */
    private Integer userType;
    private String employeeCode;
    private String openId;
    private String unionId;
    private String department;
    private Integer status;
    private String createTime;

}
