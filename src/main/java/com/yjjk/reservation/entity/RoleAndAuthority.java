/**
 * Copyright (C), 2019, 义金(杭州)健康科技有限公司
 * FileName: RoleAndAuthority
 * Author:   CentreS
 * Date:     2019/7/9 9:45
 * Description: 角色与权限关联表
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.yjjk.reservation.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @Description: 角色与权限关联表
 * @author CentreS
 * @create 2019/7/9
 */
@Data
@Accessors(chain = true)
public class RoleAndAuthority {

    private Integer relId;
    private Integer roleId;
    private Integer authorityId;

    private List<Integer> authorityIds;
}
