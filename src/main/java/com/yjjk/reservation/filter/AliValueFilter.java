/**
 * Copyright (C), 2019, 义金(杭州)健康科技有限公司
 * FileName: AliValueFilter
 * Author:   CentreS
 * Date:     2019-06-20 12:01
 * Description: 解决long过长前段精度丢失问题
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.yjjk.reservation.filter;

import com.alibaba.fastjson.serializer.ValueFilter;

/**
 * @Description: 解决long过长前段精度丢失问题
 * @author CentreS
 * @create 2019-06-20
 */
public class AliValueFilter implements ValueFilter{
    /**
     * 解决long精度丢失问题
     * @param object
     * @param name
     * @param value
     * @return
     */
    @Override
    public Object process(Object object, String name, Object value) {
        if(value instanceof Long) {
            value= value.toString();
        }
        return value;
    }


}
