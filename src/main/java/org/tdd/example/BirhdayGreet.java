package org.tdd.example;

import com.sun.jdi.connect.Transport;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.PasswordAuthentication;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class BirhdayGreet {

    private static final String FROM_EMAIL = "your_email@example.com"; // Replace with your email address
    private static final String PASSWORD = "your_password"; // Replace with your email password
    private static final String SMTP_HOST = "smtp.example.com"; // Replace with your SMTP host

    static String filePath = "files/birthdays.txt";

    public static void info() {
        // Adjust the file path as needed

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean firstLine = true; // Flag to skip the header line

            // Map to store individuals by their date of birth
            while ((line = br.readLine()) != null) {
                if (firstLine) {
                    firstLine = false; // Skip the header line
                    continue;
                }
                // Print each line from the file
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Function to get the data of individuals with upcoming birthdays
    public static void upComingBirthday() {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean firstLine = true; // Flag to skip the header line

            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date currentDate = new Date(); // Get current date

            Calendar currentCalendar = Calendar.getInstance();
            currentCalendar.setTime(currentDate);

            // Get tomorrow's date
            Calendar tomorrowCalendar = Calendar.getInstance();
            tomorrowCalendar.add(Calendar.DAY_OF_YEAR, 1);

            // Map to store information of individuals whose birthday is tomorrow
            Map<String, String> tomorrowBirthdayMap = new HashMap<>();

            while ((line = br.readLine()) != null) {
                if (firstLine) {
                    firstLine = false; // Skip the header line
                    continue;
                }
                String[] parts = line.trim().split(", ");
                if (parts.length == 4) {
                    String name = parts[1] + " " + parts[0]; // Concatenate first name and last name
                    String dobString = parts[2];

                    Date dobDate = dateFormat.parse(dobString);

                    Calendar dobCalendar = Calendar.getInstance();
                    dobCalendar.setTime(dobDate);

                    // Set the year of the birthday to the current year
                    dobCalendar.set(Calendar.YEAR, currentCalendar.get(Calendar.YEAR));

                    // Check if the birthday is tomorrow
                    if (dobCalendar.get(Calendar.MONTH) == tomorrowCalendar.get(Calendar.MONTH)
                            && dobCalendar.get(Calendar.DAY_OF_MONTH) == tomorrowCalendar.get(Calendar.DAY_OF_MONTH)) {
                        tomorrowBirthdayMap.put(name, line);
                        System.out.println("Tomorrow is " + name + "'s birthday!");
                        System.out.println("Name: " + name);
                        System.out.println("DOB: " + dobString);
                        System.out.println("Email: " + parts[3]);
                        System.out.println(); // Print an empty line for readability

                        // Send birthday email
//                        sendBirthdayEmail(parts[3], name);
                    }
                } else {
                    System.out.println("Invalid data format: " + line);
                }
            }

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

}

//    public static void sendBirthdayEmail(String recipientEmail, String firstName) {
//        String subject = "Happy birthday!";
//        String body = "Happy birthday, dear " + firstName + "!\n\nBest wishes on your special day!";
//
//        Properties properties = new Properties();
//        properties.put("mail.smtp.host", SMTP_HOST);
//        properties.put("mail.smtp.auth", "true");
//        properties.put("mail.smtp.starttls.enable", "true");
//        properties.put("mail.smtp.port", "587"); // Your SMTP port
//
//        Session session = Session.getDefaultInstance(properties, new javax.mail.Authenticator() {
//            protected PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication(FROM_EMAIL, PASSWORD);
//            }
//        });
//
//        try {
//            Message message = new MimeMessage(session);
//            message.setFrom(new InternetAddress(FROM_EMAIL));
//            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
//            message.setSubject(subject);
//            message.setText(body);
//            Transport.send(message);
//            System.out.println("Birthday email sent to " + recipientEmail);
//        } catch (MessagingException e) {
//            e.printStackTrace();
//        }









