package com.hero.wireless.web.action.admin.config;

import com.drondea.wireless.util.SuperLogger;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;


@Component("initAgentEnv")
public class InitSystemEnv implements ApplicationListener<ContextRefreshedEvent> {

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		// 解决执行两次的问题
		if (event.getApplicationContext().getParent() != null) {
			return;
		}
		SuperLogger.debug("初始化.....");
	}

}
