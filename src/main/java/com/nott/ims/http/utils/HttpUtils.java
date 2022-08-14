package com.nott.ims.http.utils;

import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.http.HttpStatus;
import sun.net.www.http.HttpClient;

import java.io.IOException;

/**
 * @author Nott
 * @Date 2022/8/11
 */

@Slf4j
public class HttpUtils {

    public static String getFromUrl(String url) {
        String responseStr = null;
        OkHttpClient httpClient = new OkHttpClient();
        try {
            log.info("request to {}", url);
            Request request = new Request.Builder().get().url(url).build();
            Response response = httpClient.newCall(request).execute();
            responseStr = response.body().string();
            log.info("get response, code:【{}】, from {},resp---->{}", String.valueOf(response.code()), url, responseStr);
        } catch (Exception e) {
            log.info("get response error,request url {}", url);
        }
        return responseStr;
    }
}
