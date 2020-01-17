package com.project;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/loginapplication")
public class LoginApplication extends HttpServlet {
	
		@Override
		 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 	String ValidateCustomerId = request.getParameter("username");
		 	String ValidatePassword = request.getParameter("password");
		 	try {
		 		Class.forName("oracle.jdbc.driver.OracleDriver");
		 		System.out.println("Driver loaded successfully!");
		 		//Get the connection
		 		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","hr");  
		 		System.out.println("Connection Established!");
		 		Statement statement = connection.createStatement();
		 		
		 		int flag=0;
		 		ResultSet resultSet = statement.executeQuery("select * from CUSTOMER_LOGIN");
		 		while(resultSet.next()) 
		 		{
					String CustomerId = resultSet.getString("CUSTOMER_ID");
					String Password = resultSet.getString("PASSWORD");
					if(ValidateCustomerId.equals(CustomerId))
					{
						if(ValidatePassword.equals(Password))
						{
							flag=1;
							break;
						}
					}
				}
		 		
		 		if(flag==1) {
		 			
		 			RequestDispatcher requestDispatcher = request.getRequestDispatcher("home.html");
		 			requestDispatcher.forward(request, response);
		 		}
		 		else {
		 			
		 			RequestDispatcher requestDispatcher = request.getRequestDispatcher("error.html");
		 			requestDispatcher.forward(request, response);
		 		}
		 	} catch (ClassNotFoundException | SQLException e) {
		 		System.out.println(e);
		 		RequestDispatcher requestDispatcher = request.getRequestDispatcher("error.html");
		 		requestDispatcher.forward(request, response);
		 	}	
		 
		   
	}
	
	}
	
		

