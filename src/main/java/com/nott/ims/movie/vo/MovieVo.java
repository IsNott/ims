package com.nott.ims.movie.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableField;
import com.nott.ims.movie.entity.ModvieInfo;
import com.nott.ims.movie.entity.Person;
import lombok.Data;

import java.util.List;

@Data
public class MovieVo {

    @JSONField(name = "id")
    private String id;
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


    @JSONField(name = "data")
    private List<ModvieInfoVo> movieInfo;
    private List<PersonVo> actor;
    private List<PersonVo> writer;
    private List<PersonVo> director;


    @Override
    public String toString() {
        return "MovieVo{" +
                "id='" + id + '\'' +
                ", originalName='" + originalName + '\'' +
                ", imdbVotes=" + imdbVotes +
                ", imdbRating='" + imdbRating + '\'' +
                ", rottenRating='" + rottenRating + '\'' +
                ", rottenVotes=" + rottenVotes +
                ", year='" + year + '\'' +
                ", imdbId='" + imdbId + '\'' +
                ", alias='" + alias + '\'' +
                ", doubanId='" + doubanId + '\'' +
                ", type='" + type + '\'' +
                ", doubanRating='" + doubanRating + '\'' +
                ", doubanVotes=" + doubanVotes +
                ", duration=" + duration +
                ", dateReleased='" + dateReleased + '\'' +
                ", movieInfo=" + movieInfo +
                ", actor=" + actor +
                ", writer=" + writer +
                ", director=" + director +
                '}';
    }
}
