package book;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class InsertTest {

   public static void main(String[] args) throws SQLException {
      InsertTest test = new InsertTest();
      Scanner sc = new Scanner(System.in);
      //test.insertMember("test002", "testPwd", "testNick002", 25000);
      test.selectMember();
      System.out.println("수정 할 책아이디 제목 저자 출판사 가격 순으로 입력");
      StringTokenizer st = new StringTokenizer(sc.nextLine());
      //test.updateMember(Integer.parseInt(st.nextToken()), st.nextToken(), st.nextToken(), st.nextToken(), Integer.parseInt(st.nextToken()));
      test.deleteMember(Integer.parseInt(st.nextToken()));
   }
   
   void deleteMember(int bookId) throws SQLException {
	      // DB 연결
	      Connection conn = getDBConnection();
	      if (conn != null)
	         System.out.println("오라클 DB 연결 성공");
	      else
	         return;

	      // PreparedStatement 변수 선언
	      PreparedStatement pstmt = null;
	      
	      String sql = "DELETE BOOK WHERE BOOKID=?";
	      
	      try {
	          // PreparedStatement 객체 생성
	          pstmt = conn.prepareStatement(sql);

	          // SQL문 매개변수에 값 추가
	          pstmt.setInt(1, bookId);
	          
	          int result = pstmt.executeUpdate();
	          if (result > 0)
	             System.out.println(result + "행이 삭제되었습니다.");
	      } catch (SQLException e) {
	          e.printStackTrace();
	      } finally {
	         // 자원 객체 닫기
	         if (conn != null) {
	            try {
	               conn.close();
	            } catch (SQLException e) {
	               e.printStackTrace();
	            }
	         }
	         if (pstmt != null) {
	            try {
	               pstmt.close();
	            } catch (SQLException e) {
	               e.printStackTrace();
	            }
	         }

	      }
	      
	      
}

   void selectMember() throws SQLException {
	      // DB 연결
	      Connection conn = getDBConnection();
	      if (conn != null)
	         System.out.println("오라클 DB 연결 성공");
	      else
	         return;

	      // PreparedStatement 변수 선언
	      PreparedStatement pstmt = null;
	      
	      String get = "SELECT * FROM BOOK";
	      
	      pstmt = conn.prepareStatement(get);
	      ResultSet rs = pstmt.executeQuery();
	      
	      while(rs.next()) {
	    	  int bookId = rs.getInt("BOOKID");
	    	  String title = rs.getString("TITLE");
	    	  String author = rs.getString("AUTHOR");
	    	  String publisher = rs.getString("PUBLISHER");
	    	  int price = rs.getInt("PRICE");
	    	  System.out.println(bookId +"번, 제목 : "+ title + " | 저자 : " + author +" | 출판사 : "+ publisher + " | 가격 :" + price);
	      }
	      
   }
   void updateMember(int bookId, String title, String author, String publisher, int price) throws SQLException {
	      // DB 연결
	      Connection conn = getDBConnection();
	      if (conn != null)
	         System.out.println("오라클 DB 연결 성공");
	      else
	         return;

	      // PreparedStatement 변수 선언
	      PreparedStatement pstmt = null;
	      
	      String sql = new StringBuilder().append("update BOOK set TITLE=?, AUTHOR=?, PUBLISHER=?, PRICE=? WHERE BOOKID=?").toString();
	      
	      try {
	          // PreparedStatement 객체 생성
	          pstmt = conn.prepareStatement(sql);

	          // SQL문 매개변수에 값 추가
	          pstmt.setString(1, title);
	          pstmt.setString(2, author);
	          pstmt.setString(3, publisher);
	          pstmt.setInt(4, price);
	          pstmt.setInt(5, bookId);
	          
	          int result = pstmt.executeUpdate();
	          if (result > 0)
	             System.out.println(result + "행이 수정되었습니다.");
	      } catch (SQLException e) {
	          e.printStackTrace();
	      } finally {
	         // 자원 객체 닫기
	         if (conn != null) {
	            try {
	               conn.close();
	            } catch (SQLException e) {
	               e.printStackTrace();
	            }
	         }
	         if (pstmt != null) {
	            try {
	               pstmt.close();
	            } catch (SQLException e) {
	               e.printStackTrace();
	            }
	         }

	      }
   }
   
   void insertMember(String id, String password, String nickname, int price) {
      // DB 연결
      Connection conn = getDBConnection();
      if (conn != null)
         System.out.println("오라클 DB 연결 성공");
      else
         return;

      // PreparedStatement 변수 선언
      PreparedStatement pstmt = null;

      // sql문 만들기
      String sql = new StringBuilder()
            .append("INSERT INTO BOOK (BOOKID, TITLE, AUTHOR, PUBLISHER, PRICE)")
            .append("VALUES (BOOKID_SEQ.NEXTVAL, ?, ?, ?, ?)").toString();

      try {
         // PreparedStatement 객체 생성
         pstmt = conn.prepareStatement(sql);

         // SQL문 매개변수에 값 추가
         pstmt.setString(1, id);
         pstmt.setString(2, password);
         pstmt.setString(3, nickname);
         pstmt.setInt(4, price);

         // SQL문 실행
         int result = pstmt.executeUpdate();
         if (result > 0)
            System.out.println(result + "행이 추가되었습니다.");

      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         // 자원 객체 닫기
         if (conn != null) {
            try {
               conn.close();
            } catch (SQLException e) {
               e.printStackTrace();
            }
         }
         if (pstmt != null) {
            try {
               pstmt.close();
            } catch (SQLException e) {
               e.printStackTrace();
            }
         }

      }
   }

   Connection getDBConnection() {
      // DB 연결을 위한 정보
      final String jdbcDriverClassName = "oracle.jdbc.OracleDriver"; //
      final String url = "jdbc:oracle:thin:@localhost:1521:xe"; // 어떤 데이터베이스 유형인지, 연동할 위치가 어디인지
      final String userid = "c##java";
      final String passwd = "1234";

      // DB 연결 객체 생성하여 반환
      Connection conn = null;

      try {
         // JDBC 드라이버 loading
         Class.forName(jdbcDriverClassName);

         // Connection 객체 생성
         conn = DriverManager.getConnection(url, userid, passwd);

      } catch (ClassNotFoundException | SQLException e) {
         e.printStackTrace();
      }

      return conn;
   }

}
