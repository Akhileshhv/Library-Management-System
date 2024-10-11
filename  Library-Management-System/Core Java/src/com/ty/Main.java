package com.ty;

import java.util.Scanner;

public class Main {
    private static Library library = new Library();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\nLibrary Management System");
            System.out.println("1. Add Book");
            System.out.println("2. View Books");
            System.out.println("3. Borrow Book");
            System.out.println("4. Return Book");
            System.out.println("5. Check Book Availability");
            System.out.println("6. View Borrow History");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addBook();
                    break;
                case 2:
                    library.viewBooks();
                    break;
                case 3:
                    borrowBook();
                    break;
                case 4:
                    returnBook();
                    break;
                case 5:
                    checkBookAvailability();
                    break;
                case 6:
                    viewBorrowHistory();
                    break;
                case 7:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private static void addBook() {
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();
        System.out.print("Enter book author: ");
        String author = scanner.nextLine();
        library.addBook(new Book(title, author));
    }

    private static void borrowBook() {
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        System.out.print("Enter your user ID: ");
        String userId = scanner.nextLine();
        User user = new User(name, userId);
        System.out.print("Enter book title to borrow: ");
        String title = scanner.nextLine();
        library.borrowBook(title, user);
    }

    private static void returnBook() {
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        System.out.print("Enter your user ID: ");
        String userId = scanner.nextLine();
        User user = new User(name, userId);
        System.out.print("Enter book title to return: ");
        String title = scanner.nextLine();
        library.returnBook(title, user);
    }

    private static void checkBookAvailability() {
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();
        library.checkBookAvailability(title);
    }

    private static void viewBorrowHistory() {
        System.out.print("Enter user ID: ");
        String userId = scanner.nextLine();
        library.viewBorrowHistory(userId);
    }
}
