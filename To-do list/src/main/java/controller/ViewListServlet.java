package controller;

import java.io.IOException;
import java.io.PrintWriter;

import dao.ToDoDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/getList")
public class ViewListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		PrintWriter out = response.getWriter();
		out.print(ToDoDao.getDAO().getList());
	}
}
