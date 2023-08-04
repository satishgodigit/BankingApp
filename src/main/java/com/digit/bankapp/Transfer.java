package com.digit.bankapp;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Transfer")
public class Transfer extends HttpServlet{
//private Connection con;
//private PreparedStatement prep;
//private ResultSet resultset2;
//private ResultSet r2;

private Connection con;
private PreparedStatement prep;
private ResultSet resultset2;
private ResultSet r2;

@Override
protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	int id = Integer.parseInt(req.getParameter("cust_id"));
	String bname = req.getParameter("bankname");
	int s_acc = Integer.parseInt(req.getParameter("S_acc"));
	String s_ifsc = req.getParameter("S_ifsc");
	int r_acc = Integer.parseInt(req.getParameter("R_acc"));
	String r_ifsc = req.getParameter("R_ifsc");
	int amount = Integer.parseInt(req.getParameter("amount"));
	int pin = Integer.parseInt(req.getParameter("pin"));
	
	HttpSession session = req.getSession();
	session.setAttribute("error", "Transaction Failed");
	session.setAttribute("amount", amount);
	
	Random r = new Random();
	String transaction_id = (100000 + r.nextInt(900000))+"";
	session.setAttribute("tid", transaction_id);
	
	
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/bank";
		String user = "root";
		String pass = "1732";
		
		con = DriverManager.getConnection(url,user,pass);
		
		
		prep = con.prepareStatement("Select * from register where cust_id=? and ifsc_code=? and accno=? and pin=?");
		prep.setInt(1, id);
		prep.setString(2, s_ifsc);
		prep.setInt(3, s_acc);
		prep.setInt(4, pin);
		
		
		ResultSet r1 = prep.executeQuery();
		
		// validating sender id , ifsc , accno , pin
		if(r1.next()) {
			prep = con.prepareStatement("select * from register where ifsc_code=? and accno=?");
			prep.setString(1,r_ifsc);
			prep.setInt(2, r_acc);
			
			resultset2 = prep.executeQuery();
			
			// validating the receiver acc
			if(resultset2.next()) {
				
				prep = con.prepareStatement("select balance from register where pin=?");
				prep.setInt(1, pin);
				r2 = prep.executeQuery();
				
				if(r2.next()) {
					//validating the balance of sender
					if(r2.getInt("balance")>=amount) {
						// debit from sender
						prep = con.prepareStatement("update register set balance =balance-? where pin=?");
						prep.setInt(1, amount);
						prep.setInt(2, pin);
						int x = prep.executeUpdate();
						
						if(x>0) {
							//crediting to reciver account
							prep = con.prepareStatement("update register set balance =balance+? where accno=?");
							prep.setInt(1, amount);
							prep.setInt(2, r_acc);
							int x2 = prep.executeUpdate();
							
							if(x2>0) {
								//storing the details inside the DB
								prep = con.prepareStatement("Insert into transferstatus values(?,?,?,?,?,?,?)");
								prep.setInt(1, (int)session.getAttribute("cust_id"));
								prep.setString(2,bname);
								prep.setString(3,s_ifsc);
								prep.setInt(4, s_acc);
								prep.setString(5,r_ifsc);
								prep.setInt(6, r_acc);
								prep.setInt(7,amount);
								
								
								int x3 = prep.executeUpdate();
								if(x3>0) {
									resp.sendRedirect("/BankApplication/TransferSuccess.jsp");
								}
								else {
									resp.sendRedirect("/BankApplication/TransferFail.jsp");
									
								}
							}else {
								resp.sendRedirect("/BankApplication/TransferFail.jsp");
							}
		
						}else {
							resp.sendRedirect("/BankApplication/TransferFail.jsp");
						}
					}else {
						resp.sendRedirect("/BankApplication/TransferFail.jsp");
					}
				}else {
					resp.sendRedirect("/BankApplication/TransferFail.jsp");
				}
			}
		}
		else {
			resp.sendRedirect("/BankApplication/TransferFail.jsp");
		}
		
		
	
		
	}catch(Exception e) {
		e.printStackTrace();
	}
}
}