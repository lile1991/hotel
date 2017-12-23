package com.hotel.controller;

import com.hotel.entity.CheckOutRecord;
import com.hotel.manage.CheckOutRecordManage;
import com.hotel.vo.CheckOutVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

@Controller
@RequestMapping("checkOutRecord")
public class CheckOutRecordController extends BaseController {

    @Autowired
    CheckOutRecordManage checkOutRecordManage;

    @RequestMapping("checkOut")
    @ResponseBody
    public void checkOut(@Valid @RequestBody CheckOutVo checkOutVo) {
        CheckOutRecord checkOutRecord = new CheckOutRecord();
        checkOutRecord.setId(checkOutVo.getCheckInId());
        checkOutRecord.setDeductDeposit(checkOutVo.getDeductDeposit());
        checkOutRecord.setRemark(checkOutVo.getRemark());
        checkOutRecordManage.checkOut(checkOutRecord);
    }
}
