package com.example.ehotelmanagerapplication;

public class PaymentData {

    private String CustomerName;
    private String Email;
    private String RoomName;

    private String Phone;
    private String NumberOfRooms;

    private String StayDuration;

    private String TotalCost;

    private String StartDate;

    private String EndDate;

    private String PaymentStatus;

    public PaymentData() {}


    public PaymentData(String CustomerName, String Email, String RoomName, String Phone, String NumberOfRooms, String StayDuration, String TotalCost, String StartDate, String EndDate, String PaymentStatus) {
        this.CustomerName = CustomerName;
        this.Email = Email;
        this.RoomName = RoomName;
        this.Phone = Phone;
        this.NumberOfRooms = NumberOfRooms;
        this.StayDuration = StayDuration;
        this.TotalCost = TotalCost;
        this.StartDate = StartDate;
        this.EndDate = EndDate;
        this.PaymentStatus = PaymentStatus;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public String getEmail() {
        return Email;
    }

    public String getRoomName() {
        return RoomName;
    }

    public String getPhone() {
        return Phone;
    }

    public String getNumberOfRooms() {
        return NumberOfRooms;
    }


    public String getStayDuration() {
        return StayDuration;
    }

    public String getTotalCost() {
        return TotalCost;
    }

    public String getStartDate() {
        return StartDate;
    }

    public String getEndDate() {
        return EndDate;
    }

    public String getPaymentStatus() {
        return PaymentStatus;
    }

}
