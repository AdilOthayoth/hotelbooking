package com.example.ehotelmanagerapplication;

public class ComplaintData {

    private String FullName;
    private String Email;
    private String ComplaintDetail;

    public ComplaintData() {}


    public ComplaintData(String FullName, String Email, String ComplaintDetail) {
        this.FullName = FullName;
        this.Email = Email;
        this.ComplaintDetail = ComplaintDetail;
    }

    public String getFullName() {
        return FullName;
    }

    public String getEmail() {
        return Email;
    }

    public String getComplaintDetail() {
        return ComplaintDetail;
    }

}
