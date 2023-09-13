package data.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import data.dto.UserDto;
import mysql.db.DBConnect;

public class UserDao {

	DBConnect db=new DBConnect();
	
	public UserDto getData(String u_id) {
		UserDto dto=new UserDto();
	
		Connection conn=db.getConnection();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
	
		String sql="select * from user where u_id=?";
	
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, u_id);
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				dto.setNum(rs.getString("num"));
				dto.setU_id(rs.getString("u_id"));
				dto.setU_pass(rs.getString("u_pass"));
				dto.setU_name(rs.getString("u_name"));
				dto.setU_hp(rs.getString("u_hp"));
				dto.setU_birth(rs.getString("u_birth"));
				dto.setU_email(rs.getString("u_email"));
				dto.setU_rdate(rs.getTimestamp("u_rdate"));
				dto.setLl_date(rs.getTimestamp("ll_date"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			db.dbClose(rs, pstmt, conn);
		}
	
		return dto;
	}
	
	public void updateLL_date(String u_id,String ll_date) {
	      Connection conn=db.getConnection();
	      PreparedStatement pstmt=null;
	      
	      String sql="update user set ll_date=? where u_id=?";
	      
	      try {
	         pstmt=conn.prepareStatement(sql);
	         pstmt.setString(1, ll_date);
	         pstmt.setString(2, u_id);
	         pstmt.execute();
	      } catch (SQLException e) {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	      }
	      finally {
	         db.dbClose(pstmt, conn);
	      }
	}
	//아이디체크
    public int isUserIdCheck(String userId){
    	int isUserId=0;
       
    	Connection conn=db.getConnection();
    	PreparedStatement pstmt=null;
    	ResultSet rs=null;
       
    	String sql="select count(*) from user where u_id=?";
       
    	try {
    		pstmt=conn.prepareStatement(sql);
       
    		pstmt.setString(1, userId);
    		rs=pstmt.executeQuery();
       
    		if(rs.next()){
    			isUserId=rs.getInt(1);
    		}
    	} catch (SQLException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	} finally {
    		db.dbClose(rs, pstmt, conn);
    	}
    	return isUserId;
    }
  //insert... user 회원가입 
	   public void insertUser(UserDto dto,String now)
	   {
		   Connection conn=db.getConnection();
		   PreparedStatement pstmt=null;
		   
		   String sql="insert into user values(null,?,?,?,?,?,?,?,?)";
		   
		   try {
			   
			   pstmt=conn.prepareStatement(sql);
			   
			   pstmt.setString(1, dto.getU_id());
			   pstmt.setString(2, dto.getU_pass());
			   pstmt.setString(3, dto.getU_name());
			   pstmt.setString(4, dto.getU_hp());
			   pstmt.setString(5, dto.getU_birth());
			   pstmt.setString(6, dto.getU_email());
			   pstmt.setString(7, now);
			   pstmt.setString(8, now);
			
			   pstmt.execute();
			   
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.dbClose(pstmt, conn);
		}
		   
	   }
	   
	   //로그인 시, 아이디와 비번 체크
	   public boolean isUserIdPass(String userId,String userPass)
	   {
		   boolean b=false;
		   
		   Connection conn=db.getConnection();
		   PreparedStatement pstmt=null;
		   ResultSet rs=null;
		   
		   String sql="select * from user where u_id=? and u_pass=?";
		   
		   try {
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setString(1, userId);
			pstmt.setString(2, userPass);
			
			rs=pstmt.executeQuery();
			
			if(rs.next())
			{
				b=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.dbClose(rs, pstmt, conn);
		}

		   return b;
	   }
	   
	   public String allSelect(String grade, String id,String name, String selectId) {
		   Connection conn=db.getConnection();
		   String returnName="";
		   PreparedStatement pstmt=null;
		   ResultSet rs=null;
		   
		   String sql="select ? from ? where ?=?";
		   
		   try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, grade);
			pstmt.setString(3, selectId);
			pstmt.setString(4, id);
			
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				returnName=rs.getString(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		   return returnName;
	   }
}
