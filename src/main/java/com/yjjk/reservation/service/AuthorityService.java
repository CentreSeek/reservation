/**
 * Copyright (C), 2019, 义金(杭州)健康科技有限公司
 * FileName: AuthorityService
 * Author:   CentreS
 * Date:     2019/7/8 15:02
 * Description: 权限管理
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.yjjk.reservation.service;

import com.yjjk.reservation.entity.Authority;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: 权限管理
 * @author CentreS
 * @create 2019/7/8
 */
public interface AuthorityService {
    /**
     * select---获取权限信息
     * @param authority
     * @return
     */
    List<Authority> getAuthorityInfoSelective(Authority authority);

    /**
     * insert---插入权限
     * @param authority
     * @return
     */
    int insertSelective(Authority authority);
}
