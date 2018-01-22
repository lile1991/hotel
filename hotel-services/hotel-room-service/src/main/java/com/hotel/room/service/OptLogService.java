package com.hotel.room.service;

import com.hotel.room.dao.OptLogRepository;
import com.hotel.room.entity.OptLog;
import com.hotel.service.BaseService;
import org.springframework.stereotype.Service;

@Service
public class OptLogService extends BaseService<OptLog, Long, OptLogRepository> {
}
