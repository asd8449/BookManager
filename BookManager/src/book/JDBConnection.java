package book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

abstract class JDBConnection {

	protected Connection conn;
	protected PreparedStatement pstmt;
	protected ResultSet rs;

	abstract void close();

}
