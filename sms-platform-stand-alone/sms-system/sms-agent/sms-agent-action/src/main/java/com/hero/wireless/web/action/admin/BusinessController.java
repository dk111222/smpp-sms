package com.hero.wireless.web.action.admin;

import com.hero.wireless.json.LayUiObjectMapper;
import com.hero.wireless.web.action.BaseAgentController;
import com.hero.wireless.web.entity.business.ext.SmsRouteExt;
import com.hero.wireless.web.service.IBusinessManage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import javax.annotation.Resource;

@Controller
@RequestMapping("/admin/")
public class BusinessController extends BaseAgentController {
    @Resource(name = "businessManage")
    private IBusinessManage businessManage;

    @RequestMapping("business_operatorListByCountry")
    @ResponseBody
    public String operatorListByCountry(String countryNumber) {
        List<SmsRouteExt> smsRouteList = businessManage.queryOperatorListByCountry(countryNumber);
        return new LayUiObjectMapper().asSuccessString(smsRouteList);
    }
}
