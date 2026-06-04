# 📚 Sistema de Gestión de Biblioteca

Un sistema completo de gestión de biblioteca desarrollado en Java que permite administrar libros, registrar préstamos y controlar devoluciones con seguimiento de fechas de vencimiento.

## 👥 Desarrolladores

- **Santiago Aguirre**
- **Alejandro Buitrago**
- **Alejandra Osorio**
- **Luis Gabriel Jaramillo**

## 📋 Descripción del Proyecto

El Sistema de Gestión de Biblioteca es una aplicación de consola que facilita la administración de una biblioteca digitalmente. Permite a los operadores de la biblioteca:

- **Agregar nuevos libros** al catálogo con información completa (ISBN, título, autor, categoría)
- **Consultar libros** disponibles y el inventario completo
- **Gestionar préstamos** de libros con seguimiento automático de fechas
- **Registrar devoluciones** de libros prestados
- **Monitorear préstamos vencidos** para control de seguimiento
- **Eliminar libros** del sistema

## 🎯 Características Principales

### 1. **Gestión de Libros**
- Registro de libros con ISBN, título, autor y categoría
- Control de estado de disponibilidad (Disponible, Prestado, Reservado)
- Búsqueda de libros por ISBN
- Visualización del catálogo completo

### 2. **Sistema de Préstamos**
- Préstamos automáticos con plazo de 14 días
- Seguimiento automático de fechas de préstamo y vencimiento
- Identificador único para cada préstamo
- Registro del nombre del usuario que solicita el préstamo

### 3. **Control de Devoluciones**
- Registro de devoluciones de libros
- Actualización automática del estado del libro a disponible
- Marcado de préstamos como completados

### 4. **Reportes**
- Visualización de préstamos activos
- Identificación de préstamos vencidos
- Listado de libros disponibles para préstamo

## 🏗️ Estructura del Proyecto

```
library-java/
├── README.md
├── library-java.iml
└── src/
    └── main/
        └── java/
            └── co/
                └── edu/
                    └── uniremignton/
                        ├── Main.java
                        ├── model/
                        │   ├── Book.java
                        │   ├── Loan.java
                        │   └── StateBook.java
                        ├── service/
                        │   └── Library.java
                        └── ui/
                            └── ConsoleUI.java
```

## 📦 Componentes del Sistema

### **Modelo de Datos**

#### `Book.java`
Representa un libro en el sistema con los siguientes atributos:
- `isbn` (String): Identificador único del libro
- `title` (String): Título del libro
- `author` (String): Autor del libro
- `category` (String): Categoría/género del libro
- `state` (StateBook): Estado actual del libro

**Métodos principales:**
- `isAvailable()`: Verifica si el libro está disponible para préstamo
- `getters`: Acceso a todos los atributos del libro

#### `Loan.java`
Representa un préstamo de libro con los siguientes atributos:
- `id` (String): Identificador único del préstamo
- `book` (Book): Referencia al libro prestado
- `borrowername` (String): Nombre de quien solicita el préstamo
- `loandate` (LocalDate): Fecha de inicio del préstamo
- `expirationdate` (LocalDate): Fecha de vencimiento (14 días desde préstamo)
- `returned` (boolean): Estado de devolución

**Métodos principales:**
- `registerReturn()`: Marca el préstamo como devuelto
- `isExpired()`: Verifica si el préstamo está vencido
- `getters`: Acceso a todos los atributos del préstamo

#### `StateBook.java`
Enumeración que define los estados posibles de un libro:
- `AVAILABLE`: Libro disponible para préstamo
- `BORROWED`: Libro actualmente prestado
- `RESERVED`: Libro reservado

### **Servicio**

#### `Library.java`
Núcleo lógico de la aplicación, encargado de:
- **Gestión de libros:**
  - `addBook()`: Agregar nuevos libros
  - `removeBook()`: Eliminar libros
  - `getBook()`: Buscar libro por ISBN
  - `getAllBooks()`: Obtener todos los libros
  - `getAvailableBooks()`: Obtener solo libros disponibles

- **Gestión de préstamos:**
  - `borrowBook()`: Registrar un nuevo préstamo
  - `returnBook()`: Registrar devolución
  - `getActiveLoans()`: Listar préstamos activos
  - `getExpiredLoans()`: Listar préstamos vencidos
  - `getAllLoans()`: Listar todos los préstamos

### **Interfaz de Usuario**

