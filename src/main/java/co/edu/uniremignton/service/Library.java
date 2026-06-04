package co.edu.uniremignton.service;

import co.edu.uniremignton.model.Book;
import co.edu.uniremignton.model.Loan;
import co.edu.uniremignton.model.StateBook;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// CREAMOS LOS ARRAYS PARA LIBROS Y OTRO PARA PRESTAMOS
public class Library {
    private List<Book> books;
    private List<Loan> loans;


    // CONSTRUCTOR
    public Library() {
        this.books = new ArrayList<>();
        this.loans = new ArrayList<>();
    }

    //AgregarLibro
    public boolean addBook(Book book) {
        if (findBookByIsbn(book.getIsbn()).isPresent()) {
            return false; // Evita duplicados
        }
        books.add(book);
        return true;
    }

    // buscarPorIsbn
    public Optional<Book> findBookByIsbn(String isbn) {
        return books.stream()
                .filter(book -> book.getIsbn().equals(isbn))
                .findFirst();
    }

    // buscarPorTitulo
    public List<Book> findBooksByTitle(String title) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            // .toLowerCase() asegura que si buscas "quijote" encuentre "El Quijote"
            if (book.getTitle().toLowerCase().contains(title.toLowerCase())) {
                result.add(book);
            }
        }
        return result;
    }

    //getCatalogo
    public List<Book> findBooksByAuthor(String author) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            // Es igual al de título, solo que aquí usamos .getAuthor()
            if (book.getAuthor().toLowerCase().contains(author.toLowerCase())) {
                result.add(book);
            }
        }
        return result;
    }

    // getLibrosDisponibles
    public List<Book> getAvailableBooks() {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.isAvailable()) {
                result.add(book);
            }
        }
        return result;
    }


    // prestarLibro(isbn, nombre)
    public boolean borrowBook(String isbn, String borrowerName) {
        Optional<Book> optionalBook = findBookByIsbn(isbn);
        if (optionalBook.isEmpty()) {
            return false;
        }
        Book book = optionalBook.get();
        if (!book.isAvailable()) {
            return false;
        }
        // CAMBIAMOS EL ESTADO DEL LIBRO A PRESTADO
        book.setState(StateBook.BORROWED);
        Loan loan = new Loan(isbn, book, borrowerName);
        loans.add(loan);
        return true;
    }

    // devolverLibro(idPrestamo)
    public boolean returnBook(String isbnLoand) {
        for (Loan loan : loans) {
            // Ahora sí compara String con String. ¡El amarillo desaparece!
            if (loan.getBook().getIsbn().equals(isbnLoand) && !loan.isReturned()) {
                loan.registerReturn();
                loan.getBook().setState(StateBook.AVAILABLE);
                return true;
            }
        }
        return false;
    }

    // getPrestamosActivos()
    public List<Loan> getActiveLoans() {
        List<Loan> result = new ArrayList<>();

        for (Loan loan : loans) {
            if (!loan.isReturned()) {
                result.add(loan);
            }
        }

        return result;
    }

// getTodosLosPrestamos

    public List<Loan> getBook() {
        return loans;
    }


    public List<Book> getBooks() {
        return books;
    }
}