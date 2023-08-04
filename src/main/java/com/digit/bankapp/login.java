package com.digit.bankapp;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

	@WebServlet("/login")
	public class login extends HttpServlet{
		 private Connection con;
		private PreparedStatement pstmt;
		private ResultSet resultSet;

		@Override
		    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                   int cust_id = Integer.parseInt(req.getParameter("cust_id"));
                   int pin = Integer.parseInt(req.getParameter("pin"));
                   
                   
                   HttpSession session=req.getSession(true);
                   //database connection
                  try {
                	  Class.forName("com.mysql.cj.jdbc.Driver");
                	  String url = "jdbc:mysql://localhost:3306/bank";
                      String user = "root";
                      String pwd = "1732";
                      
                      con=DriverManager.getConnection(url,user,pwd);
                      pstmt=con.prepareStatement("select* from register where cust_id=? and pin=?");
                      pstmt.setInt(1,cust_id);
                      pstmt.setInt(2,pin);
                      
                      resultSet  = pstmt.executeQuery();//<---------------
                      if(resultSet.next()==true)
                      {
                   	   session.setAttribute("cust_id",resultSet.getInt("cust_id"));
                   	   session.setAttribute("pin",resultSet.getString("pin"));
                   	   session.setAttribute("customer_name",resultSet.getString("customer_name"));
                   	   resp.sendRedirect("/BankApplication/loginsuccess.html");
                   	   
                      }
                      else {
                   	   resp.sendRedirect("/BankApplication/loginfail.html");
                      }
                  }
                  catch (Exception e)
                  {
                	  System.out.println("error");
                  }
                   
	}

}