/**
 * Copyright (C), 2019, 义金(杭州)健康科技有限公司
 * FileName: RoleServiceImpl
 * Author:   CentreS
 * Date:     2019/7/8 16:51
 * Description: 角色信息
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.yjjk.reservation.service.impl;

import com.yjjk.reservation.entity.Authority;
import com.yjjk.reservation.entity.Role;
import com.yjjk.reservation.entity.RoleAndAuthority;
import com.yjjk.reservation.service.BaseService;
import com.yjjk.reservation.service.RoleService;
import org.apache.log4j.Level;
import org.apache.log4j.Priority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.logging.Logger;

/**
 * @author CentreS
 * @Description: 角色信息
 * @create 2019/7/8
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class RoleServiceImpl extends BaseService implements RoleService {

    @Override
    public List<Role> getRoleInfo(Role role) {
        return super.roleMapper.getRoleInfo(role);
    }

    @Override
    public int addRole(Role role, RoleAndAuthority roleAndAuthority) {
        int i = super.roleMapper.insertSelective(role);
        int j = super.roleMapper.insertRelationSelective(roleAndAuthority);
        if (i == 0) {
            String errInfo = String.format("新增角色失败，执行条目： role: %d   rel: %d ", i, j);
            logger.info(errInfo);
            throw new RuntimeException("新增角色失败");
        }
        return i;
    }

    @Override
    public int deleteRole(Integer roleId) {
        Role role = new Role();
        role.setRoleId(roleId).setStatus(1);
        RoleAndAuthority roleAndAuthority = new RoleAndAuthority();
        roleAndAuthority.setRoleId(roleId);
        int i = super.roleMapper.updateSelective(role);
        int j = super.roleMapper.deleteByRoleId(roleAndAuthority);
        if (i == 0){
            String errInfo = String.format("删除角色失败，执行条目： role: %d   rel: %d ", i, j);
            logger.info(errInfo);
            throw new RuntimeException("删除角色失败");
        }
        return i;
    }

    @Override
    public int updateRole(Role role, List<Integer> authorityIds) {
        // 更新role表信息
        int i = super.roleMapper.updateSelective(role);
        // 删除rel表信息
        RoleAndAuthority roleAndAuthority = new RoleAndAuthority();
        roleAndAuthority.setRoleId(role.getRoleId());
        int j = super.roleMapper.deleteByRoleId(roleAndAuthority);
        // 新增rel表信息
        roleAndAuthority.setAuthorityIds(authorityIds);
        int k = super.roleMapper.insertRelationSelective(roleAndAuthority);
        return k;
    }

}
