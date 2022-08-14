package com.nott.ims.movie.entity;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

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
@ToString
public class PersonInfo implements Serializable {

    private static final long serialVersionUID = 1L;
    @JSONField(name = "id")
    private Long id;
    @JSONField(name = "name")
    private String name;
    @JSONField(name = "lang")
    private String lang;
    @JSONField(name = "person")
    private Long personId;


}
