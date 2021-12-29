package com.intelliatech.queryExtractor;

public class UserExtractor {

    private Integer srNo;
    private String userName;
    private String mobileNumber;

    public Integer getSrNo() {
        return srNo;
    }

    public void setSrNo(Integer srNo) {
        this.srNo = srNo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public UserExtractor(Integer srNo, String userName, String mobileNumber) {
        this.srNo = srNo;
        this.userName = userName;
        this.mobileNumber = mobileNumber;
    }

    public UserExtractor(String userName, String mobileNumber) {
        this.userName = userName;
        this.mobileNumber = mobileNumber;
    }

    public UserExtractor() {
    }
}
