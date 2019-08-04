package com.yb.dao;

import com.yb.entity.Timing;
import org.springframework.stereotype.Repository;

/**
 * @author Jue-PC
 */
@Repository
public interface TimingMapper {

    /**
     * get Timing
     *
     * @param identity
     * @return
     */
    Timing getTiming(String identity);

    /**
     * set timing
     *
     * @param timing
     * @return
     */
    void setTiming(Timing timing);

}
