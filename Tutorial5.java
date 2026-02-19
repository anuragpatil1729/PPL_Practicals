import java.time.LocalDate;

import java.util.ArrayList;

import java.util.List;

import java.util.Scanner;

// Custom Exceptions

class BookNotFoundException extends Exception {

    public BookNotFoundException(String message) {

        super(message);

    }

}

class BookAlreadyCheckedOutException extends Exception {

    public BookAlreadyCheckedOutException(String message) {

        super(message);

    }

}

class InvalidReturnDateException extends Exception {

    public InvalidReturnDateException(String message) {

        super(message);

    }

}

class InvalidBorrowDaysException extends Exception {

    public InvalidBorrowDaysException(String message) {

        super(message);

    }

}

// Book Class

class Book {

    private String title;

    private boolean isCheckedOut;

    private LocalDate issueDate;

    private LocalDate dueDate;

    public Book(String title) {

        this.title = title;

        this.isCheckedOut = false;

    }

    public String getTitle() {

        return title;

    }

    public boolean isCheckedOut() {

        return isCheckedOut;

    }

    public LocalDate getIssueDate() {

        return issueDate;

    }

    public LocalDate getDueDate() {

        return dueDate;

    }

    public void checkOut(int days) throws InvalidBorrowDaysException {

        if (days <= 0) {

            throw new InvalidBorrowDaysException("Borrowing days cannot be zero or negative.");

        }

        this.issueDate = LocalDate.now();

        this.dueDate = issueDate.plusDays(days);

        this.isCheckedOut = true;

    }

    public void returnBook() {

        this.isCheckedOut = false;

        this.issueDate = null;

        this.dueDate = null;

    }

}

// Library Class

class Library {

    private List<Book> books = new ArrayList<>();

    public void addBook(String title) {

        books.add(new Book(title));

        System.out.println("Book added successfully!");

    }

    private Book findBook(String title) throws BookNotFoundException {

        for (Book book : books) {

            if (book.getTitle().equalsIgnoreCase(title)) {

                return book;

            }

        }

        throw new BookNotFoundException("Book not found: " + title);

    }

    public void searchBook(String title) throws BookNotFoundException {

        Book book = findBook(title);

        System.out.println("Book Found: " + book.getTitle());

        if (book.isCheckedOut()) {

            System.out.println("Status: Checked Out");

            System.out.println("Due Date: " + book.getDueDate());

        } else {

            System.out.println("Status: Available");

        }

    }

    public void checkOutBook(String title, int days)

            throws BookNotFoundException,

            BookAlreadyCheckedOutException,

            InvalidBorrowDaysException {

        Book book = findBook(title);

        if (book.isCheckedOut()) {

            throw new BookAlreadyCheckedOutException("Book already checked out.");

        }

        book.checkOut(days);

        System.out.println("Checked out successfully! Due date: " + book.getDueDate());

    }

    public void returnBook(String title)

            throws BookNotFoundException,

            InvalidReturnDateException {

        Book book = findBook(title);

        if (!book.isCheckedOut()) {

            System.out.println("Book was not checked out.");

            return;

        }

        LocalDate today = LocalDate.now();

        if (today.isAfter(book.getDueDate())) {

            throw new InvalidReturnDateException(

                    "Book returned late! Due date was: " + book.getDueDate());

        }

        book.returnBook();

        System.out.println("Book returned successfully.");

    }

}

// Main Class

public class Tutorial5 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Library library = new Library();

        int choice;

        do {

            System.out.println("\n===== Library Management System =====");

            System.out.println("1. Add Book");

            System.out.println("2. Search Book");

            System.out.println("3. Check Out Book");

            System.out.println("4. Return Book");

            System.out.println("5. Exit");

            System.out.print("Enter your choice: ");

            choice = sc.nextInt();

            sc.nextLine(); // clear buffer

            try {

                switch (choice) {

                    case 1:

                        System.out.print("Enter book title: ");

                        String titleToAdd = sc.nextLine();

                        library.addBook(titleToAdd);

                        break;

                    case 2:

                        System.out.print("Enter book title to search: ");

                        String searchTitle = sc.nextLine();

                        library.searchBook(searchTitle);

                        break;

                    case 3:

                        System.out.print("Enter book title to checkout: ");

                        String checkoutTitle = sc.nextLine();

                        System.out.print("Enter number of days: ");

                        int days = sc.nextInt();

                        library.checkOutBook(checkoutTitle, days);

                        break;

                    case 4:

                        System.out.print("Enter book title to return: ");

                        String returnTitle = sc.nextLine();

                        library.returnBook(returnTitle);

                        break;

                    case 5:

                        System.out.println("Exiting system...");

                        System.out.println("Dhanyawad");

                        break;

                    default:

                        System.out.println("Invalid choice!");

                }

            } catch (Exception e) {

                System.out.println("Error: " + e.getMessage());

            }

        } while (choice != 5);

        sc.close();

    }

}