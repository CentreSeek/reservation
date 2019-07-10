/**
 * Copyright (C), 2019, 义金(杭州)健康科技有限公司
 * FileName: AuthorityServiceImpl
 * Author:   CentreS
 * Date:     2019/7/8 15:03
 * Description: 权限管理
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.yjjk.reservation.service.impl;

import com.yjjk.reservation.entity.Authority;
import com.yjjk.reservation.service.AuthorityService;
import com.yjjk.reservation.service.BaseService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: 权限管理
 * @author CentreS
 * @create 2019/7/8
 */
@Service
public class AuthorityServiceImpl extends BaseService implements AuthorityService  {

    @Override
    public List<Authority> getAuthorityInfoSelective(Authority authority) {
        return super.authorityMapper.getAuthorityInfoSelective(authority);
    }

    @Override
    public int insertSelective(Authority authority) {
        return super.authorityMapper.insertSelective(authority);
    }

}
