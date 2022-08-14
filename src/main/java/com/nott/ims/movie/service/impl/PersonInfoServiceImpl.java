package com.nott.ims.movie.service.impl;

import com.nott.ims.movie.entity.PersonInfo;
import com.nott.ims.movie.mapper.PersonInfoMapper;
import com.nott.ims.movie.service.IPersonInfoService;
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
public class PersonInfoServiceImpl extends ServiceImpl<PersonInfoMapper, PersonInfo> implements IPersonInfoService {

}
