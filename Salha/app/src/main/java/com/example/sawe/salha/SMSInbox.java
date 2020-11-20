package com.example.sawe.salha;

/**
 * Created by Sawe on 2/8/2016.
 */
public class SMSInbox {

    int id;
    String name;
    String phone_number;
    String message;

    public SMSInbox(String name, String phone_number, String message){
        this.name = name;
        this.phone_number = phone_number;
        this.message = message;
    }//*/

    public SMSInbox(){
        this.id = id;
        this.name = name;
        this.phone_number = phone_number;
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}

