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

@WebServlet("/Loan")
public class Loan extends HttpServlet {
	private Connection con;
	private PreparedStatement prep;
	private ResultSet resultSet;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session=req.getSession();
		int id =  Integer.parseInt(req.getParameter("id"));
		
		try {
		Class.forName("com.mysql.cj.jdbc.Driver");
  	    String url = "jdbc:mysql://localhost:3306/bank";
        String user = "root";
        String pwd = "1732";
        
        con=DriverManager.getConnection(url,user,pwd);
        prep=con.prepareStatement("select * from loan where l_id=?");
        prep.setInt(1,id);
        
        resultSet = prep.executeQuery();
		
		if(resultSet.next()==true)
		{
			resp.sendRedirect("/BankApplication/Loandetails.jsp");
			session.setAttribute("loantype", resultSet.getString("l_type"));
			session.setAttribute("tenure", resultSet.getInt("tenure"));
			session.setAttribute("interest", resultSet.getFloat("interest"));
			session.setAttribute("description", resultSet.getString("description"));
		}
		else
		{
			resp.sendRedirect("/BankApplication/Loanfaildetails.jsp");
		}
		
	}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	
	
	}}
