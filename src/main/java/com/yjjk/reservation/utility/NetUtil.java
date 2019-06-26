/**
 * Copyright (C), 2019, 义金(杭州)健康科技有限公司
 * FileName: NetUtil
 * Author:   CentreS
 * Date:     2019-06-24 14:10
 * Description: 网络工具
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.yjjk.reservation.utility;

/**
 * @Description: 网络工具
 * @author CentreS
 * @create 2019-06-24
 */
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;

/**
 * Created by Song on 2016/11/28.
 * 基于HttpClient提供网络访问工具
 */
public final class NetUtil {
    public static CloseableHttpClient httpClient = HttpClientBuilder.create().build();

    /**
     * get请求获取String类型数据
     * @param url 请求链接
     * @return
     */
    public static String get(String url){
        StringBuffer sb = new StringBuffer();
        HttpGet httpGet = new HttpGet(url);
        try {
            HttpResponse response = httpClient.execute(httpGet);

            HttpEntity entity = response.getEntity();
            InputStreamReader reader = new InputStreamReader(entity.getContent(),"utf-8");
            char [] charbufer;
            while (0<reader.read(charbufer=new char[10])){
                sb.append(charbufer);
            }
        }catch (IOException e){//1
            e.printStackTrace();
        }finally {
            httpGet.releaseConnection();
        }
        return sb.toString();
    }

    /**
     * post方式请求数据
     * @param url 请求链接
     * @param data post数据体
     * @return
     */
    @SuppressWarnings("unchecked")
    public static String post(String url, Map<String,String> data){
        StringBuffer sb = new StringBuffer();
        HttpPost httpPost = new HttpPost(url);
        List<NameValuePair> valuePairs = new ArrayList<NameValuePair>();
        if(null != data) {
            for (String key : data.keySet()) {
                valuePairs.addAll((Collection<? extends NameValuePair>) new BasicNameValuePair(key, data.get(key)));
            }
        }
        try {
            httpPost.setEntity(new UrlEncodedFormEntity((List<? extends org.apache.http.NameValuePair>) valuePairs));
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity httpEntity = response.getEntity();
            BufferedInputStream bis = new BufferedInputStream(httpEntity.getContent());
            byte [] buffer;
            while (0<bis.read(buffer=new byte[128])){
                sb.append(new String(buffer,"utf-8"));
            }
        }catch (UnsupportedEncodingException e){//数据格式有误
            e.printStackTrace();
        }catch (IOException e){//请求出错
            e.printStackTrace();
        }finally {
            httpPost.releaseConnection();
        }
        return sb.toString();
    }


}
