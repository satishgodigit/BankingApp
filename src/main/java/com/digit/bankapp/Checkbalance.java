package com.digit.bankapp;

import java.io.IOException;
import java.io.PrintWriter;
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

@WebServlet("/Checkbalance")
public class Checkbalance extends HttpServlet {
	private PreparedStatement pstmt;
	private Connection con;
	private ResultSet resultSet;

	@Override
	protected void service(HttpServletRequest req,HttpServletResponse resp ) throws ServletException, IOException{
	//	int password =  Integer.parseInt(req.getParameter("balance"));
		
		HttpSession session=req.getSession();//<---here not using (true)because the data saved inside the session	
		try{
		Class.forName("com.mysql.cj.jdbc.Driver");
  	    String url = "jdbc:mysql://localhost:3306/bank";
        String user = "root";
        String pwd = "1732";
        
        con=DriverManager.getConnection(url,user,pwd);
        pstmt=con.prepareStatement("select balance from register where cust_id=?");
        pstmt.setInt(1,(int)session.getAttribute("cust_id"));
//        pstmt.setInt(2,(int)session.getAttribute("pin"));
//        pstmt.setInt(2,pin);
        resultSet  = pstmt.executeQuery();
        if(resultSet.next()==true)
        {
//        	System.out.println(resultSet.getInt("balance"));
        	resp.sendRedirect("/BankApplication/Checkbalance.jsp");
        	session.setAttribute("balance", resultSet.getInt("balance"));
     	  
        }
        else {
//     	   resp.sendRedirect("/BankApplication/loginfail.html");
        	System.out.println("wrong...");
        }
        }
		
		catch (Exception e)
        {
      	  System.out.println("error");
        }
		
	}

}
