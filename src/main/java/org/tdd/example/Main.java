package org.tdd.example;

public class Main {
    public static void main(String[] args) {

        BirhdayGreet bg=new BirhdayGreet();

//        System.out.println("Printing data in the file:");
//
//        bg.info();

        //to print the data in text file

        System.out.println("Printing the data that has upcoming birthdays");

        bg.upComingBirthday();


    }
}