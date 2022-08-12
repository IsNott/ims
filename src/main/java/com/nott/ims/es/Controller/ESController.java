package com.nott.ims.es.Controller;

import com.nott.ims.es.service.EsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public void EStest() {
        esService.createIndex("nott");
    }
}
