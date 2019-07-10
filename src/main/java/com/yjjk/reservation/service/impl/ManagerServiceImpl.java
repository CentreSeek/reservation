/**
 * Copyright (C), 2019, 义金(杭州)健康科技有限公司
 * FileName: UserServiceImpl
 * Author:   CentreS
 * Date:     2019-06-21 10:46
 * Description: 管理员用户管理
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.yjjk.reservation.service.impl;

import com.yjjk.reservation.entity.Manager;
import com.yjjk.reservation.entity.ManagerAndRole;
import com.yjjk.reservation.service.BaseService;
import com.yjjk.reservation.service.ManagerService;
import com.yjjk.reservation.utility.PasswordUtils;
import com.yjjk.reservation.utility.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author CentreS
 * @Description: 管理员用户管理
 * @create 2019-06-21
 */
@Service
public class ManagerServiceImpl extends BaseService implements ManagerService {

    @Override
    public List<Manager> getManagerInfoSelective(Manager manager) {
        return super.managerMapper.getManagerInfoSelective(manager);
    }

    @Override
    public int insertSelective(Manager manager) {
        manager.setStatus(0);
        return super.managerMapper.insertSelective(manager);
    }

    @Override
    public int updateSelective(Manager manager) {
        // 用户修改密码则生成新的盐并加密密码
        if (!StringUtils.isNullorEmpty(manager.getPassword())){
            String salt = PasswordUtils.salt();
            String password = PasswordUtils.generate(manager.getPassword(), salt);
            manager.setPassword(password);
            manager.setSalt(salt);
        }
        return super.managerMapper.updateSelective(manager);
    }

    @Override
    public int updateManagerRole(Integer managerId, Integer roleId) {
        ManagerAndRole managerAndRole = new ManagerAndRole();
        managerAndRole.setManagerId(managerId);
        managerAndRole.setRoleId(roleId);
        super.managerMapper.deleteManagerRole(managerAndRole);
        int i = super.managerMapper.insertRelationSelective(managerAndRole);
        return i;
    }

    @Override
    public int deleteManagerRole(Integer managerId) {
        ManagerAndRole managerAndRole = new ManagerAndRole();
        managerAndRole.setManagerId(managerId);
        int i = super.managerMapper.deleteManagerRole(managerAndRole);
        return i;
    }
}
