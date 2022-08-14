package com.nott.ims.http;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author Nott
 * @Date 2022/8/11
 */

@Service("/test")
@Slf4j
public class HttpService {

    //public static void main(String[] args) throws InterruptedException {
    //   new Thread(()->{ String url = "https://movie.querydata.org/api?id=1302425";
    //       HttpUtils.getFromUrl(url);
    //   },"a").start();
    //    TimeUnit.SECONDS.sleep(5);
    //    new Thread(()->{ String url = "https://movie.querydata.org/api?id=1302425";
    //        HttpUtils.getFromUrl(url);
    //    },"b").start();
    //    TimeUnit.SECONDS.sleep(5);
    //    new Thread(()->{ String url = "https://movie.querydata.org/api?id=1302425";
    //        HttpUtils.getFromUrl(url);
    //    },"c").start();
    //}
}
