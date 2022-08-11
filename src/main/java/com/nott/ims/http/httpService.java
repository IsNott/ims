package com.nott.ims.http;

import com.nott.ims.http.utils.httpUtils;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.internal.http.HttpMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author Nott
 * @Date 2022/8/11
 */

@Service("/test")
@Slf4j
public class httpService {


    public static void main(String[] args) throws InterruptedException {
       new Thread(()->{ String url = "https://movie.querydata.org/api?id=1302425";
           httpUtils.getFromUrl(url);
       },"a").start();
        TimeUnit.SECONDS.sleep(5);
        new Thread(()->{ String url = "https://movie.querydata.org/api?id=1302425";
            httpUtils.getFromUrl(url);
        },"b").start();
        TimeUnit.SECONDS.sleep(5);
        new Thread(()->{ String url = "https://movie.querydata.org/api?id=1302425";
            httpUtils.getFromUrl(url);
        },"c").start();
    }
}
