package me.goge.service;

import me.goge.model.Book;

import java.util.List;


public interface BookService
{
	// ���ͼ��
	int addBook(Book book);

	List<Book> getAllBooks();

	Book getBookById(int id);
	
	void deleteBook(int id);
}
