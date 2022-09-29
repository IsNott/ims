package com.nott.ims.movie.entity;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;
import com.nott.ims.common.entity.BaseEntity;
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
public class Movie extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @JSONField(name = "id")
    private Long id;
    @JSONField(name = "originalName")
    private String originalName;
    @JSONField(name = "imdbVotes")
    private Long imdbVotes;
    @JSONField(name = "imdbRating")
    private String imdbRating;
    @JSONField(name = "rottenRating")
    private String rottenRating;
    @JSONField(name = "rottenVotes")
    private Long rottenVotes;
    @JSONField(name = "year")
    private String year;
    @JSONField(name = "imdbId")
    private String imdbId;
    @JSONField(name = "alias")
    private String alias;
    @JSONField(name = "doubanId")
    private String doubanId;
    @JSONField(name = "type")
    private String type;
    @JSONField(name = "doubanRating")
    private String doubanRating;
    @JSONField(name = "doubanVotes")
    private Long doubanVotes;
    @JSONField(name = "duration")
    private Long duration;
    @JSONField(name = "dateReleased")
    private String dateReleased;


}
