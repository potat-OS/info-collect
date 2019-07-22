package com.yb.service.impl;

import com.yb.dao.TimingMapper;
import com.yb.entity.Timing;
import com.yb.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Jue-PC
 */
@Service
public class ManagerServiceImpl implements ManagerService {

    private final
    TimingMapper timingMapper;

    @Autowired
    public ManagerServiceImpl(TimingMapper timingMapper) {this.timingMapper = timingMapper;}

    @Override
    public void setTiming(Timing timing) {
        timingMapper.setTiming(timing);
    }
    @Override
    public Timing getTiming(String identity) {
        return timingMapper.getTiming(identity);
    }
}
