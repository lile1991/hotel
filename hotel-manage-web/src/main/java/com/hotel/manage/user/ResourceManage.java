package com.hotel.manage.user;

import com.hotel.user.api.ResourceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ResourceManage {

    @Autowired
    ResourceApi resourceApi;
}
