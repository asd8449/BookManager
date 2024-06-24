package book;

import java.util.List;

public interface BookDAO {
	
	public int deleteBook(int bookId);
	
	public int updateBook(Book book);
	
	public int insertBook(Book book);
	
	public List<Book> selectBookAll();
	
	public Book selectBook(int bookId);
}
