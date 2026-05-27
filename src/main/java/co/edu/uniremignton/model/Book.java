package co.edu.uniremignton.model;

/*
⏺DEFINIMOS LA CLASE BOOK OSEA LIBRO
JUNTO CON TODOS LOS ATRIBUTOS PRIVADOS
PARA ENCAPSULARLOS.
* */

public class Book {
    private final String isbn;
    private final String title;
    private final String author;
    private final String category;
    private StateBook state;

    /*
    ⏺CONSTRUCTOR DEL LIBRO.
    Y DEJAMOS EL ESTADO DEL LIBRO POR DEFECTO EN DISPONIBLE
    ASI CADA VEZ QUE SE CREE UN LIBRO NUEVO POR DEFECTO
    APARECERA COMO DISPONIBLE PARA SER PRESTADO O RESERVADO.
    */
    public Book(String isbn, String title, String author, String category){
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.category = category;
        this.state = StateBook.AVAILABLE;
    }


    // ⏺UTILIZAMOS LOS GETTER PARA OBTENER LOS VALORES DE LOS ATRIBUTOS PRIVADOS
    public String getIsbn() { return isbn; }

    public String getTitle() { return title; }

    public String getAuthor() { return author; }

    public String getCategory() { return category; }

    public StateBook getState() { return state; }

    public void setState(StateBook state) { this.state = state; }


    /*
      METODOS DE COMPORTAMIENTO
     ⏺METODO PARA QUE NOS RETORNE EL ESTADO DEL LIBRO
     */
    public boolean isAvailable() { return this.state == StateBook.AVAILABLE; }

    // ⏺IMPRIMIMOS EN CADENA DE TEXTO LOS DATOS DEL LIBRO
    public String toString() {
        return  "isbn='" + isbn  +
                ", title='" + title +
                ", author='" + author +
                ", category='" + category +
                ", state=" + state +
                '}';
    }
}

