package com.hero.wireless.web.entity.business.ext;

import com.hero.wireless.web.entity.business.AgentCharge;

public class AgentChargeExt extends AgentCharge {

    private String min_Create_Date;
    private String max_Create_Date;

    public String getMin_Create_Date() {
        return min_Create_Date;
    }

    public void setMin_Create_Date(String min_Create_Date) {
        this.min_Create_Date = min_Create_Date;
    }

    public String getMax_Create_Date() {
        return max_Create_Date;
    }

    public void setMax_Create_Date(String max_Create_Date) {
        this.max_Create_Date = max_Create_Date;
    }
}
