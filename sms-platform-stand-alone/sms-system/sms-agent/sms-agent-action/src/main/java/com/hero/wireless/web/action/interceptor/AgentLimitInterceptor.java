package com.hero.wireless.web.action.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.drondea.wireless.util.SuperLogger;
import com.hero.wireless.json.LayUiJsonObjectFmt;
import com.hero.wireless.web.config.SystemKey;
import com.hero.wireless.web.entity.business.AgentLimit;
import com.hero.wireless.web.entity.business.ext.AgentExt;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 权限验证拦截器
 * @author Agentistrator
 *
 */
public class AgentLimitInterceptor  implements HandlerInterceptor {

	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        SuperLogger.debug("进入权限拦截器");
        String requestURI = request.getRequestURI();
        if("/".equals(requestURI)){
			return true;
		}
        int requestURISize = requestURI.length();
        HttpSession session = request.getSession();
        AgentExt agent = (AgentExt) session.getAttribute(SystemKey.AGENT_INFO.toString());
        List<AgentLimit> limitList = agent.getLimits();
        for (int i = 0; i < limitList.size(); i++) {
            String limitUrl = limitList.get(i).getUrl();
            if (StringUtils.isEmpty(limitUrl)) {
                continue;
            }
            if (limitUrl.length() > requestURISize) {
                continue;
            }
            String equalRequestURI = requestURI.substring(requestURISize - limitUrl.length(), requestURISize);
            SuperLogger.debug("requestURI:" + equalRequestURI + "  limitURL:" + limitUrl);
            if (equalRequestURI.equals(limitUrl)) {
                return true;
            }
            continue;
        }
        LayUiJsonObjectFmt result = new LayUiJsonObjectFmt();
        result.setCode("-1");
        result.setMsg("无权限!");
        response.getWriter().write(JSONObject.toJSONString(result));
        return false;
    }
}