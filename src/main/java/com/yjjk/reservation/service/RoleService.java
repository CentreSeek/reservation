/**
 * Copyright (C), 2019, 义金(杭州)健康科技有限公司
 * FileName: RoleService
 * Author:   CentreS
 * Date:     2019/7/8 16:50
 * Description: 角色信息
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.yjjk.reservation.service;

import com.yjjk.reservation.entity.Role;
import com.yjjk.reservation.entity.RoleAndAuthority;

import java.util.List;

/**
 * @Description: 角色信息
 * @author CentreS
 * @create 2019/7/8
 */
public interface RoleService {

    /**
     * 获取用户角色信息
     * @param role
     * @return
     */
    List<Role> getRoleInfo(Role role);

    /**
     * 新增角色
     * @param role
     * @param roleAndAuthority
     * @return
     */
    int addRole(Role role, RoleAndAuthority roleAndAuthority);

    /**
     * 删除角色
     * @param roleId
     * @return
     */
    int deleteRole(Integer roleId);

    /**
     * 更新却色权限信息
     * @param role
     * @return
     */
    int updateRole(Role role, List<Integer> authorityIds);
}
