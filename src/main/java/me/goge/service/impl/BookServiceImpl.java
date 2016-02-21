package me.goge.service.impl;

import me.goge.dao.BookDao;
import me.goge.model.Book;
import me.goge.service.BookService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class BookServiceImpl implements BookService
{

	@Resource
	private BookDao bookDao;

	public BookServiceImpl() {
		super();

	}

	@Override
	public int addBook(Book book) {
		System.out.println("add book ");
		bookDao.insert(book);
		return 0;
	}

	@Override
	public List<Book> getAllBooks() {
		return null;
	}

	@Override
	@Cacheable(value = "dbCache") // add cache .
	// ��ע�⻹�и�����key��Ĭ��Ϊ�գ�����ʾʹ�÷����Ĳ������ͼ�����ֵ��Ϊkey��֧��SpEL
	public Book getBookById(int id) {
		return bookDao.searchById(id);
	}

	@Override
	@CacheEvict(value = "dbCache") // delete cache
	// ���и�����key, Ĭ��ͬ��, ���key��Ӧ��cache
	public void deleteBook(int id) {
		System.out.println("delete");
	}
}
