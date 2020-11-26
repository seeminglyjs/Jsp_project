package user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {

		private Connection conn;
		private PreparedStatement pstmt;
		//sql injection ���� ��ŷ ������ ����ϱ� ���� ���� 
		private ResultSet rs;
//		ctrl + shift + o ����Ű ����Ͽ� sql Ŀ�ؼ� ���
		
		public UserDAO() {
			try {
				  String dbURL = "jdbc:mysql://localhost:3306/BBS?serverTimezone=UTC";
				  String dbID = "root";
				  String dbPassword = "root";
				  Class.forName("com.mysql.cj.jdbc.Driver");
				//����̹��� mysql�� ������ �� �ִ� �Ű�ü ��Ȱ�� ���ش�.  
				conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
				//conn ��ü�ȿ� ���ӵ� ������ ���� �ȴ�.
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		//������ mysql�� ������ �ϰ� ���ִ� �κ��̴�.
		
		public int login(String userID, String userPassword) {
			String SQL = "SELECT userPassword FROM USER WHERE userID = ?";
			try {
				pstmt = conn.prepareStatement(SQL);
				pstmt.setString(1, userID);
				//sql injection ���� ��ŷ ������ ����ϱ� ���� ����
				rs = pstmt.executeQuery();
				//���̵� ���� ���� üũ
				if(rs.next()) {
					if(rs.getString(1).equals(userPassword)) {
						return 1; // �α��� ����
						
					}else {
						return 0; // ��й�ȣ�� ��ġ���� ����
					}
				}
				return -1; // ���̵� ���� ��� 
				
			}catch(Exception e) {
				e.printStackTrace();
			}
			return -2; // ������ ����
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
			//������ ����
		}
			
		
		}
		

