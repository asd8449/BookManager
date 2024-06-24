package book.oracle;

import java.sql.DriverManager;
import java.sql.SQLException;

import member.JDBConnection;



public class OracleJDBConnection extends JDBConnection {

	public OracleJDBConnection() {

		// DB 연결을 위한 정보
		final String jdbcDriverClassName = "oracle.jdbc.OracleDriver"; //
		final String url = "jdbc:oracle:thin:@localhost:1521:xe"; // 어떤 데이터베이스 유형인지, 연동할 위치가 어디인지
		final String userid = "c##java";
		final String passwd = "1234";

		try {
			// JDBC 드라이버 loading
			Class.forName(jdbcDriverClassName);

			// Connection 객체 생성
			conn = DriverManager.getConnection(url, userid, passwd);
			System.out.println("오라클 DB 연결 성공");

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}

	public void close() {

		try {
			if (conn != null)
				conn.close();
			if (pstmt != null)
				pstmt.close();
			if (rs != null)
				rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
