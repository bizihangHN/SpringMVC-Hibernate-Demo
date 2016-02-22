package me.goge.service.impl;

import me.goge.dao.BookDao;
import me.goge.model.Book;
import me.goge.service.BookService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;


@Service
public class BookServiceImpl implements BookService
{

	@Resource
	private BookDao bookDao;

	@Override
	@Cacheable(value = "dbCache")
	public int addBook(@Valid Book book) {
		bookDao.insert(book);
		return book.getId();
	}

	@Override
	@Cacheable(value = "dbCache")
	public List<Book> getAllBooks() {
		return bookDao.searchAll();
	}

	@Override
	@Cacheable(value = "dbCache") // add cache .
	// Cacheableע�⻹�и�����key��Ĭ��Ϊ�գ�����ʾʹ�÷����Ĳ������ͼ�����ֵ��Ϊkey��֧��SpEL
	public Book getBookById(int id) {
		return bookDao.searchById(id);
	}

	@Override
	@CacheEvict(value = "dbCache") // delete cache
	// ���и�����key, Ĭ��ͬ��, ���key��Ӧ��cache
	public void deleteBook(int id) {
		bookDao.deleteById(id);
		System.out.println("delete");
	}

    public void deleteAllCache() {
        bookDao.deleteAllCache();
    }
}
