package com.hotel.controller;

import com.hotel.manage.user.ResourceManage;
import com.hotel.vo.ResultVo;
import com.hotel.vo.out.ResourceNodeOutVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("resource")
public class ResourceController {

    @Autowired
    ResourceManage resourceManage;

    @GetMapping("findTree")
    @ResponseBody
    public ResultVo<?> findTree() {
        ResourceNodeOutVo rootResource = resourceManage.findTree();
        return ResultVo.success(rootResource.getChildren());
    }
}
