package com.nott.ims.es.Controller;

import com.alibaba.fastjson.JSONObject;
import com.nott.common.controller.BaseController;
import com.nott.ims.common.Result;
import com.nott.ims.common.ThreadPoolUtils;
import com.nott.ims.es.service.EsService;
import com.nott.ims.http.utils.HttpUtils;
import com.nott.ims.movie.entity.Movie;
import com.nott.ims.movie.vo.MovieVo;
import com.nott.ims.movie.vo.PersonVo;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

import static com.nott.ims.common.Const.QUERYURL;

/**
 * @author Nott
 * @Date 2022/8/12
 */

@RestController
@RequestMapping("/es")
@Slf4j
public class ESController extends BaseController<Movie> {
    @Resource
    private EsService esService;
    @Resource
    private RestHighLevelClient restHighLevelClient;
    @Value("${user}")
    private String user;

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

    //@Cacheable(value = "movie")
    @RequestMapping("/get/{id}")
    public Result getFromES(@PathVariable String id) { //1302425
        HashMap<String, String> map = new HashMap<>();
        map.put("id", id);
        String fromUrl = HttpUtils.getFromUrl(QUERYURL, map);
        MovieVo movieVo = JSONObject.parseObject(fromUrl, MovieVo.class);
        Movie movie = new Movie();
        BeanUtils.copyProperties(movieVo, movie);
        movie.setId(Long.parseLong(id));
        ThreadPoolUtils.executorService.execute(() -> {
            super.save2Redis(movie);
            super.save2Es(movie);
        });
        return Result.okData(movie.toString());
    }

    @RequestMapping("/getNacos")
    public Result testNacos(){
        return Result.ok(user);
    }

}
