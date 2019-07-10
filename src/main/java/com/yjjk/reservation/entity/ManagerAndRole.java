/**
 * Copyright (C), 2019, 义金(杭州)健康科技有限公司
 * FileName: ManagerAndRole
 * Author:   CentreS
 * Date:     2019/7/9 14:40
 * Description: 管理员账户与角色关联表
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.yjjk.reservation.entity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Description: 管理员账户与角色关联表
 * @author CentreS
 * @create 2019/7/9
 */
@Data
@Accessors(chain = true)
public class ManagerAndRole {

    private Integer relId;
    private Integer managerId;
    private Integer roleId;
}
