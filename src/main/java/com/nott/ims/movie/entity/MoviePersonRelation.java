package com.nott.ims.movie.entity;

import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("movie_person_relation")
public class MoviePersonRelation implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String movieId;

    private String writerId;

    private String actorId;

    private String directorId;


}
