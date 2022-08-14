package com.nott.ims.movie.service.impl;

import com.nott.ims.movie.entity.Movie;
import com.nott.ims.movie.mapper.MovieMapper;
import com.nott.ims.movie.service.IMovieService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author nott
 * @since 2022-08-14
 */
@Service
public class MovieServiceImpl extends ServiceImpl<MovieMapper, Movie> implements IMovieService {

}
