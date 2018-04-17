package com.lovnx.service.impl;

import com.lovnx.entity.InterfaceLimit;
import com.lovnx.mapper.InterfaceLimitMapper;
import com.lovnx.service.InterfaceLimitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InterfaceLimitServiceImpl implements InterfaceLimitService {

    @Autowired
    private InterfaceLimitMapper mapper;

    @Override
    public InterfaceLimit getEntityByPri(Integer id) {
        return mapper.selectByPrimaryKey(id);
    }

}
