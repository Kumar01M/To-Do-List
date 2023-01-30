package controller;

import java.io.IOException;
import java.io.PrintWriter;

import dao.ToDoDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/removeList")
@MultipartConfig
public class RemoveFromListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(request.getParameter("id"));
		ToDoDao.getDAO().removeFromList(Integer.parseInt(request.getParameter("id")));
		PrintWriter out = response.getWriter();
		out.write("removed");
	}
}
