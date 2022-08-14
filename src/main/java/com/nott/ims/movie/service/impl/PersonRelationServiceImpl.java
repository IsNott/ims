package com.nott.ims.movie.service.impl;

import com.nott.ims.movie.entity.MoviePersonRelation;
import com.nott.ims.movie.mapper.PersonRelationMapper;
import com.nott.ims.movie.service.IPersonRelationService;
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
public class PersonRelationServiceImpl extends ServiceImpl<PersonRelationMapper, MoviePersonRelation> implements IPersonRelationService {

}
