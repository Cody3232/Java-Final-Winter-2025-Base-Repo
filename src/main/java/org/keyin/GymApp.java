package org.keyin;


import org.keyin.memberships.MembershipService;
import org.keyin.user.UserService;
import org.keyin.workoutclasses.WorkoutClassService;
import org.keyin.user.User;
import java.util.List;
import java.sql.SQLException;
import java.util.Scanner;

public class GymApp {
    public static void main(String[] args) throws SQLException {
// Initialize services
        UserService userService = new UserService();
        MembershipService membershipService = new MembershipService();
        WorkoutClassService workoutService = new WorkoutClassService();

// Scanner for user input
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n=== Gym Management System ===");
            System.out.println("1. Add a new user");
            System.out.println("2. Login as a user");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

// Validate input
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input! Please enter a number.");
                scanner.next();
            }

            choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    addNewUser(scanner, userService);
                    break;
                case 2:
                    logInAsUser(scanner, userService, membershipService, workoutService);
                    break;
                case 3:
                    System.out.println("Exiting the program...");
                    break;
                default:
                    System.out.println("Invalid choice! Please select a valid option.");
            }
        } while (choice != 3);

        scanner.close();
    }

    private static void logInAsUser(Scanner scanner, UserService userService, MembershipService membershipService, WorkoutClassService workoutService) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        try {
            User user = userService.loginForUser(username, password);
            if (user != null) {
                System.out.println("Login Successful! Welcome " + user.getUserName());
                switch (user.getUserRole().toLowerCase()) {
                    case "admin":
                        showAdminMenu(scanner, user, userService, membershipService, workoutService);
                        break;

                    case "trainer":
                        showTrainerMenu(scanner, user, userService, workoutService);
                        break;
                    
                    case "member":
                        showMemberMenu(scanner, user, userService, membershipService, workoutService);
                        break;
                    
                    default:

                        break;
                }
            } else {
                System.out.println("Login Failed! Invalid credentials.");
            }
        } catch (SQLException e) {
            System.out.println("An error occurred while logging in.");
            e.printStackTrace();
        }
    }

// Member menu
    private static void showMemberMenu(Scanner scanner, User user, UserService userService, MembershipService membershipService, WorkoutClassService workoutService) {
        int choice;

        do {
            System.out.println("\n Member Menu ");
            System.out.println("1. View all workoutclasses");
            System.out.println("2. Back to main menu");
            System.out.print("Please enter your choice: ");

            while (!scanner.hasNextInt()) {
                System.out.println("Input error, please enter a valid number!");
                scanner.next();
            }

            choice = scanner.nextInt();
            scanner.nextLine();
            
            switch (choice) {
                case 1:
                    viewWorkoutClasses (workoutService);
                    break;
                case 2:
                    System.out.println("Returning back to main menu");
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        } while (choice !=2);
    }

    private static void viewWorkoutClasses (WorkoutClassService workoutService) {
        try {
            var classes = workoutService.getAllWorkoutClasses();
            for (WorkoutClass wc : classes) {
                System.out.println("Name of class: " + wc.getClassName());
                System.out.println("Name of Trainer: " + wc.getTrainerName());
                System.out.println("Duration of Workout: " + wc.getDurationMinutes() + " minds");
                System.out.println("Schedule: " + wc.getSchedule());
                System.out.println("============================");
            }
        } catch (SQLException e) {
            System.out.println("Error getting workout classes: " +  e.getMessage());
        }
    }
// Trainer menu
    private static void showTrainerMenu(Scanner scanner, User user, UserService userService, WorkoutClassService workoutService) {
        int choice;

        do {
            System.out.println("\n=== Trainer Menu===");
            System.out.println("1. Add a workou class");
            System.out.println("2. Back to main menu");
            System.out.println("Enter your choice: ");

            while (!scanner.hasNextInt()) {
                System.out.println("Input Error, please enter valid number!");
                scanner.next();
            }

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addWorkoutClass (scanner, workoutService);
                    break;
                case 2:
                    System.out.println("Going back to main menu");
                    break;
                default:
                    System.out.println("Invalid choice");    
            }
        } while (choice !=2);
    }

// Admin menu    
    private static void showAdminMenu(Scanner scanner, User user, UserService userService, MembershipService membershipService, WorkoutClassService workoutService) {
        int choice;
    
        do {
            System.out.println("\n=== Admin Menu ===");
            System.out.println("1. View all users");
            System.out.println("2. Back to main menu");
            System.out.print("Enter your choice: ");
    
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input! Please enter a number.");
                scanner.next();
            }
    
            choice = scanner.nextInt();
            scanner.nextLine(); 
    
            switch (choice) {
                case 1:
                    viewAllUsers(userService);
                    break;
                case 2:
                    System.out.println("Returning to main menu...");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 2);
    }

    private static void viewAllUsers(UserService userService) {
        try {
            List<User> users = userService.getAllUsers();
            for (User user : users) {
                System.out.println("Username: " + user.getUserName());
                System.out.println("Role: " + user.getUserRole());
                System.out.println("---------------------------");
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving users: " + e.getMessage());
        }
    }

//adding a new user
    private static void addNewUser(Scanner scanner, UserService userService) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        System.out.print("Enter role (Admin/Trainer/Member): ");
        String role = scanner.nextLine();

        User user = new User(username, password, role);
        try {
            userService.addUser(user);
            System.out.println("User added successfully!");
        } catch (SQLException e) {
            System.out.println("Error adding user: " + e.getMessage());
        }
    }

    private static void addWorkoutClass (Scanner scanner, WorkoutClassService workoutService) {
        System.out.print("Enter name of class: ");
        String className = scanner.nextLine();
        System.out.print("Enter name of trainer: ");
        String trainerName = scanner.nextLine();
        System.out.print("Enter duration of workout in minutes: ");
        int duration = Integer.parseInt (scanner.nextLine());
        System.out.print ("Enter schedule: ");
        String schedule = scanner.nextLine();

        WorkoutClass workoutClass = new WorkoutClass(className, trainerName, duration, schedule);

        try {
            workoutService.addWorkoutClass(workoutClass);
            System.out.println("Your workout class has been successgully added!");
        } catch (SQLException e) {
            System.out.println("Error when adding workout class, please try again: " + e.getMessage());
        }
    }
}
