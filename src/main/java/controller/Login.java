package main.java.controller;

import javax.servlet.ServletContext;
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
		String path = req.getServletPath();
		ServletContext sc = req.getServletContext();
		System.out.println(path); 
		switch (path){
				case "/":
					 try{
								sc.getRequestDispatcher("/html/index.html").forward(req, res);
						} catch (Exception e){}
					 break;
				case "/calculadora.html":
						try{
								sc.getRequestDispatcher("/html/calculadora.html").forward(req, res);
						}catch (Exception e){}
				break;
				default:
						try{
							 sc.getRequestDispatcher("/html/historico.html").forward(req, res);
						}catch (Exception e){}               
		}
	}
}
