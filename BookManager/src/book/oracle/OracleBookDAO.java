package book.oracle;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import book.Book;
import book.BookDAO;

public class OracleBookDAO implements BookDAO {
	
	public int deleteBook(int bookId) {
		OracleJDBConnection jdbc = new OracleJDBConnection();
		int result = 0;
		
		String sql = new StringBuilder()
				.append("DELETE FROM BOOK ")
				.append("WHERE BOOKID=?")
				.toString();
				try {
					jdbc.pstmt = jdbc.conn.prepareStatement(sql);

					jdbc.pstmt.setInt(1, bookId);

					result = jdbc.pstmt.executeUpdate();

					System.out.println(result + "행이 수정되었습니다.");
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					jdbc.close();
				}
				return result;
	}
	
	public int updateBook(Book book) {
		OracleJDBConnection jdbc = new OracleJDBConnection();
		String sql = "UPDATE BOOK SET TITLE=?, AUTHOR=?, PUBLISHER=?,PRICE=? WHERE BOOKID=?";
		int result = 0;
		try {
			jdbc.pstmt = jdbc.conn.prepareStatement(sql);
			jdbc.pstmt.setString(1, book.getTitle());
			jdbc.pstmt.setString(2, book.getAuthor());
			jdbc.pstmt.setString(3, book.getPublisher());
			jdbc.pstmt.setInt(4, book.getPrice());
			jdbc.pstmt.setInt(5, book.getBookId());
			
			result = jdbc.pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			jdbc.close();
		}	
		return result;
	}
	
	public int insertBook(Book book) {
		OracleJDBConnection jdbc = new OracleJDBConnection();
		String sql = new StringBuilder().append("INSERT INTO BOOK(BOOKID, TITLE, AUTHOR, PUBLISHER, PRICE)").append("VALUSE(BOOKID_SEQ.NEXTVAL, ?, ?, ?, ?)").toString();
		int result = 0;
		try {
			jdbc.pstmt = jdbc.conn.prepareStatement(sql);
			jdbc.pstmt.setString(1, book.getTitle());
			jdbc.pstmt.setString(2, book.getAuthor());
			jdbc.pstmt.setString(3, book.getPublisher());
			jdbc.pstmt.setInt(4, book.getPrice());
			
			result = jdbc.pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			jdbc.close();
		}
		return result;
	}
	
	public List<Book> selectBookAll() {

		OracleJDBConnection jdbc = new OracleJDBConnection();
		List<Book> bookList = new ArrayList<>();
		String sql = "SELECT * FROM BOOK";
		
		try {
			jdbc.pstmt = jdbc.conn.prepareStatement(sql);
			jdbc.rs = jdbc.pstmt.executeQuery();
			while(jdbc.rs.next()) {
				bookList.add(new Book(jdbc.rs.getInt("BOOKID"), jdbc.rs.getString("TITLE"), jdbc.rs.getString("AUTHOR"), jdbc.rs.getString("PUBLISHER"), jdbc.rs.getInt("PRICE")));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			jdbc.close();
		}
		return bookList;
	}
	
	public Book selectBook(int bookId) {

		OracleJDBConnection jdbc = new OracleJDBConnection();
		Book book = null;
		String sql = "SELECT * FROM BOOK WHERE BOOKID = ?";
		
		try {
			jdbc.pstmt = jdbc.conn.prepareStatement(sql);
			jdbc.pstmt.setInt(1, bookId);
			jdbc.rs = jdbc.pstmt.executeQuery();
			if(jdbc.rs.next()) {
				book = new Book(jdbc.rs.getInt("BOOKID"), jdbc.rs.getString("TITLE"), jdbc.rs.getString("AUTHOR"), jdbc.rs.getString("PUBLISHER"), jdbc.rs.getInt("PRICE"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			jdbc.close();
		}
		return book;
	}
}
