package co.edu.uniremignton.ui;

import co.edu.uniremignton.model.Book;
import co.edu.uniremignton.model.Loan;
import co.edu.uniremignton.service.Library;
import java.util.List;
import java.util.Scanner;

public class ConsoleUI {
    private Library library;
    private Scanner scanner;

    public ConsoleUI(Library library) {
        this.library = library;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        boolean running = true;
        while (running) {
            showMainMenu();
            int option = getIntInput();

            switch (option) {
                case 1:
                    addBook();
                    break;
                case 2:
                    showAllBooks();
                    break;
                case 3:
                    showAvailableBooks();
                    break;
                case 4:
                    borrowBook();
                    break;
                case 5:
                    returnBook();
                    break;
                case 6:
                    showActiveLoans();
                    break;
                case 0:
                    running = false;
                    System.out.println("¡Gracias por usar el sistema de biblioteca!");
                    break;
                default:
                    System.out.println("❌ Opción no válida. Intenta de nuevo.");
            }
        }
        scanner.close();
    }

    private void showMainMenu() {
        System.out.println("\n=== 📚 SISTEMA DE GESTIÓN DE BIBLIOTECA ===");
        System.out.println("1. ➕ Agregar nuevo libro");
        System.out.println("2. 📖 Ver todos los libros (Historial)");
        System.out.println("3. ✅ Ver libros disponibles");
        System.out.println("4. 🔄 Prestar un libro");
        System.out.println("5. 🔙 Devolver un libro");
        System.out.println("6. 📋 Ver préstamos activos");
        System.out.println("0. 🚪 Salir");
        System.out.print("Selecciona una opción: ");
    }

    private void addBook() {
        System.out.println("\n--- Agregar Nuevo Libro ---");

        System.out.print("ISBN: ");
        String isbn = scanner.nextLine().trim();

        System.out.print("Título: ");
        String title = scanner.nextLine().trim();

        System.out.print("Autor: ");
        String author = scanner.nextLine().trim();

        System.out.print("Categoría: ");
        String category = scanner.nextLine().trim();

        Book book = new Book(isbn, title, author, category);
        
        if (library.addBook(book)) {
            System.out.println("✅ ¡Libro agregado exitosamente!");
        } else {
            System.out.println("❌ Error: Ya existe un libro registrado con ese ISBN.");
        }
    }

    private void showAllBooks() {
        System.out.println("\n--- Historial de Todos los Préstamos Realizados ---");
        List<Book> Books = library.getBooks();

        if (Books.isEmpty()) {
            System.out.println("No hay libros registros en el historial.");
            return;
        }

        for (Book book : Books) {
            System.out.println("🔄 Libro: " + book.getTitle() + " ISBBN " + book.getIsbn() + " Autor " + book.getAuthor());
        }
    }

    private void showAvailableBooks() {
        System.out.println("\n--- Libros Disponibles ---");
        List<Book> available = library.getAvailableBooks();

        if (available.isEmpty()) {
            System.out.println("No hay libros disponibles en este momento.");
            return;
        }

        for (Book book : available) {
            System.out.println("✅ " + book.getTitle() + " (ISBN: " + book.getIsbn() + ")");
        }
        System.out.println("Total disponibles: " + available.size());
    }

    private void borrowBook() {
        System.out.println("\n--- Prestar un Libro ---");

        System.out.print("Ingresa el ISBN del libro: ");
        String isbn = scanner.nextLine().trim();

        System.out.print("Nombre de quien pide prestado: ");
        String borrowername = scanner.nextLine().trim();


        if (library.borrowBook(isbn, borrowername)) {
            System.out.println("✅ Préstamo realizado exitosamente!");
            System.out.println("📅 El libro debe ser devuelto en 14 días.");
        } else {
            System.out.println("❌ No se pudo realizar el préstamo. El libro no existe o ya está prestado.");
        }
    }

    private void returnBook() {
        System.out.println("\n--- Devolver un Libro ---");

        System.out.print("Ingresa el ISBN del libro a devolver: ");
        String isbn = scanner.nextLine().trim();

        if (library.returnBook(isbn)) {
            System.out.println("✅ Libro devuelto exitosamente!");
        } else {
            System.out.println("❌ No se encontró un préstamo activo para este ISBN.");
        }
    }

    private void showActiveLoans() {
        System.out.println("\n--- Préstamos Activos ---");
        List<Loan> activeLoans = library.getActiveLoans();

        if (activeLoans.isEmpty()) {
            System.out.println("No hay préstamos activos en este momento.");
            return;
        }

        for (Loan loan : activeLoans) {
            System.out.println("📌 Libro: " + loan.getBook().getTitle() + " | Prestado a: " + loan.getBorrowername());
        }
        System.out.println("Total activos: " + activeLoans.size());
    }



    private int getIntInput() {
        try {
            int value = Integer.parseInt(scanner.nextLine().trim());
            return value;
        } catch (NumberFormatException e) {
            System.out.println("❌ Entrada inválida. Por favor ingresa un número.");
            return -1;
        }
    }
}