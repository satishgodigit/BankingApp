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

@WebServlet("/Changepassword")
public class Changepassword extends HttpServlet{
private Connection con;
private PreparedStatement prep;

@Override
protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	int password = Integer.parseInt(req.getParameter("old"));
	int newpass = Integer.parseInt(req.getParameter("new"));
	int confirm = Integer.parseInt(req.getParameter("conf"));
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/bank";
		String user = "root";
		String pass = "1732";
		
		con = DriverManager.getConnection(url,user,pass);
		prep = con.prepareStatement("update register set pin=? where pin=?");
		
		prep.setInt(1, newpass);
		prep.setInt(2, password);
		
		
		int r = prep.executeUpdate();
		if(r>0) {
			resp.sendRedirect("/BankApplication/Changepasswordsuccess.html");
		}
		else {
			resp.sendRedirect("/BankApplication/ChangepasswordFail.html");
		}
		
	}catch(Exception e) {
		e.printStackTrace();
	}
}
}
