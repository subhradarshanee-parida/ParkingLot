package com.parkinglot.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ParkingLot {
    /*private ArrayList<Slot> slots;*/
    private Slot[] slots;
    private int totalSlot;
     ResultSet resultSet;
    public   class MyJDBC {

       Connection connection;
        Statement statement;
        Random random;
        public MyJDBC(int totalSlot){
            try

            {
               int i=1;

                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/javadb", "root", "Mphasis77");
                Statement statement = connection.createStatement();

                while (i<=totalSlot){
                    ResultSet resultSet = statement.executeQuery("INSERT INTO `javadb`.`parking_slot`
                    (`Parking_name`,
                     `slot_no`,
                    `status`)
                    VALUES
                            ("ParkingLot",
                                    i,
                                    "E"););
                }

//        while(resultSet.next()){
//            System.out.println(resultSet.getString("parking_name"));
//        }
            } catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    ParkingLot(int totalSlot) {
        slots = new Slot[totalSlot];
        this.totalSlot = totalSlot;

    }

    private int checkSlotAvailable() {

        int slotsOccupied = slots.length;

        if (slotsOccupied == 0) {
            return 1;
        }

        for (int i = 0; i < slotsOccupied; i++) {
            if (slots[i] == null) {
                return i + 1;
            }
        }


        if (slotsOccupied < totalSlot)
            return slotsOccupied + 1;

        else
            return -1;
    }

    public void issueParkingTicket(int slotno, Car car) {

        System.out.printf("Ticket Issued on :  \n Allocated Slot No: %d \n VehichleNo : %s \n  Car colour: %s \n " ,slotno, car.getVehicleNo(), car.getColor());


    }

    public void enterVehichle(Car car) {
        int slotno = checkSlotAvailable();
        System.out.println ("Allocated slot number: "+ slotno);
        if (slotno == -1) {

            System.out.println("Sorry, parking lot is full");

        } else {
            slots[slotno-1]= new Slot(slotno, car);
            issueParkingTicket(slotno, car);

        }

    }

    private int checkIfCarExist(String vehichleNo) {

        for (int i = 0; i < slots.length; i++) {

            if (slots[i].getCar().getVehicleNo().equalsIgnoreCase(vehichleNo))
                return i + 1;
        }
        return -1;

    }


    public void onExit(Car car) {

        int releaseSlot = checkIfCarExist(car.getVehicleNo());
        if (releaseSlot > 0)
            /*slots.add(releaseSlot - 1, null);*/
            slots[releaseSlot - 1]= null;

    }

    public void onExit(String vehichleNo) {

        int releaseSlot = checkIfCarExist(vehichleNo);
        if (releaseSlot > 0)
            /*slots.add(releaseSlot - 1, null);*/
            slots[releaseSlot - 1]= null;
        /* slots.add(releaseSlot - 1, null);*/
    }

    public void  printtheSlotDetails(){

        for (int i=0;i<slots.length;i++){
            if (slots[i]!=null)
                System.out.printf(" \n Slot No Parked: %d \n VehichleNo : %s \n  Car colour: %s \n ",slots[i].getSlotNo(),slots[i].getCar().getVehicleNo(),slots[i].getCar().getColor());
            else

                System.out.printf (" \n Slot No : %d is Empty",i+1);

        }
    }

    public List findCars(String color){

        ArrayList<String> car= new ArrayList<String>();

        for (int i=0;i< slots.length;i++){
            if (slots[i].getCar().getColor().equalsIgnoreCase(color)){

                car.add(slots[i].getCar().getVehicleNo());
            }
        }

        return car;

    }

    public List findSlotForCars(String color){

        ArrayList<Integer> car= new ArrayList<>();

        for (int i=0;i< slots.length;i++){
            if (slots[i].getCar().getColor().equalsIgnoreCase(color)){

                car.add(i+1);
            }
        }

        return car;

    }
    public Integer findSlotForRegistrationNo (String registrationNo){

        ArrayList<Integer> car= new ArrayList<>();

        for (int i=0;i< slots.length;i++){
            if (slots[i].getCar().getVehicleNo().equalsIgnoreCase(registrationNo))
            {

                return i+1;
            }
        }

        return -1;

    }

    public Car findCarsusingVehichleno(String Vehichleno){

        ArrayList<Car> car= new ArrayList<Car>();

        for (int i=0;i< slots.length;i++){
            if (slots[i].getCar().getVehicleNo().equalsIgnoreCase(Vehichleno)){

                return slots[i].getCar();
            }
        }

        return null;

    }

    public  boolean release_slot(int slotNo) {

        if   (slots[slotNo-1]!= null){

            slots[slotNo-1]= null;
        }

        return true;

    }
}
