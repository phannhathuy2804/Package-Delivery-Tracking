package cmpt213.assignment1.packagedeliveriestracker;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This class prints the text-based menu and handle user input
 * @author Nhat Huy Phan (James)
 */
public class Menu {
    String title = "My package Deliveries Tracker";
    List<String> options = new ArrayList<>();


    public Menu() {
        options.add("List all packages");
        options.add("Add a package");
        options.add("Remove a package");
        options.add("List overdue packages");
        options.add("List upcoming packages");
        options.add("Mark package as delivered");
        options.add("Exit");
    }

    /**
     * Print the title and options of the menu
     */
    public void printMenu(){
        System.out.println("");
        for (int i = 0; i < title.length() + 4; i++){
            System.out.print("#");
        }
        System.out.println("");
        System.out.println("# " + title + " #");
        for (int i = 0; i < title.length() + 4; i++){
            System.out.print("#");
        }
        System.out.println("");

        LocalDateTime today = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        System.out.println("Today is "+ dateTimeFormatter.format(today));

        for(int i =0; i < options.size(); i++){
            System.out.println(i+1 + ": " + options.get(i));
        }
    }

    /**
     * Handle input to choose the right option and check invalid input
     * @return the number that associates with an option
     */
    public int getInputOption(){
        Scanner scanner = new Scanner(System.in);
        boolean invalidInput = true;
        int input = 0;
        while (invalidInput) {
            try {
                System.out.print("Choose an option by entering 1-7 ");
                input = scanner.nextInt();
                if (input < 1 || input > 7){
                    System.out.print("Invalid selection. Enter a number between 1 and 7");
                    continue;
                }
                invalidInput = false;
            } catch (Exception e) {
                System.out.println("Invalid input. Input must be a number between 1 and 7");
                break;
            }
        }
        return input;
    }

    /**
     * Get and check the input for creating new package
     * @return A new package that will be added to the package list
     */
    public Package getInputForNewPackage(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the name of the package: ");
        String packageName = scanner.nextLine();
        System.out.print("Enter the notes of the package: ");
        String packageNotes = scanner.nextLine();
        System.out.print("Enter the price of the package (in dollar): ");
        int packagePrice = scanner.nextInt();
        System.out.print("Enter the weight of the package (in kg): ");
        int packageWeight = scanner.nextInt();
        int packageExpectedYear = 0;
        int packageExpectedMonth = 0;
        int packageExpectedDay = 0;
        boolean invalidDate = true;
        while (invalidDate){
            try{
                System.out.print("Enter the year of the expected delivery date: ");
                packageExpectedYear = scanner.nextInt();
                System.out.print("Enter the month of the expected delivery date: ");
                packageExpectedMonth = scanner.nextInt();
                System.out.print("Enter the day of the expected delivery date: ");
                packageExpectedDay = scanner.nextInt();
                LocalDate localDate = LocalDate.of(packageExpectedYear, packageExpectedMonth, packageExpectedDay);
                invalidDate = false;
            } catch (DateTimeException e){
                System.out.println("Error: this date does not exist");
            }
        }
        int packageExpectedHour = 0;
        invalidDate = true;
        while(invalidDate) {
            System.out.print("Enter the hour of the expected delivery date (0-23): ");
            packageExpectedHour = scanner.nextInt();
            if (packageExpectedHour <0 || packageExpectedHour >23){
                System.out.println("Error: this time does not exist");
                continue;
            }
            invalidDate = false;
        }

        int packageExpectedMinute = 0;
        invalidDate = true;
        while(invalidDate) {
            System.out.print("Enter the minute of the expected delivery date (0-59): ");
            packageExpectedMinute = scanner.nextInt();
            if (packageExpectedMinute <0 || packageExpectedMinute >59){
                System.out.println("Error: this time does not exist");
                continue;
            }
            invalidDate = false;
        }
        LocalDateTime expectedDeliveryTime = LocalDateTime.of(packageExpectedYear, packageExpectedMonth, packageExpectedDay, packageExpectedHour, packageExpectedMinute);

        Package newPackage = new Package(packageName, packageNotes, packagePrice, packageWeight, expectedDeliveryTime);
        return newPackage;
    }




}
