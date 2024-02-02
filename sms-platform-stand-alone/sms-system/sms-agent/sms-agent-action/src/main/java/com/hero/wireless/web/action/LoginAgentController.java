package com.hero.wireless.web.action;

import com.drondea.wireless.util.ServiceException;
import com.hero.wireless.web.config.DatabaseCache;
import com.hero.wireless.web.config.SystemKey;
import com.hero.wireless.web.entity.business.Agent;
import com.hero.wireless.web.entity.business.ext.AgentExt;
import com.hero.wireless.web.service.IAgentManage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class LoginAgentController extends BaseAgentController {

	@Resource(name = "agentManage")
	private IAgentManage agentManage;
	
	@RequestMapping("/userLogin")
	public String userLogin() {
		return "redirect:" + getDomainUrl();
	}
	@RequestMapping("/agent_Login")
	public String login(String validateCode, Agent agent) {
		if (!checkValidateCode(validateCode)) {
			request.setAttribute("msg", "验证码错误！");
			return "forward:" + getDomainUrl();
		}
		try {
			agent = agentManage.agentLogin(agent);
			request.getSession().setAttribute(SystemKey.AGENT_INFO.toString(), agent);
			String indexUrl = DatabaseCache.getStringValueBySortCodeAndCode("page_configuration", "agent_index_home", "/admin/index/admin_index.jsp");
			return "redirect:" + indexUrl;
		} catch (ServiceException be) {
			request.setAttribute("msg", be.getMessage());
			return "forward:" + getDomainUrl();
		} catch (Exception be) {
			request.setAttribute("msg", be.getMessage());
			return "forward:" + getDomainUrl();
		}
	}

	// 验证码验证
	public boolean checkValidateCode(String validateCode) {
		String kaptchaExpected = (String) request.getSession()
				.getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
		request.getSession()
				.removeAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
		if (validateCode == null || !validateCode.equalsIgnoreCase(kaptchaExpected)) {
			request.setAttribute("msg", "验证码错误");
			return false;
		}
		return true;
	}

	@RequestMapping("/agent_Logout")
	public String logout() {
		request.getSession().invalidate();
		return "redirect:" + getDomainUrl();
	}
	/**
	 * 根据域名查询代理域名设置url
	 */
	private String getDomainUrl() {
		//用户域名
		String userDomain = request.getServerName();
		AgentExt agentExt = new AgentExt();
		agentExt.setDomain(userDomain);
		List<Agent> agents = agentManage.queryAgentList(agentExt);
		
		if(agents!=null&&!agents.isEmpty()) {
			return agents.get(0).getLogin_Url();
		}
		return DatabaseCache.getStringValueBySortCodeAndCode("page_configuration","agent_login_url", "/public/admin/login.jsp");
	}

}
