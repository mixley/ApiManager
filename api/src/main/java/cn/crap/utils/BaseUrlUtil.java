package cn.crap.utils;

import com.google.common.base.Strings;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: 李志锐
 * @CreateTime: 2019-09-05 15:53
 * @Description:
 **/
public class BaseUrlUtil {
    private BaseUrlUtil(){}
    private static String BASEURL=null;
    public static void setBaseUrl(String baseUrl){
        BASEURL=baseUrl;
    }
    public static String getBaseUrl(HttpServletRequest request){
        if (Strings.isNullOrEmpty(BASEURL)){
            BASEURL = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() ;
        }
        return BASEURL;
    }
    public static String getBaseUrl(){
        return BASEURL;
    }
}
