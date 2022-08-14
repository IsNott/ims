package com.nott.ims.movie.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 *
 * </p>
 *
 * @author nott
 * @since 2022-08-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("movie_info")
public class ModvieInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @JSONField(name = "id")
    private Long id;
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
    private Long movieId;

}
