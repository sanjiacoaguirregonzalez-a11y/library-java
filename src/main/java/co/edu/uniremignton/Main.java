/*
⏺️PUNTO DE ENTRADA DE LA APLICACION, DONDE SE INICIA,
EN EL MAIN CREAMOS UNA INSTANCIA DE LA BIBLIOTECA,
Y DESDE AQUI SE PODRA INICIAR LA INTERFAZ DE USUARIO
PARA REGISTRAR LIBROS O REALIZAR OTRAS OPERACIONES.
*/

package co.edu.uniremignton;
import co.edu.uniremignton.service.Library;
import co.edu.uniremignton.ui.ConsoleUI;

public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        ConsoleUI consoleUI = new ConsoleUI(library);

        // PRENDEMOS LA ConsoleUI
        consoleUI.start();    }
}