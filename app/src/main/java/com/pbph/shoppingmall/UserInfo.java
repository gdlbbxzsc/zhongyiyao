package com.pbph.shoppingmall;

import com.pbph.shoppingmall.model.response.GetMyCustomerResponse;
import com.pbph.shoppingmall.model.response.MoblieLoginResponse;

/**
 * Created by Administrator on 2018/5/8.
 */

public class UserInfo {

    public static UserInfo getInstance() {
        return UserInfo.InnerInstance.INSTANCE;
    }


    private static class InnerInstance {
        private static UserInfo INSTANCE = new UserInfo();
    }

    private UserInfo() {
    }


    private String token;

    /**
     * 会员编号
     */
    private int ppid;

    /**
     * 会员ID
     */
    private Integer customerId;

    /**
     * 密码
     */
    private String customerPassword;

    /**
     * 手机号
     */
    private String mobile;
    /**
     * 昵称
     */
    private String customerNickname;
    /**
     * 用户名
     */
    private String customerUsername;
    /**
     * 真实姓名
     */
    private String customerRealname;

    /**
     * 头像
     */
    private String customerImg;
    /**
     * 身份证
     */
    private String customerCardId;
    /**
     * 性别
     */
    private int sex;
    /**
     * 极光Id
     */
    private String jpushId;

    private String customerBirthday;


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getPpid() {
        return ppid;
    }

    public void setPpid(int ppid) {
        this.ppid = ppid;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getCustomerPassword() {
        return customerPassword;
    }

    public void setCustomerPassword(String customerPassword) {
        this.customerPassword = customerPassword;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCustomerNickname() {
        return customerNickname;
    }

    public void setCustomerNickname(String customerNickname) {
        this.customerNickname = customerNickname;
    }

    public String getCustomerUsername() {
        return customerUsername;
    }

    public void setCustomerUsername(String customerUsername) {
        this.customerUsername = customerUsername;
    }

    public String getCustomerRealname() {
        return customerRealname;
    }

    public void setCustomerRealname(String customerRealname) {
        this.customerRealname = customerRealname;
    }

    public String getCustomerImg() {
        return customerImg;
    }

    public void setCustomerImg(String customerImg) {
        this.customerImg = customerImg;
    }

    public String getCustomerCardId() {
        return customerCardId;
    }

    public void setCustomerCardId(String customerCardId) {
        this.customerCardId = customerCardId;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getJpushId() {
        return jpushId;
    }

    public void setJpushId(String jpushId) {
        this.jpushId = jpushId;
    }

    public String getCustomerBirthday() {
        return customerBirthday;
    }

    public void setCustomerBirthday(String customerBirthday) {
        this.customerBirthday = customerBirthday;
    }

    public void setUserInfoFromLogin(MoblieLoginResponse.DataBean dataBean) {
        MoblieLoginResponse.DataBean.CustomersBean customersBean = dataBean.getCustomers();

        token = dataBean.getTokens();

        ppid = customersBean.getPpid();
        customerId = customersBean.getCustomerId();
        customerPassword = customersBean.getCustomerPassword();
        mobile = customersBean.getMobile();
//        customerNickname = customersBean.getCustomerNickname();
        customerUsername = customersBean.getCustomerUsername();
//        customerRealname = customersBean.getCustomerRealname();
        customerImg = customersBean.getCustomerImg();
//        customerCardId = customersBean.getCustomerCardId();
        sex = customersBean.getSex();
//        jpushId = customersBean.getJpushId();
    }

    public void setUserInfoFromGetMyCustomer(GetMyCustomerResponse.DataBean dataBean) {

        GetMyCustomerResponse.DataBean.CustomerBean customersBean = dataBean.getCustomer();

        ppid = customersBean.getPpid();
        customerId = customersBean.getCustomerId();
        mobile = customersBean.getMobile();
//        customerNickname = customersBean.getCustomerNickname();
        customerUsername = customersBean.getCustomerUsername();
//        customerRealname = customersBean.getCustomerRealname();
        customerImg = customersBean.getCustomerImg();
//        customerCardId = customersBean.getCustomerCardId();
        sex = customersBean.getSex();
//        jpushId = customersBean.getJpushId();

        customerNickname = customersBean.getCustomerNickname();
        customerImg = customersBean.getCustomerImg();
        mobile = customersBean.getMobile();

        GetMyCustomerResponse.DataBean.CustomerInfoBean customerInfoBean = dataBean.getCustomerInfo();
        sex = customerInfoBean.getSex();
        customerBirthday = customerInfoBean.getCustomerBirthday();
    }

}
