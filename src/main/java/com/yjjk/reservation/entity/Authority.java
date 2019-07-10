/**
 * Copyright (C), 2019, 义金(杭州)健康科技有限公司
 * FileName: Authority
 * Author:   CentreS
 * Date:     2019/7/8 10:51
 * Description: 角色权限表
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.yjjk.reservation.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @Description: 角色权限表
 * @author CentreS
 * @create 2019/7/8
 */
@Data
@Accessors(chain = true)
public class Authority {

    private Integer authorityId;
    /** 父权限id，为0则为根权限 */
    private String parentId;
    private String name;
    private String url;
    private String createTime;
    private Integer status;

    /** 子标签 */
    private List<Authority> child;
}
