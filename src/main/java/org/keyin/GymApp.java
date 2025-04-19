package org.keyin;

import org.keyin.memberships.Membership;
import org.keyin.workoutclasses.WorkoutClass;
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
            System.out.println("1. View all workout classes");
            System.out.println("2. Purchase a membership");
            System.out.println("3. View my total expense");
            System.out.println("4. Back to main menu");
            System.out.print("Please enter your choice: ");

            while (!scanner.hasNextInt()) {
                System.out.println("Input error, please enter a valid number!");
                scanner.next();
            }

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    viewWorkoutClasses(workoutService);
                    break;
                case 2:
                    purchaseMembership(scanner, user, membershipService);
                    break;
                case 3:
                    viewMemberExpenses(user, membershipService);
                    break;
                case 4:
                    System.out.println("Returning back to main menu");
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        } while (choice != 4);
    }

// Method to handle membership purchase
    private static void purchaseMembership(Scanner scanner, User user, MembershipService membershipService) {
        System.out.println("\n=== Purchase a Membership ===");
        System.out.print("Enter membership type (e.g., Basic, Premium): ");
        String membershipType = scanner.nextLine();

        System.out.print("Enter membership price: ");
        double membershipPrice = scanner.nextDouble();

        System.out.print("Enter membership duration (in months): ");
        int durationMonths = scanner.nextInt();
        scanner.nextLine(); 

// Create a new membership object
        Membership membership = new Membership(membershipType, membershipPrice, durationMonths);

        try {
// Add the membership and associate it with the current user
            membershipService.addMembership(membership, user.getUserId());
            System.out.println("Membership purchased successfully!");
        } catch (SQLException e) {
            System.out.println("Error purchasing membership: " + e.getMessage());
        }
    }

    private static void viewWorkoutClasses(WorkoutClassService workoutService) {
        try {
            var classes = workoutService.getAllWorkoutClasses();
            for (WorkoutClass wc : classes) {
                System.out.println("Name of class: " + wc.getClassName());
                System.out.println("Name of Trainer: " + wc.getTrainerName());
                System.out.println("Duration of Workout: " + wc.getDurationMinutes() + " mins");
                System.out.println("Schedule: " + wc.getSchedule());
                System.out.println("============================");
            }
        } catch (SQLException e) {
            System.out.println("Error getting workout classes: " + e.getMessage());
        }
    }

// Trainer menu
    private static void showTrainerMenu(Scanner scanner, User user, UserService userService, WorkoutClassService workoutService) {
        int choice;

        do {
            System.out.println("\n=== Trainer Menu ===");
            System.out.println("1. Add a workout class");
            System.out.println("2. Update a workout class");
            System.out.println("3. Remove a workout class");
            System.out.println("4. Back to main menu");
            System.out.print("Enter your choice: ");

            while (!scanner.hasNextInt()) {
                System.out.println("Input Error, please enter valid number!");
                scanner.next();
            }

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addWorkoutClass(scanner, workoutService);
                    break;
                case 2:
                    updateWorkoutClass(scanner, workoutService);
                    break;
                case 3:
                    deleteWorkoutClass(scanner, workoutService);
                    break;
                case 4:
                    System.out.println("Going back to main menu");
                    break;
                default:
                    System.out.println("Invalid choice");    
            }
        } while (choice != 4);
    }

    private static void updateWorkoutClass (Scanner scanner, WorkoutClassService workoutService) {
        System.out.print("Enter class ID to update: ");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter new class name: ");
        String className = scanner.nextLine();
        System.out.print("Enter new trainer name: ");
        String trainerName = scanner.nextLine(); 
        System.out.print("Enter new duration of workout (mins): ");
        int duration = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter new schedule (Ex: Mon/Wed/Fri 7:00AM): ");
        String schedule = scanner.nextLine();

        WorkoutClass wc = new WorkoutClass(id, className, trainerName, duration, schedule);

        try {
            workoutService.updateWorkoutClass(wc);
            System.out.println("Workout class updated successfully!");
        } catch (SQLException e) {
            System.out.println("Error updating workout class: " + e.getMessage());
        }
    }

// Admin menu    
    private static void showAdminMenu(Scanner scanner, User user, UserService userService, MembershipService membershipService, WorkoutClassService workoutService) {
        int choice;

        do {
            System.out.println("\n=== Admin Menu ===");
            System.out.println("1. View all users");
            System.out.println("2. View total revenue from memberships");
            System.out.println("3. Delete a member");
            System.out.println("4. Back to main menu");
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
                    viewTotalRevenue(membershipService);  
                    break;
                case 3:
                    deleteUser (scanner, userService);
                    break;
                case 4:
                    System.out.println("Returning to main menu...");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 4);
    }

    private static void viewTotalRevenue(MembershipService membershipService) {
        try {
            double totalRevenue = membershipService.getTotalRevenue();
            System.out.println("Total Revenue from Memberships: $" + totalRevenue);
        } catch (SQLException e) {
            System.out.println("Error retrieving total revenue: " + e.getMessage());
        }
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

    private static void deleteUser( Scanner scanner, UserService userService) {
        System.out.print("Enter username of memeber you wish to delete: ");
        String username = scanner.nextLine();

        try {
            userService.deleteUser(username);
            System.out.println("Member '" + username + "' deleted successfully!");
        } catch (SQLException e) {
            System.out.println("Erro deleting Member: " + e.getMessage());
        }
    }

// Adding a new user
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
// Add workoutclass
    private static void addWorkoutClass(Scanner scanner, WorkoutClassService workoutService) {
        System.out.print("Enter name of class: ");
        String className = scanner.nextLine();
        System.out.print("Enter name of trainer: ");
        String trainerName = scanner.nextLine();
        System.out.print("Enter duration of workout in minutes: ");
        int duration = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter schedule: ");
        String schedule = scanner.nextLine();

        WorkoutClass workoutClass = new WorkoutClass(className, trainerName, duration, schedule);

        try {
            workoutService.addWorkoutClass(workoutClass);
            System.out.println("Your workout class has been successfully added!");
        } catch (SQLException e) {
            System.out.println("Error when adding workout class, please try again: " + e.getMessage());
        }
    }
// Delete Workoutclass
    private static void deleteWorkoutClass(Scanner scanner, WorkoutClassService workoutService) {
        System.out.print("Delete Selected class ID: ");
        int id = Integer.parseInt(scanner.nextLine());
        try {
            workoutService.deleteWorkoutClass(id);
            System.out.println("Workout class deleted successfully!");
        } catch (SQLException e) {
            System.out.println("Error when attempting to delete class: " + e.getMessage());
        }
    }
}




