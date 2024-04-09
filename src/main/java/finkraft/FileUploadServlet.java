package finkraft;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

//@WebServlet("/FileUploadServlet")
@MultipartConfig
public class FileUploadServlet extends HttpServlet {

		Connection con = null;
		PreparedStatement pstmt = null;
		String url = "jdbc:mysql://localhost:3306/finkraft";
		String un ="root";
		String password ="bhargavi@123";
		 String query = "insert into  transactions values (?,?,?,?,?,?)";
		
		
		@Override
		public void init() throws ServletException 
		{
			System.out.println("hello jdbc loading");
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				System.out.println("loaded succesfully");
		        con = DriverManager.getConnection(url,un,password);
		        System.out.println("connection established succesfully");
		       
		        
				
			} catch (ClassNotFoundException | SQLException |NullPointerException e) {
				e.printStackTrace();
			}
			  
		}
		
		
		
		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			 try {
			        pstmt = con.prepareStatement(query);
			        String transactionId = req.getParameter("ti");
			        String customerName = req.getParameter("cn");
			        String transactionDate = req.getParameter("td");
			        BigDecimal amount = new BigDecimal(req.getParameter("am"));
			        String status = req.getParameter("st");
			        String invoiceUrl = req.getParameter("url");
			        
			        pstmt.setString(1, transactionId);
			        pstmt.setString(2, customerName);
			        pstmt.setString(3, transactionDate);
			        pstmt.setBigDecimal(4, amount);
			        pstmt.setString(5, status);
			        pstmt.setString(6, invoiceUrl);
			        
			        pstmt.executeUpdate();
			        
			        System.out.println("Row inserted successfully");
		        
		    } catch (SQLException | NullPointerException e) {
		        e.printStackTrace();
		    }
		}

		
		
		
		@Override
		public void destroy() 
		{
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
		
		
		
		
		
	}
    



