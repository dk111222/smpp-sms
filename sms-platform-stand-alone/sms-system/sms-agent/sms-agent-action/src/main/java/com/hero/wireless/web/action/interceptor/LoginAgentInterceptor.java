package com.hero.wireless.web.action.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

import com.alibaba.fastjson.JSONObject;
import com.hero.wireless.json.LayUiJsonObjectFmt;
import com.hero.wireless.web.config.SystemKey;
import com.hero.wireless.web.entity.business.Agent;

/**
 * 管理平台登录拦截
 * 
 * @author Administrator
 * 
 */
public class LoginAgentInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
        Agent agent = (Agent) session.getAttribute(SystemKey.AGENT_INFO.toString());
		if (agent == null) {
			String requestType = request.getHeader("X-Requested-With");
			if (requestType != null && requestType.equals("XMLHttpRequest")) {
				LayUiJsonObjectFmt result = new LayUiJsonObjectFmt();
				result.setCode("301");
				result.setMsg("会话超时!");
				result.setUrl("/");
				response.getWriter().write(JSONObject.toJSONString(result));
				return false;
			} else if("/".equals(request.getRequestURI())){
				return true;
			} else {
				response.sendRedirect("/");
			}
			return false;
		}
		return true;
	}
}
