package com.hotel.controller;

import com.hotel.entity.CheckOutRecord;
import com.hotel.manage.CheckOutRecordManage;
import com.hotel.utils.AmountUtils;
import com.hotel.vo.ResultVo;
import com.hotel.vo.in.CheckOutVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.Date;

@Controller
@RequestMapping("checkOutRecord")
public class CheckOutRecordController extends BaseController {

    @Autowired
    CheckOutRecordManage checkOutRecordManage;

    @RequestMapping("checkOut")
    @ResponseBody
    public ResultVo<?> checkOut(@Valid @RequestBody CheckOutVo checkOutVo) {
        CheckOutRecord checkOutRecord = new CheckOutRecord();
        checkOutRecord.setId(checkOutVo.getCheckInId());
        checkOutRecord.setDeductDeposit(AmountUtils.toLong(checkOutVo.getDeductDeposit()));
        checkOutRecord.setRemark(checkOutVo.getRemark());
        checkOutRecord.setCheckOutTime(new Date());
        return ResultVo.success(checkOutRecordManage.checkOut(checkOutRecord), "退房成功");
    }
}
