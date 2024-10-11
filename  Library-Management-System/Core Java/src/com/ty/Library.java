package com.ty;
import java.io.*;
import java.util.*;

public class Library {
    private List<Book> books;
    private Map<String, List<String>> borrowHistory;
    private static final String BOOK_FILE = "books.dat";
    private static final String HISTORY_FILE = "history.dat";
    
    public Library() {
        books = loadBooks();
        borrowHistory = loadHistory();
    }

    public void addBook(Book book) {
        books.add(book);
        saveBooks();
        System.out.println("Book added: " + book.getTitle());
    }

    public void viewBooks() {
        if (books.isEmpty()) {
            System.out.println("No books in the library.");
        } else {
            books.forEach(System.out::println);
        }
    }

    public void borrowBook(String title, User user) {
        Book book = findBookByTitle(title);
        if (book != null && !book.isBorrowed()) {
            book.borrowBook();
            saveBooks();
            addToBorrowHistory(user.getUserId(), book.getTitle());
            System.out.println(user.getName() + " borrowed " + title);
        } else {
            System.out.println("Book is either unavailable or already borrowed.");
        }
    }

    public void returnBook(String title, User user) {
        Book book = findBookByTitle(title);
        if (book != null && book.isBorrowed()) {
            book.returnBook();
            saveBooks();
            System.out.println(user.getName() + " returned " + title);
        } else {
            System.out.println("Invalid return operation. Book is not borrowed.");
        }
    }

    public void checkBookAvailability(String title) {
        Book book = findBookByTitle(title);
        if (book != null) {
            System.out.println(book);
        } else {
            System.out.println("Book not found in the library.");
        }
    }

    public void viewBorrowHistory(String userId) {
        List<String> history = borrowHistory.get(userId);
        if (history != null && !history.isEmpty()) {
            System.out.println("Borrow history for User ID: " + userId);
            history.forEach(System.out::println);
        } else {
            System.out.println("No borrow history found for User ID: " + userId);
        }
    }

    private Book findBookByTitle(String title) {
        return books.stream()
                .filter(book -> book.getTitle().equalsIgnoreCase(title))
                .findFirst()
                .orElse(null);
    }

    private void addToBorrowHistory(String userId, String bookTitle) {
        borrowHistory.computeIfAbsent(userId, k -> new ArrayList<>()).add(bookTitle);
        saveHistory();
    }

    // File handling methods to save and load books
    @SuppressWarnings("unchecked")
    private List<Book> loadBooks() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(BOOK_FILE))) {
            return (List<Book>) ois.readObject();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    private void saveBooks() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(BOOK_FILE))) {
            oos.writeObject(books);
        } catch (IOException e) {
            System.out.println("Error saving book data.");
        }
    }

    @SuppressWarnings("unchecked")
    private Map<String, List<String>> loadHistory() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(HISTORY_FILE))) {
            return (Map<String, List<String>>) ois.readObject();
        } catch (Exception e) {
            return new HashMap<>();
        }
    }

    private void saveHistory() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(HISTORY_FILE))) {
            oos.writeObject(borrowHistory);
        } catch (IOException e) {
            System.out.println("Error saving history data.");
        }
    }
}
