package com.nott.ims.movie.service.impl;

import com.nott.ims.movie.entity.ModvieInfo;
import com.nott.ims.movie.mapper.InfoMapper;
import com.nott.ims.movie.service.IInfoService;
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
public class InfoServiceImpl extends ServiceImpl<InfoMapper, ModvieInfo> implements IInfoService {

}
