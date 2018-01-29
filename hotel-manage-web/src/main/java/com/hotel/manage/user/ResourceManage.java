package com.hotel.manage.user;

import com.hotel.room.constant.SystemConstant;
import com.hotel.user.api.ResourceApi;
import com.hotel.user.entity.Resource;
import com.hotel.utils.HotelBeanUtils;
import com.hotel.vo.out.ResourceNodeOutVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class ResourceManage {

    @Autowired
    ResourceApi resourceApi;

    public List<Resource> findAll() {
        return resourceApi.findAll();
    }

    public ResourceNodeOutVo findTree() {
        ResourceNodeOutVo root = new ResourceNodeOutVo();

        List<ResourceNodeOutVo> resourceVoList = findAll().stream().map(r -> HotelBeanUtils.convert(r, ResourceNodeOutVo.class)).collect(Collectors.toList());

        Map<Long, ResourceNodeOutVo> resourceMap = resourceVoList.stream().collect(Collectors.toMap(Resource::getId, r -> r));

        resourceVoList.forEach(resource -> {
//            String module = resource.getModule();
            Long parentId = resource.getParentId();
            if(parentId == SystemConstant.NULL_ID) {
                root.addChild(resource);
            } else {
                ResourceNodeOutVo parentResource = resourceMap.get(parentId);
                parentResource.addChild(resource);
            }
        });

        return root;
    }
}
