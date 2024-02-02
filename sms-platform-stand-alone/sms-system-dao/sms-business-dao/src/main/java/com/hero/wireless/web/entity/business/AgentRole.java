package com.hero.wireless.web.entity.business;

import com.hero.wireless.web.entity.base.BaseEntity;
import java.util.Date;

public class AgentRole extends BaseEntity {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column agent_role.Id
     *
     * @mbg.generated Tue Oct 19 10:30:10 CST 2021
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column agent_role.Name
     *
     * @mbg.generated Tue Oct 19 10:30:10 CST 2021
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column agent_role.Code
     *
     * @mbg.generated Tue Oct 19 10:30:10 CST 2021
     */
    private String code;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column agent_role.Up_Code
     *
     * @mbg.generated Tue Oct 19 10:30:10 CST 2021
     */
    private String up_Code;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column agent_role.Is_Share
     *
     * @mbg.generated Tue Oct 19 10:30:10 CST 2021
     */
    private Boolean is_Share;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column agent_role.Agent_Id
     *
     * @mbg.generated Tue Oct 19 10:30:10 CST 2021
     */
    private String agent_Id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column agent_role.Create_User
     *
     * @mbg.generated Tue Oct 19 10:30:10 CST 2021
     */
    private String create_User;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column agent_role.Description
     *
     * @mbg.generated Tue Oct 19 10:30:10 CST 2021
     */
    private String description;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column agent_role.Remark
     *
     * @mbg.generated Tue Oct 19 10:30:10 CST 2021
     */
    private String remark;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column agent_role.Create_Date
     *
     * @mbg.generated Tue Oct 19 10:30:10 CST 2021
     */
    private Date create_Date;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column agent_role.Id
     *
     * @return the value of agent_role.Id
     *
     * @mbg.generated Tue Oct 19 10:30:10 CST 2021
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column agent_role.Id
     *
     * @param id the value for agent_role.Id
     *
     * @mbg.generated Tue Oct 19 10:30:10 CST 2021
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column agent_role.Name
     *
     * @return the value of agent_role.Name
     *
     * @mbg.generated Tue Oct 19 10:30:10 CST 2021
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column agent_role.Name
     *
     * @param name the value for agent_role.Name
     *
     * @mbg.generated Tue Oct 19 10:30:10 CST 2021
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column agent_role.Code
     *
     * @return the value of agent_role.Code
     *
     * @mbg.generated Tue Oct 19 10:30:10 CST 2021
     */
    public String getCode() {
        return code;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column agent_role.Code
     *
     * @param code the value for agent_role.Code
     *
     * @mbg.generated Tue Oct 19 10:30:10 CST 2021
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column agent_role.Up_Code
     *
     * @return the value of agent_role.Up_Code
     *
     * @mbg.generated Tue Oct 19 10:30:10 CST 2021
     */
    public String getUp_Code() {
        return up_Code;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column agent_role.Up_Code
     *
     * @param up_Code the value for agent_role.Up_Code
     *
     * @mbg.generated Tue Oct 19 10:30:10 CST 2021
     */
    public void setUp_Code(String up_Code) {
        this.up_Code = up_Code;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column agent_role.Is_Share
     *
     * @return the value of agent_role.Is_Share
     *
     * @mbg.generated Tue Oct 19 10:30:10 CST 2021
     */
    public Boolean getIs_Share() {
        return is_Share;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column agent_role.Is_Share
     *
     * @param is_Share the value for agent_role.Is_Share
     *
     * @mbg.generated Tue Oct 19 10:30:10 CST 2021
     */
    public void setIs_Share(Boolean is_Share) {
        this.is_Share = is_Share;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column agent_role.Agent_Id
     *
     * @return the value of agent_role.Agent_Id
     *
     * @mbg.generated Tue Oct 19 10:30:10 CST 2021
     */
    public String getAgent_Id() {
        return agent_Id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column agent_role.Agent_Id
     *
     * @param agent_Id the value for agent_role.Agent_Id
     *
     * @mbg.generated Tue Oct 19 10:30:10 CST 2021
     */
    public void setAgent_Id(String agent_Id) {
        this.agent_Id = agent_Id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column agent_role.Create_User
     *
     * @return the value of agent_role.Create_User
     *
     * @mbg.generated Tue Oct 19 10:30:10 CST 2021
     */
    public String getCreate_User() {
        return create_User;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column agent_role.Create_User
     *
     * @param create_User the value for agent_role.Create_User
     *
     * @mbg.generated Tue Oct 19 10:30:10 CST 2021
     */
    public void setCreate_User(String create_User) {
        this.create_User = create_User;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column agent_role.Description
     *
     * @return the value of agent_role.Description
     *
     * @mbg.generated Tue Oct 19 10:30:10 CST 2021
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column agent_role.Description
     *
     * @param description the value for agent_role.Description
     *
     * @mbg.generated Tue Oct 19 10:30:10 CST 2021
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column agent_role.Remark
     *
     * @return the value of agent_role.Remark
     *
     * @mbg.generated Tue Oct 19 10:30:10 CST 2021
     */
    public String getRemark() {
        return remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column agent_role.Remark
     *
     * @param remark the value for agent_role.Remark
     *
     * @mbg.generated Tue Oct 19 10:30:10 CST 2021
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column agent_role.Create_Date
     *
     * @return the value of agent_role.Create_Date
     *
     * @mbg.generated Tue Oct 19 10:30:10 CST 2021
     */
    public Date getCreate_Date() {
        return create_Date;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column agent_role.Create_Date
     *
     * @param create_Date the value for agent_role.Create_Date
     *
     * @mbg.generated Tue Oct 19 10:30:10 CST 2021
     */
    public void setCreate_Date(Date create_Date) {
        this.create_Date = create_Date;
    }
}