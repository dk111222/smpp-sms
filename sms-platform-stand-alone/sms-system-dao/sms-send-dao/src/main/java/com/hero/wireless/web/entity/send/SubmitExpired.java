package com.hero.wireless.web.entity.send;

import com.hero.wireless.web.entity.base.BaseEntity;

import java.util.Date;

public class SubmitExpired extends BaseEntity {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column submit_expired.Id
     *
     * @mbg.generated Thu Jan 07 20:02:42 CST 2021
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column submit_expired.Submit_Id
     *
     * @mbg.generated Thu Jan 07 20:02:42 CST 2021
     */
    private Long submit_Id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column submit_expired.Submit_Key
     *
     * @mbg.generated Thu Jan 07 20:02:42 CST 2021
     */
    private String submit_Key;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column submit_expired.Create_Date
     *
     * @mbg.generated Thu Jan 07 20:02:42 CST 2021
     */
    private Date create_Date;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column submit_expired.Id
     *
     * @return the value of submit_expired.Id
     *
     * @mbg.generated Thu Jan 07 20:02:42 CST 2021
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column submit_expired.Id
     *
     * @param id the value for submit_expired.Id
     *
     * @mbg.generated Thu Jan 07 20:02:42 CST 2021
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column submit_expired.Submit_Id
     *
     * @return the value of submit_expired.Submit_Id
     *
     * @mbg.generated Thu Jan 07 20:02:42 CST 2021
     */
    public Long getSubmit_Id() {
        return submit_Id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column submit_expired.Submit_Id
     *
     * @param submit_Id the value for submit_expired.Submit_Id
     *
     * @mbg.generated Thu Jan 07 20:02:42 CST 2021
     */
    public void setSubmit_Id(Long submit_Id) {
        this.submit_Id = submit_Id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column submit_expired.Submit_Key
     *
     * @return the value of submit_expired.Submit_Key
     *
     * @mbg.generated Thu Jan 07 20:02:42 CST 2021
     */
    public String getSubmit_Key() {
        return submit_Key;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column submit_expired.Submit_Key
     *
     * @param submit_Key the value for submit_expired.Submit_Key
     *
     * @mbg.generated Thu Jan 07 20:02:42 CST 2021
     */
    public void setSubmit_Key(String submit_Key) {
        this.submit_Key = submit_Key;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column submit_expired.Create_Date
     *
     * @return the value of submit_expired.Create_Date
     *
     * @mbg.generated Thu Jan 07 20:02:42 CST 2021
     */
    public Date getCreate_Date() {
        return create_Date;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column submit_expired.Create_Date
     *
     * @param create_Date the value for submit_expired.Create_Date
     *
     * @mbg.generated Thu Jan 07 20:02:42 CST 2021
     */
    public void setCreate_Date(Date create_Date) {
        this.create_Date = create_Date;
    }
}