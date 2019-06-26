/**
 * Copyright (C), 2019, 义金(杭州)健康科技有限公司
 * FileName: UserMapper
 * Author:   CentreS
 * Date:     2019-06-21 10:22
 * Description: 用户管理
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.yjjk.reservation.mapper;

import com.yjjk.reservation.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Description: 用户管理
 * @author CentreS
 * @create 2019-06-21
 */
@Mapper
public interface UserMapper {

    /**
     * select---查询用户信息
     * @param userInfo
     * @return
     */
    List<User> getUserInfoBySelect(User userInfo);

    /**
     * update---更新用户信息
     * @param userInfo
     * @return
     */
    int updateUserInfoBySelect(User userInfo);

    /**
     * update---绑定微信
     * @param userInfo
     * @return
     */
    int updateUserInfoByWx(User userInfo);
}
