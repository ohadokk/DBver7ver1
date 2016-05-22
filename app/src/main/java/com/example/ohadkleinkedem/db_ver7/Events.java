package com.example.ohadkleinkedem.db_ver7;

public class Events {

    int _id;
    String name, Number, type;


    public Events(){}

    public Events(int id, String name, String _number, String type){
        this._id = id;
        this.name = name;
        this.Number = _number;
        this.type = type;
    }

    public Events(String name, String _phone_number, String type){
        this.name = name;
        this.Number = _phone_number;
        this.type = type;
    }

    public int getID(){
        return this._id;
    }

    public void setID(int id){
        this._id = id;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getType(){
        return this.type;
    }

    public void setType(String type){
        this.type = type;
    }

    public String getNumber(){
        return this.Number;
    }

    public void setNumber(String number){
        this.Number = number;
    }
}
