package com.nott.ims.movie.vo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class PersonInfoVo {
    @JSONField(name = "id")
    private String id;
    @JSONField(name = "name")
    private String name;
    @JSONField(name = "lang")
    private String lang;
    @JSONField(name = "person")
    private String personId;
}
