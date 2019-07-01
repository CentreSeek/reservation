/**
 * Copyright (C), 2019, 义金(杭州)健康科技有限公司
 * FileName: BaseController
 * Author:   CentreS
 * Date:     2019-06-18 15:59
 * Description: BaseController
 * History:
 * <author>          <time>          <version>          <desc>
 * CentreS         2019/06/18          1.0.0
 */
package com.yjjk.reservation.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.yjjk.reservation.filter.AliValueFilter;
import com.yjjk.reservation.service.*;
import org.apache.log4j.Logger;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author CentreS
 * @Description: BaseController
 * @create 2019-06-18
 */

public class BaseController {

    private static Logger logger = Logger.getLogger("weblogger");

    private static String RESULT_CODE_SUCCESS = "200";
    private static String RESULT_CODE_FAIL = "300";

    @Resource
    protected TimesService timesService;
    @Resource
    protected UserService userService;
    @Resource
    protected ConferenceRoomService conferenceRoomService;
    @Resource
    protected OrderRecordService orderRecordService;
    @Resource
    protected ManagerService managerService;

    /**
     * WEB端返回值，判断是否过期
     *
     * @param request
     * @param response
     * @param obj
     */
    public void returnResult(long startTime, HttpServletRequest request, HttpServletResponse response,
                               boolean resultCode, String msg, Object obj) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            resultMap.put("message", msg);
            resultMap.put("data", obj);
            //判断是否已经登录
            if (resultCode) {
                resultMap.put("code", RESULT_CODE_SUCCESS);
            } else {
                resultMap.put("code", RESULT_CODE_FAIL);
            }
            //解决long型过长精度丢失的问题
            String jsonArray = JSON.toJSONString(resultMap, new AliValueFilter(), new SerializerFeature[0]);
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();
            response.setContentType("text/xml;");
            response.setHeader("Cache-Control", "no-cache");
            out.print(jsonArray);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
