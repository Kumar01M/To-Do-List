package controller;

import java.io.IOException;
import java.io.PrintWriter;

import org.json.simple.JSONObject;

import dao.ToDoDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/add")
@MultipartConfig
public class AddToListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String data = request.getParameter("inputtodo");
		System.out.println(data);
		response.setContentType("application/json");
		JSONObject obj = new JSONObject();
		
		PrintWriter out = response.getWriter();
		obj.put("input",data);
		obj.put("check", 0);
		obj.put("id", ToDoDao.getDAO().addToList(data));
		out.print(obj);
				
	}

}
