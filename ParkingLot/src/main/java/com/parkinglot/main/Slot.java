package com.parkinglot.main;

public class Slot {
    private int slotNo;
    private Car car;

    Slot (int slotNo,Car car){

        this.slotNo= slotNo;
        this.car= car  ;
    }

    public int getSlotNo() {
        return slotNo;
    }

    public Car getCar() {
        return car;
    }

    public int getSlotNo(String VehicleNo) {

        if (VehicleNo== car.getVehicleNo())

            return slotNo;
        else return 0;
    }
}
