package com.digit.bankapp;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Register")//<---linking

public class Register extends HttpServlet{

    private Connection con;

    private PreparedStatement pstmt;

 

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int bank_id = Integer.parseInt(req.getParameter("bank_id"));

        String bank_name = req.getParameter("bank_name");

        String ifsc_code = req.getParameter("ifsc_code");

        int accno = Integer.parseInt(req.getParameter("accno"));

        int pin = Integer.parseInt(req.getParameter("pin"));

        int cust_id = Integer.parseInt(req.getParameter("cust_id"));

        String customer_name = req.getParameter("customer_name");

        int balance = Integer.parseInt(req.getParameter("balance"));

        String email = req.getParameter("email");

//        long phone = Long.parseLong(req.getParameter("phone"));

 
      
        

        //Database connection

        try {

//            Class.forName("com.mysql.cj.jdbc.Driver");
        	Class.forName("com.mysql.cj.jdbc.Driver");
        	 
            String url = "jdbc:mysql://localhost:3306/bank";

            String user = "root";

            String pwd = "1732";

            con = DriverManager.getConnection(url, user, pwd);

            

            pstmt = con.prepareStatement("insert into register values(?,?,?,?,?,?,?,?,?)");

            pstmt.setInt(1, bank_id);

            pstmt.setString(2, bank_name);

            pstmt.setString(3, ifsc_code);

            pstmt.setInt(4, accno);

            pstmt.setInt(5, pin);

            pstmt.setInt(6, cust_id);

            pstmt.setString(7, customer_name);

            pstmt.setInt(8, balance);

            pstmt.setString(9, email);

//            pstmt.setLong(10, phone);

            

            int x = pstmt.executeUpdate();

            if(x>0) 
            {

                resp.sendRedirect("/BankApplication/success.html");

            }

            else {

                resp.sendRedirect("/BankApplication/fail.html");

            }

           
        }

        catch (Exception e) {

            e.printStackTrace();

        }

    }

}
