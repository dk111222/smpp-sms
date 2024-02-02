package com.hero.wireless.web.action.config;

import com.hero.wireless.web.config.DatabaseCache;
import com.hero.wireless.web.service.base.BaseSendManage;
import com.hero.wireless.web.util.QueueUtil;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


@Component("initAdminEnv")
public class InitSystemEnv implements ApplicationListener<ContextRefreshedEvent> {

	@Resource(name = "databaseCache")
	private DatabaseCache databaseCache;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		// 解决执行两次的问题
		if (event.getApplicationContext().getParent() != null) {
			return;
		}
		//设置平台
		QueueUtil.setPlatform("ADMIN");
		//开启拉取待审的队列任务
		BaseSendManage.startPollApproveQueue();
	}

}
