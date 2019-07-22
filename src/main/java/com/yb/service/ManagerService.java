package com.yb.service;

import com.yb.entity.Timing;

/**
 * @author Jue-PC
 */
public interface ManagerService {
    /**
     * get timing
     *
     * @param identity
     * @return
     */
    Timing getTiming(String identity);

    /**
     * setTiming
     *
     * @param timing
     */
    void setTiming(Timing timing);

}
