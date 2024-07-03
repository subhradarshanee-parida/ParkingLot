package com.parkinglot.main;

public class Car {
    private String VehicleNo;
    private String color;

    Car(String VehicleNo, String color){
        this.VehicleNo=VehicleNo;
        this.color= color;


    }

    public String getVehicleNo() {
        return VehicleNo;
    }

    public String getColor() {
        return color;
    }

    public void setVehicleNo(String vehicleNo) {
        VehicleNo = vehicleNo;
    }
}
