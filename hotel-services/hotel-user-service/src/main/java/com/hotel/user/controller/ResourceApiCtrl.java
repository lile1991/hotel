package com.hotel.user.controller;

import com.hotel.user.api.ResourceApi;
import com.hotel.user.entity.Resource;
import com.hotel.user.entity.Resource_;
import com.hotel.user.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ResourceApiCtrl implements ResourceApi {

    @Autowired
    ResourceService resourceService;

    @Override
    public List<Resource> findAll() {
        Sort sort = new Sort(Sort.Direction.ASC, Resource_.id.getName());
        return resourceService.findAll(sort);
    }
}
