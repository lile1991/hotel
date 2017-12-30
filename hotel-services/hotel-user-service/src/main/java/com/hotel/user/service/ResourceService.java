package com.hotel.user.service;

import com.hotel.service.BaseService;
import com.hotel.user.dao.ResourceRepository;
import com.hotel.user.entity.Resource;
import org.springframework.stereotype.Service;

@Service
public class ResourceService extends BaseService<Resource, Long, ResourceRepository> {
}
