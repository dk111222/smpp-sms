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
public class ChannelExcludeKeywordDiversion implements ISortDiversionService {

    private ChannelExcludeKeywordDiversion() {
    }

    private static class SingletonClassInstance {
        private static final ChannelExcludeKeywordDiversion diversion = new ChannelExcludeKeywordDiversion();
    }

    public static ChannelExcludeKeywordDiversion getInstance() {
        return SingletonClassInstance.diversion;
    }

    @Override
    public IDiversionResult diversion(SortContext sortContext, ProductChannels productChannel) {
        Set<String> keywords = strategy(productChannel.getChannel_No(), ProductChannelDiversionType.EXCLUDE_KEYWORD.toString());
        if (ObjectUtils.isEmpty(keywords)) {
            return new BooleanResult(false);
        }

        boolean sensitivesResult = keywords.stream().anyMatch(item -> {
            boolean isContains = sortContext.getInput().getContent().contains(item);
            return isContains;
        });
        return new BooleanResult(sensitivesResult);
    }

}
