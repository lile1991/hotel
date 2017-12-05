package com.hotel.service;

import com.hotel.dao.OptLogRepository;
import com.hotel.entity.OptLog;
import org.springframework.stereotype.Service;

@Service
public class OptLogService extends BaseService<OptLog, Long, OptLogRepository> implements IOptLogService {
}
