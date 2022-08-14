package com.nott.ims.movie.service.impl;

import com.nott.ims.movie.entity.Person;
import com.nott.ims.movie.mapper.PersonMapper;
import com.nott.ims.movie.service.IPersonService;
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
public class PersonServiceImpl extends ServiceImpl<PersonMapper, Person> implements IPersonService {

}
