package com.nott.ims.es.Controller;

import com.alibaba.fastjson.JSONObject;
import com.nott.ims.common.Result;
import com.nott.ims.es.service.EsService;
import com.nott.ims.http.utils.HttpUtils;
import com.nott.ims.movie.vo.MovieVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.nott.ims.common.Const.QUERYURL;

/**
 * @author Nott
 * @Date 2022/8/12
 */

@RestController
@RequestMapping("/es")

public class ESController {
    @Autowired
    private EsService esService;

    @RequestMapping("/test")
    public Result EStest() {
//        esService.createIndex("nott");
        return Result.okData(esService.existsIndex("nott"));
    }

    @RequestMapping("/test1")
    public Result EStest1() {
//        esService.createIndex("nott");
        return Result.okData(esService.delIndex("nott"));
    }

    @Cacheable(value = "movie")
    @RequestMapping("/get")
    public Result getFromDB() {
        String fromUrl = HttpUtils.getFromUrl(QUERYURL + "?id=1302425");
        MovieVo movieVo = JSONObject.parseObject(fromUrl, MovieVo.class);
        return Result.okData(movieVo.toString());
    }

}
