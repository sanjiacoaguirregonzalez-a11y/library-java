package co.edu.uniremignton.service;

import co.edu.uniremignton.model.Book;
import co.edu.uniremignton.model.Loan;
import co.edu.uniremignton.model.StateBook;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Clase Library: Gestiona la lógica del sistema de biblioteca.
 * Permite agregar libros, realizar préstamos, devoluciones y consultarlos.
 */
public class Library {
    private List<Book> books;
    private List<Loan> loans;

    /**
     * Constructor: Inicializa las listas de libros y préstamos
     */
    public Library() {
        this.books = new ArrayList<>();
        this.loans = new ArrayList<>();
    }

    /**
     * Agrega un nuevo libro a la biblioteca
     */
    public void addBook(Book book) {
        if (book != null && !books.contains(book)) {
            books.add(book);
        }
    }

    /**
     * Elimina un libro de la biblioteca
     */
    public void removeBook(String isbn) {
        books.removeIf(book -> book.getIsbn().equals(isbn));
    }

    /**
     * Obtiene un libro por ISBN
     */
    public Optional<Book> getBook(String isbn) {
        return books.stream()
                .filter(book -> book.getIsbn().equals(isbn))
                .findFirst();
    }

    /**
     * Obtiene todos los libros disponibles
     */
    public List<Book> getAvailableBooks() {
        List<Book> available = new ArrayList<>();
        for (Book book : books) {
            if (book.isAvailable()) {
                available.add(book);
            }
        }
        return available;
    }

    /**
     * Obtiene todos los libros de la biblioteca
     */
    public List<Book> getAllBooks() {
        return new ArrayList<>(books);
    }

    /**
     * Realiza un préstamo de un libro
     */
    public boolean borrowBook(String isbn, String borrowername) {
        Optional<Book> bookOpt = getBook(isbn);
        if (bookOpt.isPresent() && bookOpt.get().isAvailable()) {
            Book book = bookOpt.get();
            book.setState(StateBook.BORROWED);

            String loanId = "LOAN-" + System.currentTimeMillis();
            Loan loan = new Loan(loanId, book, borrowername);
            loans.add(loan);
            return true;
        }
        return false;
    }

    /**
     * Registra la devolución de un libro
     */
    public boolean returnBook(String isbn) {
        Optional<Loan> loanOpt = loans.stream()
                .filter(loan -> loan.getBook().getIsbn().equals(isbn) && !loan.isReturned())
                .findFirst();

        if (loanOpt.isPresent()) {
            Loan loan = loanOpt.get();
            loan.registerReturn();
            loan.getBook().setState(StateBook.AVAILABLE);
            return true;
        }
        return false;
    }

    /**
     * Obtiene todos los préstamos activos
     */
    public List<Loan> getActiveLoans() {
        List<Loan> active = new ArrayList<>();
        for (Loan loan : loans) {
            if (!loan.isReturned()) {
                active.add(loan);
            }
        }
        return active;
    }

    /**
     * Obtiene todos los préstamos vencidos
     */
    public List<Loan> getExpiredLoans() {
        List<Loan> expired = new ArrayList<>();
        for (Loan loan : loans) {
            if (loan.isExpired()) {
                expired.add(loan);
            }
        }
        return expired;
    }

    /**
     * Obtiene todos los préstamos registrados
     */
    public List<Loan> getAllLoans() {
        return new ArrayList<>(loans);
    }
}
