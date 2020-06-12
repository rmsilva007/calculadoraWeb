package main.java.controller;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;
import java.io.PrintWriter;

@WebServlet(name="Login", urlPatterns= {"/index"})
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void doPost(	HttpServletRequest req, HttpServletResponse res){
		req.getRequestDispatcher("/html/index.html");
	}
	
	
	
	

	
}
