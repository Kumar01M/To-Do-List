package controller;

import java.io.IOException;

import dao.ToDoDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/elementChecked")
@MultipartConfig
public class CheckListServlet extends HttpServlet {	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		int check = ToDoDao.getDAO().isChecked(id);
		resp.getWriter().write(String.valueOf(check));
	}
}
