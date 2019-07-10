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
import com.yjjk.reservation.entity.Role;
import com.yjjk.reservation.entity.RoleAndAuthority;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author CentreS
 * @Description: 管理员用户管理
 * @create 2019-06-21
 */
@Mapper
public interface RoleMapper {

    /**
     * select---获取用户角色信息
     *
     * @param role
     * @return
     */
    List<Role> getRoleInfo(Role role);

    /**
     * insert---新增角色
     *
     * @param role
     * @return
     */
    int insertSelective(Role role);

    /**
     * update---更新角色信息
     *
     * @param role
     * @return
     */
    int updateSelective(Role role);

    /********************** 角色权限 **********************/

    /**
     * insert---新增角色权限
     *
     * @param roleAndAuthority
     * @return
     */
    int insertRelationSelective(RoleAndAuthority roleAndAuthority);

    /**
     * delete---删除角色权限(关联表）
     * @param roleAndAuthority
     * @return
     */
    int deleteByRoleId(RoleAndAuthority roleAndAuthority);

}
