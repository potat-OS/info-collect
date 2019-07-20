package com.yb.util.Excel;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class TimerStarter implements InitializingBean {

    private final
    ExcelUtilExecutor executor;

    public TimerStarter(ExcelUtilExecutor executor) {this.executor = executor;}

    @Override
    public void afterPropertiesSet() throws Exception {
        executor.setTimer();
    }
}
