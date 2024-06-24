package book;

import java.lang.reflect.Member;
import java.util.List;

import book.oracle.OracleBookDAO;


public class BookManagerTest {
	public static void main(String[] args) {
		BookService service = new BookService(new OracleBookDAO());

		
		Book book = service.read(1);
		if (book != null) {
			System.out.println(book.toString());
		}
		/*
		 * member = new Member("TEST002", "1234", "TESTER03");
		 * if(service.regist(member)) { System.out.println("회원을 등록하였습니다."); }else {
		 * System.out.println("회원 등록에 실패했습니다."); }
		 */
		book = new Book(2, "title", "auther", "publisher", 30000);
		service.edit(book, "1234");
		
		service.remove(2);

		List<Book> bookList = service.readAll();
		for (Book b : bookList) {
			if (b != null) {
				System.out.println(b.toString());
			}
		}
		
	}
}
