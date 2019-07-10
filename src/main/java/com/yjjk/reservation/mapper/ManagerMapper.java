/**
 * Copyright (C), 2019, 义金(杭州)健康科技有限公司
 * FileName: UserMapper
 * Author:   CentreS
 * Date:     2019-06-21 10:22
 * Description: 管理员用户管理
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.yjjk.reservation.mapper;

import com.yjjk.reservation.entity.Manager;
import com.yjjk.reservation.entity.ManagerAndRole;
import com.yjjk.reservation.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

import java.util.List;

/**
 * @Description: 管理员用户管理
 * @author CentreS
 * @create 2019-06-21
 */
@Mapper
public interface ManagerMapper {

    /**
     * select---查询管理员用户信息
     * @param manager
     * @return
     */
    List<Manager> getManagerInfoSelective(Manager manager);

    /**
     * insert---新增管理员
     * @param manager
     * @return
     */
//    @Options(useGeneratedKeys = true, keyProperty = "managerId", keyColumn = "manager_id")
    int insertSelective(Manager manager);

    /**
     * update---更新管理员信息
     * @param manager
     * @return
     */
    int updateSelective(Manager manager);

    /**************************************** 角色管理模块 ****************************************/

    /**
     * select---查询用户角色数量
     * @param managerId
     * @return
     */
    int selectCountByManagerId(Integer managerId);

    /**
     * insert---为用户赋予角色
     * @param managerAndRole
     * @return
     */
    int insertRelationSelective(ManagerAndRole managerAndRole);

    /**
     * delete---删除用户角色关联
     * @param managerAndRole
     * @return
     */
    int deleteManagerRole(ManagerAndRole managerAndRole);



}
