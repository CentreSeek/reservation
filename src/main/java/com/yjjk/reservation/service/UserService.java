/**
 * Copyright (C), 2019, 义金(杭州)健康科技有限公司
 * FileName: UserService
 * Author:   CentreS
 * Date:     2019-06-21 10:45
 * Description: 用户管理
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.yjjk.reservation.service;

import com.yjjk.reservation.entity.User;

import java.util.List;
import java.util.Map;

/**
 * @Description: 用户管理
 * @author CentreS
 * @create 2019-06-21
 */
public interface UserService {


    /**
     * select---查询用户信息
     * @param userInfo
     * @return
     */
    List<User> getUserInfoBySelect(User userInfo);

    /**
     * select---根据openId和unionId查询用户信息
     * @param openId
     * @param unionId
     * @return
     */
    List<User> getUserInfoByWX(String openId, String unionId);

    /**
     * select---使用name和phone查询用户
     * @param name
     * @param phone
     * @return
     */
    List<User> getUserInfo(String name, Long phone);

    /**
     * update---更新用户信息
     * @param userInfo
     * @return
     */
    int updateUserInfoBySelect(User userInfo);

    /**
     * update---绑定微信
     * @param openId
     * @param unionId
     * @param name
     * @param phone
     * @return
     */
    int bandingWx(String openId, String unionId, String name, Long phone);

}
