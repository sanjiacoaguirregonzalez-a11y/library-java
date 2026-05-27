package co.edu.uniremignton.model;
import java.security.PublicKey;
import java.time.LocalDate;

/*
⏺CREMSO LA CLASE LOAN OSEA PRESTAMO PARA
VERIFICAR LOS PRESTAMOS DE LOS LIBROS, ESTA CLASE VA A TENER LOS ATRIBUTOS
ID, EL LIBRO QUE SE PRESTA, EL NOMBRE DE LA PERSONA QUE LO PRESTA,
LA FECHA DE PRESTAMO, LA FECHA DE VENCIMIENTO Y SI EL LIBRO FUE DEVUELTO O NO.
*/

public class Loan {
    private final String id;
    private final Book book;
    private final String borrowername;
    private final LocalDate loandate;
    private LocalDate expirationdate;
    private boolean returned;


    // ⏺CONTRUCTOR DE PRESTAMO DE LOS LIBROS
    public Loan(String id, Book book, String borrowername) {
        this.id = id;
        this.book = book; //➡ EL LIBRO QUE SE PRESTA
        this.borrowername = borrowername; //➡ NOMBRE DE LA PERSONA QUE PRESTA EL LIBRO

        this.loandate = LocalDate.now(); //➡ FECHA DE PRESTAMO
        this.expirationdate = this.loandate.plusDays(14); //➡ FECHA DE VENCIMIENTO

        this.returned = false; //➡ EL LIBRO NO HA SIDO DEVUELTO
    }

    // ⏺GETTER PARA OBTENER LOS VALORES DE LOS ATRIBUTOS PRIVADOS
    public String getId() { return id; }
    public Book getBook() { return book; }
    public String getBorrowername() { return borrowername; }
    public LocalDate getLoandate() { return loandate; }
    public LocalDate getExpirationdate() { return expirationdate; }
    public boolean isReturned() { return returned; }

    // ⏺METODOS DE COMPORTAMIENTO

    // CAMBIA DEVUELTO A TRUE
    public void regisetrReturn(){
        this.returned = true;
    }

    // RETORNA TURE SI ES POSTERIOR A LA FECHA Y EL LIBRO NO FUE DEVUELTO
    public boolean Expired() {
        return LocalDate.now().isAfter(this.expirationdate) && !this.returned;
    }


    // RETORNA LA CADENA LEGIBLE CON EL RESUMEN DEL PRESTAMO
    public String toString() {
        return "Loan{" +
                "id='" + id +
                ", book=" + book +
                ", borrowername='" + borrowername +
                ", loandate=" + loandate +
                ", expirationdate=" + expirationdate +
                ", returned=" + returned +
                '}';
    }
}
