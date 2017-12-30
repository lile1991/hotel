package com.hotel.user.controller;

import com.hotel.user.api.ResourceApi;
import com.hotel.user.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ResourceApiCtrl implements ResourceApi {

    @Autowired
    ResourceService resourceService;
}
