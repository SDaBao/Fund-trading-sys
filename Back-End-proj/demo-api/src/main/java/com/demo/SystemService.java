package com.demo;

import com.hundsun.jrescloud.rpc.annotation.CloudService;

import java.time.LocalDate;

@CloudService
public interface SystemService {
    String systemClear();
    String systemRollback();
    String setDate(String date);
    String getDate();
}
