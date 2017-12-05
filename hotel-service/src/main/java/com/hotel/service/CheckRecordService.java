package com.hotel.service;

import com.hotel.dao.CheckRecordRepository;
import com.hotel.entity.CheckRecord;
import org.springframework.stereotype.Service;

@Service
public class CheckRecordService extends BaseService<CheckRecord, Long, CheckRecordRepository> implements ICheckRecordService {
}
