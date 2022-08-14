package com.nott.ims.movie.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ModvieInfoVo {
    @JSONField(name = "id")
    private String id;
    @JSONField(name = "poster")
    private String poster;
    @JSONField(name = "name")
    private String name;
    @JSONField(name = "genre")
    private String genre;
    @JSONField(name = "description")
    private String description;
    @JSONField(name = "language")
    private String language;
    @JSONField(name = "country")
    private String country;
    @JSONField(name = "lang")
    private String lang;
    @TableField("shareImage")
    @JSONField(name = "shareImage")
    private String shareimage;
    @JSONField(name = "movie")
    private String movieId;
}
