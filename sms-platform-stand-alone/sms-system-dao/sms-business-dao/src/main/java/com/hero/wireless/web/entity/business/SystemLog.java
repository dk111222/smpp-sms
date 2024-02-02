package com.hero.wireless.web.entity.business;

import com.hero.wireless.web.entity.base.BaseEntity;

import java.util.Date;

public class SystemLog extends BaseEntity {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column system_log.Id
     *
     * @mbg.generated Thu May 14 09:56:20 CST 2020
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column system_log.User_Id
     *
     * @mbg.generated Thu May 14 09:56:20 CST 2020
     */
    private Integer user_Id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column system_log.Real_Name
     *
     * @mbg.generated Thu May 14 09:56:20 CST 2020
     */
    private String real_Name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column system_log.User_Name
     *
     * @mbg.generated Thu May 14 09:56:20 CST 2020
     */
    private String user_Name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column system_log.Module_Name
     *
     * @mbg.generated Thu May 14 09:56:20 CST 2020
     */
    private String module_Name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column system_log.Operate_Desc
     *
     * @mbg.generated Thu May 14 09:56:20 CST 2020
     */
    private String operate_Desc;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column system_log.Specific_Desc
     *
     * @mbg.generated Thu May 14 09:56:20 CST 2020
     */
    private String specific_Desc;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column system_log.Ip_Address
     *
     * @mbg.generated Thu May 14 09:56:20 CST 2020
     */
    private String ip_Address;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column system_log.Create_Date
     *
     * @mbg.generated Thu May 14 09:56:20 CST 2020
     */
    private Date create_Date;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column system_log.Id
     *
     * @return the value of system_log.Id
     *
     * @mbg.generated Thu May 14 09:56:20 CST 2020
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column system_log.Id
     *
     * @param id the value for system_log.Id
     *
     * @mbg.generated Thu May 14 09:56:20 CST 2020
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column system_log.User_Id
     *
     * @return the value of system_log.User_Id
     *
     * @mbg.generated Thu May 14 09:56:20 CST 2020
     */
    public Integer getUser_Id() {
        return user_Id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column system_log.User_Id
     *
     * @param user_Id the value for system_log.User_Id
     *
     * @mbg.generated Thu May 14 09:56:20 CST 2020
     */
    public void setUser_Id(Integer user_Id) {
        this.user_Id = user_Id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column system_log.Real_Name
     *
     * @return the value of system_log.Real_Name
     *
     * @mbg.generated Thu May 14 09:56:20 CST 2020
     */
    public String getReal_Name() {
        return real_Name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column system_log.Real_Name
     *
     * @param real_Name the value for system_log.Real_Name
     *
     * @mbg.generated Thu May 14 09:56:20 CST 2020
     */
    public void setReal_Name(String real_Name) {
        this.real_Name = real_Name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column system_log.User_Name
     *
     * @return the value of system_log.User_Name
     *
     * @mbg.generated Thu May 14 09:56:20 CST 2020
     */
    public String getUser_Name() {
        return user_Name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column system_log.User_Name
     *
     * @param user_Name the value for system_log.User_Name
     *
     * @mbg.generated Thu May 14 09:56:20 CST 2020
     */
    public void setUser_Name(String user_Name) {
        this.user_Name = user_Name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column system_log.Module_Name
     *
     * @return the value of system_log.Module_Name
     *
     * @mbg.generated Thu May 14 09:56:20 CST 2020
     */
    public String getModule_Name() {
        return module_Name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column system_log.Module_Name
     *
     * @param module_Name the value for system_log.Module_Name
     *
     * @mbg.generated Thu May 14 09:56:20 CST 2020
     */
    public void setModule_Name(String module_Name) {
        this.module_Name = module_Name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column system_log.Operate_Desc
     *
     * @return the value of system_log.Operate_Desc
     *
     * @mbg.generated Thu May 14 09:56:20 CST 2020
     */
    public String getOperate_Desc() {
        return operate_Desc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column system_log.Operate_Desc
     *
     * @param operate_Desc the value for system_log.Operate_Desc
     *
     * @mbg.generated Thu May 14 09:56:20 CST 2020
     */
    public void setOperate_Desc(String operate_Desc) {
        this.operate_Desc = operate_Desc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column system_log.Specific_Desc
     *
     * @return the value of system_log.Specific_Desc
     *
     * @mbg.generated Thu May 14 09:56:20 CST 2020
     */
    public String getSpecific_Desc() {
        return specific_Desc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column system_log.Specific_Desc
     *
     * @param specific_Desc the value for system_log.Specific_Desc
     *
     * @mbg.generated Thu May 14 09:56:20 CST 2020
     */
    public void setSpecific_Desc(String specific_Desc) {
        this.specific_Desc = specific_Desc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column system_log.Ip_Address
     *
     * @return the value of system_log.Ip_Address
     *
     * @mbg.generated Thu May 14 09:56:20 CST 2020
     */
    public String getIp_Address() {
        return ip_Address;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column system_log.Ip_Address
     *
     * @param ip_Address the value for system_log.Ip_Address
     *
     * @mbg.generated Thu May 14 09:56:20 CST 2020
     */
    public void setIp_Address(String ip_Address) {
        this.ip_Address = ip_Address;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column system_log.Create_Date
     *
     * @return the value of system_log.Create_Date
     *
     * @mbg.generated Thu May 14 09:56:20 CST 2020
     */
    public Date getCreate_Date() {
        return create_Date;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column system_log.Create_Date
     *
     * @param create_Date the value for system_log.Create_Date
     *
     * @mbg.generated Thu May 14 09:56:20 CST 2020
     */
    public void setCreate_Date(Date create_Date) {
        this.create_Date = create_Date;
    }
}