package com.parkinglot.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.List;

enum Command{
    create_parking_lot,
    park,
    leave,
    status,
    registration_numbers_for_cars_with_colour,
    slot_numbers_for_cars_with_colour,
    slot_number_for_registration_number
}

public class ApplicationRunner {



    public static ParkingLot parkinglot;

    public static void main(String[] args) throws Exception {

        File file = new File("C:\\Users\\subhr\\Documents\\File_repository\\Input.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String st;
        // Condition holds true till
        // there is character in a string
        while ((st = br.readLine()) != null) {
            // Print the string
            System.out.println(st);
            /*String str = " Hello I'm your String";*/
            String[] arr = st.split(" ");
           /* for (String data : arr
            ) {
                System.out.println(data);
            }*/


            switch (Command.valueOf(arr[0])) {

                case create_parking_lot:
                    create_parking_lot(arr);

                    break;
                case park:
                    park(arr);
                    break;
                case leave:
                    leave(arr);
                    break;

                case  status:
                    status();
                    break;
                case registration_numbers_for_cars_with_colour:
                    getRegistrationNumber(arr);
                    break;


                case  slot_numbers_for_cars_with_colour:
                    slot_numbers_for_cars_with_colour(arr);
                    break;

                case  slot_number_for_registration_number:
                    slot_number_for_registration_number(arr);
                    break;
                default:
                    System.out.println("Invalid Command");
                    break;
            }
        }
    }

    public static void create_parking_lot(String[] args) {
        Integer slot;
        try {
            // checking valid integer using parseInt()
            // method
            slot = Integer.parseInt(args[1]);
            /*System.out.println(
                    args[1] + " is a valid integer number");*/
            parkinglot = new ParkingLot(slot);
            System.out.println("Created a parking lot with " + args[1] + " slots  ");
        } catch (NumberFormatException e) {
            System.out.println(
                    args[1] + " is not a valid integer number");
        }


    }

    public static void park(String[] args) {

        try {
            parkinglot.enterVehichle(new Car(args[1], args[2]));

        } catch (NullPointerException e) {
            System.out.print("Caught NullPointerException");
        }
    }

    public static void leave(String[] args) {

        try {
            if (parkinglot.release_slot(Integer.parseInt(args[1]))) {
                System.out.println("Slot " + args[1] + " is free");
            }

        } catch (NullPointerException e) {
            System.out.print("Caught NullPointerException");
        }
    }

    public static void status(){

        parkinglot.printtheSlotDetails();

    }

    public static void getRegistrationNumber(String [] args){

        List cars=  parkinglot.findCars(args[1]);

        for (Object vehichleno:cars
        ) {
            System.out.println((String)vehichleno );
        }
    }

    public static void  slot_numbers_for_cars_with_colour(String[] args){
        System.out.println ("Slot No For car in "+ args[1] );

        List cars= parkinglot.findSlotForCars(args[1]);

        if (cars.size() >0)
            for (Object slotno:cars
            ) {
                System.out.println((Integer) slotno );
            }
        else{
            System.out.println("NA");
        }
    }

    public  static void slot_number_for_registration_number(String[] args){

        try {

            String newline = System.lineSeparator();

            if (parkinglot.findSlotForRegistrationNo(args[1])!= -1)
                System.out.println ("slot_number_for_registration_number "+ args[1] + newline  + parkinglot.findSlotForRegistrationNo(args[1]));
            else
                System.out.println ("slot_number_for_registration_number "+ args[1] + newline  + "Not Found");

        }
        catch (NullPointerException e){

            System.out.println(e.getMessage());

        }
    }

}
