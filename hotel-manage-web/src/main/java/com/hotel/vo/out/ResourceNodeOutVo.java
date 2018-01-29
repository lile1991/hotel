package com.hotel.vo.out;

import com.hotel.user.entity.Resource;
import com.hotel.utils.HotelBeanUtils;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ResourceNodeOutVo extends Resource {

    List<ResourceNodeOutVo> children;

    public void addChild(Resource child) {
        if(children == null) {
            children = new ArrayList<>();
        }

        ResourceNodeOutVo childVo;
        if(child instanceof ResourceNodeOutVo) {
            childVo = (ResourceNodeOutVo) child;
        } else {
            childVo = HotelBeanUtils.convert(child, ResourceNodeOutVo.class);
        }
        children.add(childVo);
    }
}
