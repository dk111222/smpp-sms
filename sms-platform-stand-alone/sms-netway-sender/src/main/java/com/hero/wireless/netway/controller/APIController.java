package com.hero.wireless.netway.controller;

import com.drondea.wireless.util.IpUtil;
import com.drondea.wireless.util.ServiceException;
import com.drondea.wireless.util.SuperLogger;
import com.hero.wireless.enums.MessageType;
import com.hero.wireless.enums.ProtocolType;
import com.hero.wireless.netway.controller.wrap.DrondeaJsonRequestData;
import com.hero.wireless.netway.service.IHttpService;
import com.hero.wireless.netway.service.impl.BaseService;
import com.hero.wireless.notify.JsonBase;
import com.hero.wireless.notify.JsonQueryResult;
import com.hero.wireless.notify.JsonResponse;
import com.hero.wireless.notify.JsonSubmit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author volcano
 * @version V1.0
 * @date 2019年9月19日上午7:26:33
 */
@SuppressWarnings("deprecation")
@Controller
public class APIController extends BaseService {
    @Autowired
    private IHttpService apiService;
    @Autowired
    public HttpServletRequest request;

    @RequestMapping("/json/submit")
    @ResponseBody
    public String submit(@RequestBody String json) {
        try {
            SuperLogger.debug(json);
            DrondeaJsonRequestData data = new DrondeaJsonRequestData(json);
            data.checkSign();
            JsonSubmit submit = data.assembleSubmitRequestData();
            submit.setClientIp(IpUtil.getRemoteIpAddr(request));
            submit.setProtocolType(ProtocolType.HTTP_JSON.toString());
            submit.setMessageType(MessageType.SMS.toString());
            String returnString = apiService.submit(submit);
            SuperLogger.debug("给下游返回结果：" + returnString.toString());
            return (returnString);
        } catch (Exception e) {
            SuperLogger.error(e.getMessage(), e);
            return (new JsonResponse("-1000", e.getMessage()).toJson());
        }
    }

    @RequestMapping("/json/balance")
    @ResponseBody
    public String balance(@RequestBody String json) {
        try {
            SuperLogger.debug(json);
            DrondeaJsonRequestData requestData = new DrondeaJsonRequestData(json);
            requestData.checkSign();
            JsonBase balance = requestData.assembleBalaceRequestData();
            balance.setClientIp(IpUtil.getRemoteIpAddr(request));
            JsonResponse info = apiService.balance(balance);
            return (info.toJson());
        } catch (Exception e) {
            SuperLogger.error(e.getMessage(), e);
            return (new JsonResponse("-1000", e.getMessage()).toJson());
        }
    }

    @RequestMapping("/json/report")
    @ResponseBody
    public String report(@RequestBody String json) {
        try {
            SuperLogger.debug(json);
            DrondeaJsonRequestData requestData = new DrondeaJsonRequestData(json);
            requestData.checkSign();
            JsonBase base = requestData.assembleReportRequestData();
            base.setClientIp(IpUtil.getRemoteIpAddr(request));
            JsonQueryResult info = apiService.queryReport(base);
            return (info.toReportJson());
        } catch (ServiceException e) {
            SuperLogger.error(e.getMessage(), e);
            return (new JsonResponse("-1000", e.getMessage()).toJson());
        } catch (Exception e) {
            SuperLogger.error(e.getMessage(), e);
            return (new JsonResponse("-1000", e.getMessage()).toJson());
        }
    }

    @RequestMapping("/json/mo")
    @ResponseBody
    public String mo(@RequestBody String json) {
        try {
            SuperLogger.debug(json);
            DrondeaJsonRequestData requestData = new DrondeaJsonRequestData(json);
            requestData.checkSign();
            JsonBase base = requestData.assembleMoRequestData();
            base.setClientIp(IpUtil.getRemoteIpAddr(request));
            JsonQueryResult info = apiService.queryMo(base);
            return (info.toMoJson());
        } catch (ServiceException e) {
            SuperLogger.error(e.getMessage(), e);
            return (new JsonResponse("-1000", e.getMessage()).toJson());
        } catch (Exception e) {
            SuperLogger.error(e.getMessage(), e);
            return (new JsonResponse("-1000", e.getMessage()).toJson());
        }
    }

    @RequestMapping("/mms/submit")
    @ResponseBody
    public String mmsSubmit(@RequestBody String json) {
        SuperLogger.debug(json);
        try {
            SuperLogger.debug(json);
            DrondeaJsonRequestData data = new DrondeaJsonRequestData(json);
            data.checkSign();
            JsonSubmit submit = data.assembleSubmitRequestData();
            submit.setProtocolType(ProtocolType.HTTP_JSON.toString());
            submit.setMessageType(MessageType.MMS.toString());
            String returnString = apiService.submit(submit);
            SuperLogger.debug("给下游返回结果：" + returnString.toString());
            return (returnString);
        } catch (Exception e) {
            SuperLogger.error(e.getMessage(), e);
            return (new JsonResponse("-1000", e.getMessage()).toJson());
        }
    }


    @RequestMapping("/video/submit")
    @ResponseBody
    public String videoSubmit(@RequestBody String json) {
        SuperLogger.debug(json);
        try {
            SuperLogger.debug(json);
            DrondeaJsonRequestData data = new DrondeaJsonRequestData(json);
            data.checkSign();
            JsonSubmit submit = data.assembleSubmitRequestData();
            submit.setProtocolType(ProtocolType.HTTP_JSON.toString());
            submit.setMessageType(MessageType.VIDEO.toString());
            String returnString = apiService.submit(submit);
            SuperLogger.debug("给下游返回结果：" + returnString.toString());
            return (returnString);
        } catch (Exception e) {
            SuperLogger.error(e.getMessage(), e);
            return (new JsonResponse("-1000", e.getMessage()).toJson());
        }
    }

    @RequestMapping("/voice/submit")
    @ResponseBody
    public String submitVoice(@RequestBody String json) {
        try {
            SuperLogger.debug(json);
            DrondeaJsonRequestData data = new DrondeaJsonRequestData(json);
            data.checkSign();
            JsonSubmit submit = data.assembleSubmitRequestData();
            submit.setClientIp(IpUtil.getRemoteIpAddr(request));
            submit.setProtocolType(ProtocolType.HTTP_JSON.toString());
            submit.setMessageType(MessageType.VOICE.toString());
            String returnString = apiService.submit(submit);
            SuperLogger.debug("给下游返回结果：" + returnString.toString());
            return (returnString);
        } catch (Exception e) {
            SuperLogger.error(e.getMessage(), e);
            return (new JsonResponse("-1000", e.getMessage()).toJson());
        }
    }

}
