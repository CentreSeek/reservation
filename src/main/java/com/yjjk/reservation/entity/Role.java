/**
 * Copyright (C), 2019, 义金(杭州)健康科技有限公司
 * FileName: Role
 * Author:   CentreS
 * Date:     2019/7/8 10:49
 * Description: 用户角色表
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.yjjk.reservation.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @Description: 用户角色表
 * @author CentreS
 * @create 2019/7/8
 */
@Data
@Accessors(chain = true)
public class Role {

    private Integer roleId;
    private String name;
    private String createTime;
    private Integer status;

    private List<Authority> authorities;
    /** 角色与权限关联表id */
    private List<Integer> relIds;
}