#### `ConsoleUI.java`
Proporciona la interfaz de consola interactiva con:
- Menú principal con 9 opciones
- Validación de entrada del usuario
- Mensajes informativos con emojis (UX mejorada)
- Manejo de errores

**Opciones del menú:**
1. ➕ Agregar nuevo libro
2. 📖 Ver todos los libros
3. ✅ Ver libros disponibles
4. 🔄 Prestar un libro
5. 🔙 Devolver un libro
6. 📋 Ver préstamos activos
7. ⚠️ Ver préstamos vencidos
8. 🗑️ Eliminar un libro
9. 🚪 Salir

### **Punto de Entrada**

#### `Main.java`
Punto de inicio de la aplicación que:
1. Crea una instancia de la biblioteca
2. Inicializa la interfaz de usuario
3. Inicia el menú interactivo

## 🚀 Uso de la Aplicación

### Requisitos Previos
- Java 8 o superior instalado
- IDE Java (IntelliJ IDEA, Eclipse, etc.) o compilador de línea de comandos

### Compilación y Ejecución

**Opción 1: Desde línea de comandos**
```bash
# Compilar
javac -d bin src/main/java/co/edu/uniremignton/*.java \
             src/main/java/co/edu/uniremignton/model/*.java \
             src/main/java/co/edu/uniremignton/service/*.java \
             src/main/java/co/edu/uniremignton/ui/*.java

# Ejecutar
java -cp bin co.edu.uniremignton.Main
```

**Opción 2: Desde IDE**
1. Abre el proyecto en tu IDE favorito (IntelliJ IDEA, Eclipse, etc.)
2. Busca la clase `Main.java`
3. Haz clic derecho y selecciona "Run 'Main'"

### Flujo de Ejemplo

1. **Inicia la aplicación** - Verás el menú principal
2. **Agrega libros** - Opción 1, ingresa ISBN, título, autor y categoría
3. **Consulta disponibilidad** - Opción 3 para ver libros disponibles
4. **Realiza un préstamo** - Opción 4, ingresa ISBN y nombre del usuario
5. **Consulta préstamos activos** - Opción 6 para ver préstamos vigentes
6. **Devuelve un libro** - Opción 5, ingresa el ISBN a devolver
7. **Verifica préstamos vencidos** - Opción 7 después de 14 días

## 🔧 Tecnologías Utilizadas

- **Lenguaje:** Java
- **API de Colecciones:** `ArrayList`, `List`, `Optional`
- **API de Fechas:** `java.time.LocalDate`
- **Paradigma:** Programación Orientada a Objetos
- **Patrones de diseño:** Service Pattern, Model-View-Controller (MVC)

## 📐 Arquitectura

El proyecto sigue una arquitectura en capas:

```
┌─────────────────────────────────────┐
│   Interfaz de Usuario (ui)          │  ConsoleUI
│   Presenta información al usuario   │
└─────────────────────────────────────┘
              ↓
┌─────────────────────────────────────┐
│   Capa de Servicio (service)        │  Library
│   Lógica de negocio principal       │
└─────────────────────────────────────┘
              ↓
┌─────────────────────────────────────┐
│   Capa de Modelo (model)            │  Book, Loan, StateBook
│   Definición de entidades           │
└─────────────────────────────────────┘
```

## 💾 Datos y Estado

- Los datos se almacenan en memoria durante la ejecución
- La aplicación utiliza colecciones `ArrayList` para persistencia en runtime
- Nota: Los datos se pierden al cerrar la aplicación (sin persistencia a disco)

## 🎓 Aspectos Educativos

Este proyecto es ideal para aprender:

- ✅ Encapsulación y acceso a datos (getters/setters)
- ✅ Uso de enumeraciones en Java
- ✅ Colecciones y utilidades de Java
- ✅ Manejo de `Optional` para null-safety
- ✅ Programación funcional con streams
- ✅ Gestión de fechas con `LocalDate`
- ✅ Interfaz de usuario en consola
- ✅ Validación de entrada de datos
- ✅ Patrones de diseño (Service Pattern)

## 📝 Ejemplo de Uso en Código

```java
// Crear biblioteca
Library library = new Library();

// Agregar libros
Book book1 = new Book("978-1234567", "Clean Code", "Robert C. Martin", "Programación");
//library.addBook(book1);

// Realizar préstamo
//library.borrowBook("978-1234567", "Juan Pérez");

// Consultar préstamos activos
List<Loan> activeLoans = library.getActiveLoans();

// Devolver libro
//library.returnBook("978-1234567");
```





