import java.io.File;
import java.io.*;
import java.util.Scanner;
import java.util.InputMismatchException;

//Class defines the structure a behaviour of Objects
public class Main {
    public static void main(String[] args) {
        //An object is an instance of a class and
        // An instance is a particular occurrence of an object.
        Main main = new Main();
        Scanner console = new Scanner(System.in);

        while (true) {
            System.out.println("\nCar Rental System Menu:");
            System.out.println("1.  Add a car");
            System.out.println("2.  All Cars");
            System.out.println("3.  Availability of car for rent");
            System.out.println("4.  Delete a car");
            System.out.println("5.  Rent a car");
            System.out.println("6.  Add customers");
            System.out.println("7.  All customers");
            System.out.println("8.  Customer rental details");
            System.out.println("9.  Clear customers rent");
            System.out.println("10. Remove customer");
            System.out.println("11. Show all rents");
            System.out.println("12. Show all customers details");
            System.out.println("13. Rent time");
            System.out.println("14. Exit");

            System.out.print("Enter your choice: ");
            int choice = console.nextInt();

            switch (choice) {
                case 1:
                    main.addCar();
                    break;
                case 2:
                    main.showAllCars();
                    break;
                case 3:
                    main.checkCarAvailability();
                    break;
                case 4:
                    main.deleteCar();
                    break;
                case 5:
                    main.rentCar();
                    break;
                case 6:
                    main.addCustomer();
                    break;
                case 7:
                    main.showAllCustomers();
                    break;
                case 8:
                    main.showCustomerRentalDetails();
                    break;
                case 9:
                    main.clearCustomerRent();
                    break;
                case 10:
                    main.removeCustomer();
                    break;
                case 11:
                    main.showAllRents();
                    break;
                case 12:
                    main.showAllCustomersDetails();
                    break;
                case 13:
                    main.showRentTime();
                    break;
                case 14:
                    System.out.println("Exiting Car Rental System.");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }
    File CAR_FILE = new File("cars.txt");
    File CUSTOMER_FILE = new File("customers.txt");
    File RENT_FILE = new File("rents.txt");

    // Performs some operation on the instance variables of the class and doesn't return any result.
    public void addCar() {
        Scanner console = new Scanner(System.in);
        System.out.print("Enter car model: ");
        String model = console.next();
        System.out.print("Enter car seater (1,2..etc) : ");
        int seater = console.nextInt();
        System.out.print("Enter car color: ");
        String color = console.next();

    // file already exists, new data will be appended to the end of the file rather than overwriting its content.
        try (PrintWriter writer = new PrintWriter(new FileWriter(CAR_FILE, true))) {
            writer.println(model + "," + seater + "," + color + ",available\n");
            System.out.println("Car added successfully!");
        }
        catch (InputMismatchException | IOException ex) {
            System.out.println("Error adding car");
        }
    }
    public void showAllCars() {
        //Buffered Reader it reads chunks of data from the file into memory and then processes the data
            try (BufferedReader reader = new BufferedReader(new FileReader(CAR_FILE))) {
                String line;
                System.out.println("All Cars:");
                while ((line = reader.readLine()) != null) {
                    String[] carData = line.split(",");
                    if(carData.length >=4){
                        String availability = carData[3].equals("available")? "Available" : "Rented";
                        System.out.println("Model: " + carData[0] + ", Seater: " + carData[1] +
                                ", Color: " + carData[2] + ", Availability: " + availability);
                    }
                }
            }
            catch (InputMismatchException | IOException ex) {
                System.out.println("Error showing car details");
            }
        }
        public void checkCarAvailability() {
        System.out.print("Enter car model to check availability: ");
        Scanner console = new Scanner(System.in);
        String checkModel = console.next();

        //Buffered Reader it reads chunks of data from the file into memory and then processes the data
        try (BufferedReader reader = new BufferedReader(new FileReader(CAR_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] carData = line.split(",");
                if (carData[0].equals(checkModel) && carData[3].equals("available")) {
                    System.out.println("Car is available for rent.");
                }
            }
            System.out.println("Car not found or not available for rent.");
        }
        catch (InputMismatchException | IOException ex) {
            System.out.println("Error checking car availability");
        }
    }
    public void deleteCar() {
            System.out.print("Enter car model to delete: ");
            Scanner console = new Scanner(System.in);
            String deleteModel = console.next();

            //Buffered Reader it reads chunks of data from the file into memory and then processes the data
            try (BufferedReader reader = new BufferedReader(new FileReader(CAR_FILE));
                 PrintWriter writer = new PrintWriter(new FileWriter(CAR_FILE))) {

                String line;
                while ((line = reader.readLine()) != null) {
                    if (!line.equals(deleteModel)) {
                        writer.println(line + "\n");
                    }
                }
                System.out.println("Car deleted successfully!");
            }
            catch (InputMismatchException | IOException ex) {
                System.out.println("Error deleting car");
            }
        }
    public void rentCar() {
        showAllCars();
        Scanner console = new Scanner(System.in);
        System.out.println("Do you want to book a car from these?");
        String RentApproval = console.next();

        if(RentApproval.equals("yes")){
            System.out.print("Enter car model to rent: ");
            String rentCarModel = console.next();

            System.out.print("Enter customer name: ");
            String customerName = console.next();

            System.out.print("Enter customer contact number without any spaces: ");
            long CustomerNumber = console.nextLong();

            System.out.print("Enter customer CNIC without dashes: ");
            long newCustomerCNIC = console.nextLong();

            System.out.print("Enter Card Number (without spaces) to process transaction: ");
            long cardNumber = console.nextLong();

            System.out.print("Enter Duration to rent (in hours): ");
            int rentCarHours = console.nextInt();

            double hourlyRate = 10.0;
            double totalPayment = rentCarHours * hourlyRate;

            System.out.println("Payment: $" + totalPayment);

            if (String.valueOf(cardNumber).length() >= 16) {
                System.out.println("Do you want to continue?");
                String Continue = console.next();

                if(Continue.equals("yes")){
                    System.out.println("Transaction has been Processed");
                }
                else{
                    System.out.println("System Exiting");
                    System.exit(0);
                }
            }
            else{
                System.out.println("Enter valid card Number");
            }

            //Buffered Reader it reads chunks of data from the file into memory and then processes the data
                try (PrintWriter rentWriter = new PrintWriter(new FileWriter(RENT_FILE, true));
                 BufferedReader carReader = new BufferedReader(new FileReader(CAR_FILE));
                 PrintWriter carTempWriter = new PrintWriter(new FileWriter(CAR_FILE + ".temp", true))) {

                rentWriter.println(customerName + "," + rentCarModel + "," + rentCarHours + "," + totalPayment + "\n");
                System.out.println("Car rented successfully!");

                // Update car availability in the temporary cars.txt file
                String line;
                while ((line = carReader.readLine()) != null) {
                    String[] carData = line.split(",");
                    if (carData[0].equals(rentCarModel) && carData[3].equals("available")) {
                        carTempWriter.println(carData[0] + "," + carData[1] + "," + carData[2] + ",rented\n");
                    } else {
                        carTempWriter.println(line + "\n");
                    }
                }
            } catch (InputMismatchException | IOException ex) {
                System.out.println("Error renting car");
            }

            File oldFile = new File(CAR_FILE.getPath());
            if (oldFile.delete()) {
                System.out.println("Old file deleted successfully.");
            } else {
                System.out.println("Error deleting old file.");
            }
            File tempFile = new File(CAR_FILE + ".temp");
            if (tempFile.renameTo(oldFile)) {
                System.out.println("File replaced successfully.");
            } else {
                System.out.println("Error replacing file.");
            }

        }
        else{
            System.exit(0);
        }
    }
    public void addCustomer() {
        Scanner console = new Scanner(System.in);
        System.out.print("Enter new customer name: ");
        String newCustomerName = console.next();
        System.out.print("Enter customer contact number without any spaces: ");
        long newCustomerNumber = console.nextLong();


        // file already exists, new data will be appended to the end of the file rather than overwriting its content.
        try (PrintWriter writer = new PrintWriter(new FileWriter(CUSTOMER_FILE, true))) {
            writer.println(newCustomerName + "\n");
            System.out.println("Customer added successfully!");
        }
        catch (InputMismatchException | IOException ex) {
            System.out.println("Error adding customer");
        }
    }
    public void showAllCustomers() {
        //Buffered Reader it reads chunks of data from the file into memory and then processes the data
        try (BufferedReader reader = new BufferedReader(new FileReader(CUSTOMER_FILE))) {
            System.out.println("Customer Details:");
            String line;
            while ((line = reader.readLine()) != null) {
                String[] customerData = line.split(",");
                System.out.println("Name: " + customerData[0]);
            }
        }
        catch (InputMismatchException | IOException x) {
            System.out.println("Error showing customer details");
        }
    }
    public void showCustomerRentalDetails() {
        Scanner console = new Scanner(System.in);
        System.out.print("Enter customer name to view rental details: ");
        String customerName = console.next();

        //Buffered Reader it reads chunks of data from the file into memory and then processes the data
        try (BufferedReader reader = new BufferedReader(new FileReader(RENT_FILE))) {
            String line;
            boolean found = false;
            while ((line = reader.readLine()) != null) {
                String[] rentData = line.split(",");
                if (rentData[0].equals(customerName)) {
                    found = true;
                    System.out.println("Rented Car: " + rentData[1] + ", Rent Time: " + rentData[2] + " hours");
                    break;
                }
            }
            if (!found) {
                System.out.println("Customer not found.");
            }
        }
        catch (InputMismatchException | IOException ex) {
            System.out.println("Error showing customer rental details");
        }
    }
    public void clearCustomerRent() {
        Scanner console = new Scanner(System.in);
        System.out.print("Enter customer name to clear rent: ");
        String customerName = console.next();

        ///Buffered Reader it reads chunks of data from the file into memory and then processes the data
        // file already exists, new data will be appended to the end of the file rather than overwriting its content.
        try (BufferedReader reader = new BufferedReader(new FileReader(RENT_FILE));
             PrintWriter writer = new PrintWriter(new FileWriter(RENT_FILE, true))) {

            String line;
            boolean found = false;

            while ((line = reader.readLine()) != null) {
                String[] rentData = line.split(",");
                if (rentData[0].equals(customerName)) {
                    found = true;
                    System.out.println("Customer rent cleared successfully!");
                } else {
                    writer.println(line + "\n");
                }
            }
            if (!found) {
                System.out.println("Customer not found.");
            }
        }
        catch (InputMismatchException | IOException ex) {
            System.out.println("Error clearing customer rent");
        }
        // Replacing the original file with the temporary file
        File originalFile = new File(RENT_FILE.getPath());
        File tempFile = new File(RENT_FILE.getPath() + ".txt");
        tempFile.renameTo(originalFile);

    }
    public void removeCustomer() {
        Scanner console = new Scanner(System.in);
        System.out.print("Enter customer name to remove: ");
        String customerName = console.next();

        //Buffered Reader it reads chunks of data from the file into memory and then processes the data
        try (BufferedReader reader = new BufferedReader(new FileReader(CUSTOMER_FILE));
             PrintWriter writer = new PrintWriter(new FileWriter(CUSTOMER_FILE + ".tmp"))) {

            String line;
            boolean found = false;

            while ((line = reader.readLine()) != null) {
                String[] customerData = line.split(",");
                if (customerData[0].equals(customerName)) {
                    found = true;
                    System.out.println("Customer removed successfully!");
                } else {
                    writer.println(line + "\n");
                }
            }
            if (!found) {
                System.out.println("Customer not found.");
            }
        }
        catch (InputMismatchException | IOException ex) {
            System.out.println("Error removing customer");
        }
        File originalFile = CUSTOMER_FILE;
        File tempFile = new File(CUSTOMER_FILE + ".tmp");

        if (tempFile.renameTo(originalFile)) {
            System.out.println("File replaced successfully.");
        } else {
            System.out.println("Error replacing file.");
        }
    }
    public void showAllRents() {
        //Buffered Reader it reads chunks of data from the file into memory and then processes the data
        try (BufferedReader reader = new BufferedReader(new FileReader(RENT_FILE))) {
            String line;
            System.out.println("All Rents:");

            while ((line = reader.readLine()) != null) {
                String[] rentData = line.split(",");
                System.out.println("Customer: " + rentData[0] + " Car rented:" + rentData[1] + ", Rent Time: " + rentData[2] + " hours");
            }
        }
        catch (InputMismatchException | IOException | ArrayIndexOutOfBoundsException ex) {
            System.out.println("Error showing all rents");
        }
    }
    public void showAllCustomersDetails() {
        //Buffered Reader it reads chunks of data from the file into memory and then processes the data
        try (BufferedReader reader = new BufferedReader(new FileReader(CUSTOMER_FILE))) {
            String line;
            System.out.println("Customer Details:");
            while ((line = reader.readLine()) != null) {
                String[] customerData = line.split(",");
                System.out.println("Name: " + customerData[0] + ", Rented Car: " + customerData[1]);
            }
        }
        catch (InputMismatchException | IOException ex) {
            System.out.println("Error showing customer details");
        }
    }
    public void showRentTime() {
        Scanner console = new Scanner(System.in);
        System.out.print("Enter customer name to check rent time: ");
        String customerName = console.next();

        ////Buffered Reader it reads chunks of data from the file into memory and then processes the data
        try (BufferedReader reader = new BufferedReader(new FileReader(RENT_FILE))) {
            String line;
            boolean found = false;
            while ((line = reader.readLine()) != null) {
                String[] rentData = line.split(",");
                if (rentData[0].equals(customerName)) {
                    found = true;
                    System.out.println("Rent Time for " + customerName + ": " + rentData[2] + " hours");
                    break;
                }
            }
            if (!found) {
                System.out.println("Customer not found.");
            }
        }
        catch (InputMismatchException | IOException ex) {
            System.out.println("Error showing rent time");
        }
    }
}
