package com.parkinglot.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class MyJDBC {

    Connection connection;
    Statement statement;

public MyJDBC(){
 try

    {

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/javadb", "root", "Mphasis77");
        Statement statement = connection.createStatement();
//        ResultSet resultSet = statement.executeQuery("select  * from parking_vehichle");
//        while(resultSet.next()){
//            System.out.println(resultSet.getString("parking_name"));
//        }
    } catch(Exception e){
e.printStackTrace();
 }
}
}
