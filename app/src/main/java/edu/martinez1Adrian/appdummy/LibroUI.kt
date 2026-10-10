package edu.martinez1Adrian.appdummy

data class LibroUI(
    val id: String,
    val titulo: String,
    val autor: String,
    val year: Int,
    val isbn: String,
    val cover: String,
    val esFavorito: Boolean = false,
    val leido: Boolean = false
)

val librosDeEjemplo = listOf(
    LibroUI(
        id = "1",
        titulo = "Cien años de soledad",
        autor = "Gabriel García Márquez",
        year = 1967,
        isbn = "978-0307474728",
        cover = "https://images-na.ssl-images-amazon.com/images/S/compressed.photo.goodreads.com/books/1327881361i/320.jpg",
        esFavorito = true,
        leido = true
    ),
    LibroUI(
        id = "2",
        titulo = "1984",
        autor = "George Orwell",
        year = 1949,
        isbn = "978-0451524935",
        cover = "https://images-na.ssl-images-amazon.com/images/S/compressed.photo.goodreads.com/books/1657781256i/5470.jpg",
        esFavorito = false,
        leido = true
    ),
    LibroUI(
        id = "3",
        titulo = "El señor de los anillos",
        autor = "J.R.R. Tolkien",
        year = 1954,
        isbn = "978-0544003415",
        cover = "https://images-na.ssl-images-amazon.com/images/S/compressed.photo.goodreads.com/books/1566425108i/33.jpg",
        esFavorito = true,
        leido = false
    ),
    LibroUI(
        id = "4",
        titulo = "Fahrenheit 451",
        autor = "Ray Bradbury",
        year = 1953,
        isbn = "978-1451673319",
        cover = "https://images-na.ssl-images-amazon.com/images/S/compressed.photo.goodreads.com/books/1383718290i/13079982.jpg",
        esFavorito = false,
        leido = false
    ),
    LibroUI(
        id = "5",
        titulo = "Crónica de una muerte anunciada",
        autor = "Gabriel García Márquez",
        year = 1981,
        isbn = "978-1400034710",
        cover = "https://images-na.ssl-images-amazon.com/images/S/compressed.photo.goodreads.com/books/1585860361i/23878.jpg",
        esFavorito = false,
        leido = true
    )
)