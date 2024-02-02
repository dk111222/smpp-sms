package com.hero.wireless.sort.diversion.impl;

import com.drondea.wireless.util.SuperLogger;
import com.hero.wireless.enums.ProductChannelDiversionType;
import com.hero.wireless.sort.SortContext;
import com.hero.wireless.sort.diversion.ISortDiversionService;
import com.hero.wireless.web.entity.business.ProductChannels;
import com.hero.wireless.web.util.ChannelUtil.IDiversionResult;
import org.apache.commons.lang3.ObjectUtils;

import java.util.Set;

/**
 * 包含关键字的导流策略 只有短信内容包含指定的关键字，才能命中该通道 数据库结构每行一条
 *
 * @author yjb
 */
public class ChannelIncludeKeywordDiversion implements ISortDiversionService {

    private ChannelIncludeKeywordDiversion() {
    }

    private static class SingletonClassInstance {
        private static final ChannelIncludeKeywordDiversion diversion = new ChannelIncludeKeywordDiversion();
    }

    public static ChannelIncludeKeywordDiversion getInstance() {
        return SingletonClassInstance.diversion;
    }

    @Override
    public IDiversionResult diversion(SortContext sortContext, ProductChannels productChannel) {
        Set<String> keywords = strategy(productChannel.getChannel_No(), ProductChannelDiversionType.INCLUDE_KEYWORD.toString());
        if (ObjectUtils.isEmpty(keywords)) {
            return new BooleanResult(true);
        }
        boolean sensitivesResult = keywords.stream().anyMatch(item -> {
            boolean isContains = sortContext.getInput().getContent().contains(item);
            if (!isContains) {
                SuperLogger.warn("智能路由📶(" + productChannel.getChannel_No() + ") 未包含关键字(" + item + ")");
            }
            return isContains;
        });
        return new BooleanResult(sensitivesResult);
    }

}
