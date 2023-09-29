package com.database;

import com.elements.Author;
import com.elements.Book;
import com.elements.Series;

import java.util.ArrayList;
import java.util.List;

public class DataBase {
    // make the single instance to get the same data from it in different classes
    private static DataBase instance = new DataBase();
    private List<Book> books = new ArrayList<>();
    private List<Series> series = new ArrayList<>();
    private List<Author> authors = new ArrayList<>();
    private List<Author> characters = new ArrayList<>();

    private DataBase() {
    }

    public static DataBase getInstance() {
        return instance;
    }

    public void updateBook(Book updatedBook) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getId() == updatedBook.getId()) {
                books.set(i, updatedBook);
            }
        }
    }

    public void addSeries(Series newSeries) {
        series.add(newSeries);
    }

    public void deleteBookById(int idOfBookToDelete) {
        books.removeIf(book -> book.getId() == idOfBookToDelete);
    }

    public List<Book> findBooksByAuthor(int authorId) {
        for (Author author : authors) {
            if (author.getId() == authorId) {
                return author.getListOfBook();
            }
        }
        System.out.println("Sorry. The books of this author weren't found");
        return null;
    }

    public void addBook(Book newBook) {
        books.add(newBook);
    }

    public List<Book> getBooks() {
        return books;
    }

    public List<Series> getSeries() {
        return series;
    }

    public List<Author> getAuthors() {
        return authors;
    }
}