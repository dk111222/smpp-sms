package com.hero.wireless.notify;

import com.hero.wireless.json.JsonUtil;

/**
 * 阿里语音验证回执
 */
public class AliVoiceReport {

    private String status_code;
    private String callee;
    private String ring_time;
    private String duration;
    private String voice_type;
    private String originate_time;
    private String b_start_time;
    private String end_time;
    private String call_id;
    private String caller;
    private String status_msg;
    private String out_id;
    private String toll_type;

    public AliVoiceReport() {

    }

    public static AliVoiceReport parseJson(String json) throws Exception {
        return JsonUtil.NON_NULL.readValue(json, AliVoiceReport.class);
    }

    public String getStatus_code() {
        return status_code;
    }

    public void setStatus_code(String status_code) {
        this.status_code = status_code;
    }

    public String getCallee() {
        return callee;
    }

    public void setCallee(String callee) {
        this.callee = callee;
    }

    public String getRing_time() {
        return ring_time;
    }

    public void setRing_time(String ring_time) {
        this.ring_time = ring_time;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getVoice_type() {
        return voice_type;
    }

    public void setVoice_type(String voice_type) {
        this.voice_type = voice_type;
    }

    public String getOriginate_time() {
        return originate_time;
    }

    public void setOriginate_time(String originate_time) {
        this.originate_time = originate_time;
    }

    public String getB_start_time() {
        return b_start_time;
    }

    public void setB_start_time(String b_start_time) {
        this.b_start_time = b_start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getCall_id() {
        return call_id;
    }

    public void setCall_id(String call_id) {
        this.call_id = call_id;
    }

    public String getCaller() {
        return caller;
    }

    public void setCaller(String caller) {
        this.caller = caller;
    }

    public String getStatus_msg() {
        return status_msg;
    }

    public void setStatus_msg(String status_msg) {
        this.status_msg = status_msg;
    }

    public String getOut_id() {
        return out_id;
    }

    public void setOut_id(String out_id) {
        this.out_id = out_id;
    }

    public String getToll_type() {
        return toll_type;
    }

    public void setToll_type(String toll_type) {
        this.toll_type = toll_type;
    }
}
