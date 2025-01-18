package com.example.ehotelmanagerapplication;

public class RoomDatainfo {

    private String image_url;
    private String room_name;
    private String room_price;
    private String num_room;

    private String room_status;


    public RoomDatainfo() {}


    public RoomDatainfo(String image_url, String room_name, String room_price, String num_room, String room_status) {
        this.image_url = image_url;
        this.room_name = room_name;
        this.room_price = room_price;
        this.num_room = num_room;
        this.room_status = room_status;
    }


    public String getImage_url() {
        return image_url;
    }

    public String getRoom_name() {
        return room_name;
    }

    public String getRoom_price() {
        return room_price;
    }

    public String getNum_room() {
        return num_room;
    }

    public String getRoom_status() {
        return room_status;
    }


    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public void setRoom_name(String room_name) {
        this.room_name = room_name;
    }

    public void setRoom_price(String room_price) {
        this.room_price = room_price;
    }

    public void setNum_room(String num_room) {
        this.num_room = num_room;
    }


    public void setRoom_status(String room_status) {
        this.room_status = room_status;
    }


}
