package com.nott.ims.movie.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableField;
import com.nott.ims.movie.entity.Person;
import com.nott.ims.movie.entity.PersonInfo;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@ToString
@Data
public class PersonVo {
    private static final long serialVersionUID = 1L;

    private String id;

    @JSONField(name = "data")
    private List<PersonInfoVo> personInfos;

}
