package user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {

		private Connection conn;
		private PreparedStatement pstmt;
		//sql injection 같은 해킹 공격을 방어하기 위한 수단 
		private ResultSet rs;
//		ctrl + shift + o 단축키 사용하여 sql 커넥션 등록
		
		public UserDAO() {
			try {
				  String dbURL = "jdbc:mysql://localhost:3306/BBS?serverTimezone=UTC";
				  String dbID = "root";
				  String dbPassword = "root";
				  Class.forName("com.mysql.cj.jdbc.Driver");
				//드라이버는 mysql에 접속할 수 있는 매개체 역활을 해준다.  
				conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
				//conn 객체안에 접속된 정보가 담기게 된다.
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		//실제로 mysql에 접속을 하게 해주는 부분이다.
		
		public int login(String userID, String userPassword) {
			String SQL = "SELECT userPassword FROM USER WHERE userID = ?";
			try {
				pstmt = conn.prepareStatement(SQL);
				pstmt.setString(1, userID);
				//sql injection 같은 해킹 공격을 방어하기 위한 수단
				rs = pstmt.executeQuery();
				//아이디 존재 여부 체크
				if(rs.next()) {
					if(rs.getString(1).equals(userPassword)) {
						return 1; // 로그인 성공
						
					}else {
						return 0; // 비밀번호가 일치하지 않음
					}
				}
				return -1; // 아이디가 없을 경우 
				
			}catch(Exception e) {
				e.printStackTrace();
			}
			return -2; // 데이터 오류
		}
		
		
		public int join(User user) {
			String SQL = "INSERT INTO USER VALUES(?, ?, ?, ?, ?)";
			try {
				pstmt =conn.prepareStatement(SQL);
				pstmt.setString(1, user.getUserID());
				pstmt.setString(2, user.getUserPassword());
				pstmt.setString(3, user.getUserName());
				pstmt.setString(4, user.getUserGender());
				pstmt.setString(5, user.getUserEmail());
				return pstmt.executeUpdate();
			}catch(Exception e) {
				e.printStackTrace();
			}
			return -1;
			//데이터 오류
		}
			
		
		}
		

