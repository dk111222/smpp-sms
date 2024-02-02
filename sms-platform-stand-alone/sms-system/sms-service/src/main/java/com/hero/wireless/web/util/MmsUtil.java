package com.hero.wireless.web.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.type.TypeReference;
import com.hero.wireless.enums.MMSSubmitMaterialType;
import com.hero.wireless.json.JsonUtil;
import com.hero.wireless.web.config.DatabaseCache;
import com.hero.wireless.web.entity.business.MmsTemplate;

import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MmsUtil {

    /**
     * 获取上游通道的模板编号
     * @param submitContent
     * @return
     * @throws IOException
     */
    public static String getMmsChannelTemplateCode(String submitContent) throws IOException {
        Map<String, String> contentMap = JsonUtil.NON_NULL.readValue(submitContent, new TypeReference<Map<String, String>>() {});
        MmsTemplate mmsTemplate = DatabaseCache.getMmsTemplateCacheByCode(contentMap.get("templateCode"));
        return mmsTemplate.getChannel_Template_Code();
    }

    /**
     * 判断彩信模板是否符合单帧样式：即只包含一个文本+一个多媒体（图片/音频/视频）中的一个素材
     * @return
     */
    public static boolean isSingleFrameStyle(MmsTemplate mmsTemplate) {
        String mmsTemplateContent = mmsTemplate.getTemplate_Content();
        JSONObject jsonObject = JSONObject.parseObject(mmsTemplateContent);
        JSONArray dataArray = JSONArray.parseArray(jsonObject.getString("data"));
        if (dataArray.size() > 2) {
            return false;
        }
        Set<String> typeSet = new HashSet<>(2);
        for(int i = 0; i < dataArray.size(); i++) {
            JSONObject item = dataArray.getJSONObject(i);
            typeSet.add(item.getString("type"));
        }
        return typeSet.size() == 2 && typeSet.contains(MMSSubmitMaterialType.TXT.toString());
    }
}
