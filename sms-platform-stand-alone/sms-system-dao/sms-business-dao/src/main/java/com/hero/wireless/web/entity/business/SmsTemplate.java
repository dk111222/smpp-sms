package com.hero.wireless.web.entity.business;

import com.hero.wireless.web.entity.base.BaseEntity;

import java.util.Date;

public class SmsTemplate extends BaseEntity {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sms_template.Id
     *
     * @mbg.generated Thu May 14 09:56:20 CST 2020
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sms_template.Enterprise_No
     *
     * @mbg.generated Thu May 14 09:56:20 CST 2020
     */
    private String enterprise_No;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sms_template.Enterprise_User_Id
     *
     * @mbg.generated Thu May 14 09:56:20 CST 2020
     */
    private Integer enterprise_User_Id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sms_template.Template_Name
     *
     * @mbg.generated Thu May 14 09:56:20 CST 2020
     */
    private String template_Name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sms_template.Template_Content
     *
     * @mbg.generated Thu May 14 09:56:20 CST 2020
     */
    private String template_Content;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sms_template.Template_Type
     *
     * @mbg.generated Thu May 14 09:56:20 CST 2020
     */
    private String template_Type;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sms_template.Approve_Status
     *
     * @mbg.generated Thu May 14 09:56:20 CST 2020
     */
    private String approve_Status;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sms_template.Description
     *
     * @mbg.generated Thu May 14 09:56:20 CST 2020
     */
    private String description;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sms_template.Create_Date
     *
     * @mbg.generated Thu May 14 09:56:20 CST 2020
     */
    private Date create_Date;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sms_template.Id
     *
     * @return the value of sms_template.Id
     *
     * @mbg.generated Thu May 14 09:56:20 CST 2020
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sms_template.Id
     *
     * @param id the value for sms_template.Id
     *
     * @mbg.generated Thu May 14 09:56:20 CST 2020
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sms_template.Enterprise_No
     *
     * @return the value of sms_template.Enterprise_No
     *
     * @mbg.generated Thu May 14 09:56:20 CST 2020
     */
    public String getEnterprise_No() {
        return enterprise_No;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sms_template.Enterprise_No
     *
     * @param enterprise_No the value for sms_template.Enterprise_No
     *
     * @mbg.generated Thu May 14 09:56:20 CST 2020
     */
    public void setEnterprise_No(String enterprise_No) {
        this.enterprise_No = enterprise_No;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sms_template.Enterprise_User_Id
     *
     * @return the value of sms_template.Enterprise_User_Id
     *
     * @mbg.generated Thu May 14 09:56:20 CST 2020
     */
    public Integer getEnterprise_User_Id() {
        return enterprise_User_Id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sms_template.Enterprise_User_Id
     *
     * @param enterprise_User_Id the value for sms_template.Enterprise_User_Id
     *
     * @mbg.generated Thu May 14 09:56:20 CST 2020
     */
    public void setEnterprise_User_Id(Integer enterprise_User_Id) {
        this.enterprise_User_Id = enterprise_User_Id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sms_template.Template_Name
     *
     * @return the value of sms_template.Template_Name
     *
     * @mbg.generated Thu May 14 09:56:20 CST 2020
     */
    public String getTemplate_Name() {
        return template_Name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sms_template.Template_Name
     *
     * @param template_Name the value for sms_template.Template_Name
     *
     * @mbg.generated Thu May 14 09:56:20 CST 2020
     */
    public void setTemplate_Name(String template_Name) {
        this.template_Name = template_Name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sms_template.Template_Content
     *
     * @return the value of sms_template.Template_Content
     *
     * @mbg.generated Thu May 14 09:56:20 CST 2020
     */
    public String getTemplate_Content() {
        return template_Content;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sms_template.Template_Content
     *
     * @param template_Content the value for sms_template.Template_Content
     *
     * @mbg.generated Thu May 14 09:56:20 CST 2020
     */
    public void setTemplate_Content(String template_Content) {
        this.template_Content = template_Content;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sms_template.Template_Type
     *
     * @return the value of sms_template.Template_Type
     *
     * @mbg.generated Thu May 14 09:56:20 CST 2020
     */
    public String getTemplate_Type() {
        return template_Type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sms_template.Template_Type
     *
     * @param template_Type the value for sms_template.Template_Type
     *
     * @mbg.generated Thu May 14 09:56:20 CST 2020
     */
    public void setTemplate_Type(String template_Type) {
        this.template_Type = template_Type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sms_template.Approve_Status
     *
     * @return the value of sms_template.Approve_Status
     *
     * @mbg.generated Thu May 14 09:56:20 CST 2020
     */
    public String getApprove_Status() {
        return approve_Status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sms_template.Approve_Status
     *
     * @param approve_Status the value for sms_template.Approve_Status
     *
     * @mbg.generated Thu May 14 09:56:20 CST 2020
     */
    public void setApprove_Status(String approve_Status) {
        this.approve_Status = approve_Status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sms_template.Description
     *
     * @return the value of sms_template.Description
     *
     * @mbg.generated Thu May 14 09:56:20 CST 2020
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sms_template.Description
     *
     * @param description the value for sms_template.Description
     *
     * @mbg.generated Thu May 14 09:56:20 CST 2020
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sms_template.Create_Date
     *
     * @return the value of sms_template.Create_Date
     *
     * @mbg.generated Thu May 14 09:56:20 CST 2020
     */
    public Date getCreate_Date() {
        return create_Date;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sms_template.Create_Date
     *
     * @param create_Date the value for sms_template.Create_Date
     *
     * @mbg.generated Thu May 14 09:56:20 CST 2020
     */
    public void setCreate_Date(Date create_Date) {
        this.create_Date = create_Date;
    }
}