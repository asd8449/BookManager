package book;

import java.util.List;

public class BookService {
	private BookDAO bookDao;
	
	public BookService(BookDAO b) {
		bookDao = b;
	}
	
	public boolean regist(Book book) {
		int result = bookDao.insertBook(book);
		return result == 1;
	}
	
	public Book read(int bookId) {
		Book book = bookDao.selectBook(bookId);
		return book;
	}
	
	public List<Book> readAll(){
		List<Book> bookList = bookDao.selectBookAll();
		return bookList;
	}
	
	public boolean edit(Book book, String oldTitle) {
		if(book == null)return false;
		if(oldTitle == null)return false;
		Book bookInfo = bookDao.selectBook(book.getBookId());
		if(bookInfo.getTitle().equals(oldTitle)) {
			bookDao.updateBook(book);
			return true;			
		}
		return false;
	}
	
	public boolean remove(int bookId) {
		if(bookDao.selectBook(bookId) == null) {
			return false;
		}
		int result = bookDao.deleteBook(bookId);
		return true;
	}
}
