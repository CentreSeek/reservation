/**
 * Copyright (C), 2019, 义金(杭州)健康科技有限公司
 * FileName: WxController
 * Author:   CentreS
 * Date:     2019-06-24 14:08
 * Description: 微信相关
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.yjjk.reservation.controller;

/**
 * @Description: 微信相关
 * @author CentreS
 * @create 2019-06-24
 */

import com.google.gson.Gson;
import com.yjjk.reservation.entity.WechatModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/wx")
public class WxController extends BaseController {

    private static Logger log = LoggerFactory.getLogger(WxController.class);
    private static String appId = "wx163f390dc08a8531";
    private static String secret = "e727c1642bd9814bbd522512ea0ae908";

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public void getWxInfo(@RequestParam(value = "code") String code,
                          HttpServletRequest request, HttpServletResponse response) {
        /********************** 参数初始化 **********************/
        long startTime = System.currentTimeMillis();
        boolean resultCode = false;
        String message = "";
        String url = "https://api.weixin.qq.com/sns/jscode2session?" +
                "appid=" + appId + "&secret=" + secret + "&js_code=" + code + "&grant_type=authorization_code";

        RestTemplate restTemplate = new RestTemplate();
        //进行网络请求,访问url接口
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null, String.class);
        //根据返回值进行后续操作
        WechatModel wechatModel = null;
        if(responseEntity != null && responseEntity.getStatusCode() == HttpStatus.OK){
            String sessionData = responseEntity.getBody();
            Gson gson = new Gson();
            //解析从微信服务器获得的openid和session_key;
            wechatModel = gson.fromJson(sessionData,WechatModel.class);
        }
        if (wechatModel != null) {
            log.debug("微信登录返回参数:" + wechatModel);
        }
        if (wechatModel == null || wechatModel.getErrcode() != null) {
            resultCode = false;
            message = wechatModel.getErrmsg();
            returnResult(startTime, request, response, resultCode, message, wechatModel);
            return;
        }
        message = "获取成功";
        resultCode = true;
        returnResult(startTime, request, response, resultCode, message, wechatModel);
    }


//    public static void main(String[] args) {
//        String appId = "wx163f390dc08a8531";
//        String secret = "e727c1642bd9814bbd522512ea0ae908";
//        String code = "061tMdL71hdKqL1QblM71913L71tMdLi";
//        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?" +
//                "appid=" + appId + "&secret=" + secret + "&code=" + code + "&grant_type=authorization_code";
//        String url2 = "https://api.weixin.qq.com/sns/jscode2session?" +
//                "appid=" + appId + "&secret=" + secret + "&js_code=" + code + "&grant_type=authorization_code";
//        String result = NetUtils.get(url2);
//        System.out.println(result);
//    }
}