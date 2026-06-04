package co.edu.uniremignton.service;

import co.edu.uniremignton.model.Book;
import co.edu.uniremignton.model.Loan;
import co.edu.uniremignton.model.StateBook;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Library {
    private List<Book> books;
    private List<Loan> loans;


public Library() {
        this.books = new ArrayList<>();
        this.loans = new ArrayList<>();
    }

public boolean addBook(Book book) {
        if (findBookByIsbn(book.getIsbn()).isPresent()) {
            return false; // Evita duplicados
        }
        books.add(book);
        return true;
    }

public Optional<Book> findBookByIsbn(String isbn) {
        return books.stream()
                .filter(book -> book.getIsbn().equals(isbn))
                .findFirst();
    }

public List<Book> findBooksByTitle(String title) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(title.toLowerCase())) {
                result.add(book);
            }
        }
        return result;
    }

public List<Book> findBooksByAuthor(String author) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getAuthor().toLowerCase().contains(author.toLowerCase())) {
                result.add(book);
            }
        }
        return result;
    }

public List<Book> getAvailableBooks() {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.isAvailable()) {
                result.add(book);
            }
        }
        return result;
    }


public boolean borrowBook(String isbn, String borrowerName) {
        Optional<Book> optionalBook = findBookByIsbn(isbn);
        if (optionalBook.isEmpty()) {
            return false;
        }
        Book book = optionalBook.get();
        if (!book.isAvailable()) {
            return false;
        }
book.setState(StateBook.BORROWED);
        Loan loan = new Loan(isbn, book, borrowerName);
        loans.add(loan);
        return true;
    }

public boolean returnBook(String isbnLoand) {
        for (Loan loan : loans) {
            if (loan.getBook().getIsbn().equals(isbnLoand) && !loan.isReturned()) {
                loan.registerReturn();
                loan.getBook().setState(StateBook.AVAILABLE);
                return true;
            }
        }
        return false;
    }

public List<Loan> getActiveLoans() {
        List<Loan> result = new ArrayList<>();

        for (Loan loan : loans) {
            if (!loan.isReturned()) {
                result.add(loan);
            }
        }

        return result;
    }

public List<Loan> getBook() {
        return loans;
    }

public List<Book> getBooks() {
        return books;
    }
}