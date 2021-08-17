package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import common.model.Menu;
import common.model.Ticket;
import common.util.AppConstants;
import common.util.HttpUtil;
import manager.TicketManager;

@WebServlet("/tickets/*")
public class TicketServlet extends HttpServlet {

	private TicketManager manager = new TicketManager();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String jsonInString = null;
		ObjectMapper mapper = new ObjectMapper();

		String[] pathVariables = HttpUtil.getPathVariables(req);
		
		if(pathVariables == null || pathVariables.length == 0) {
			//get data from backend
			List<Ticket> tickets = manager.findAll();
			//transform java object to JSON string
			jsonInString = mapper.writeValueAsString(tickets);
		} 
		// GET /menus/:id
		// fetch individual menu item
		if(pathVariables != null && pathVariables.length == 2) {
			//get data from backend
			int id = Integer.parseInt(pathVariables[1]);
			Ticket ticket = manager.findById(id);
			//transform java object to JSON string
			if(ticket != null)
				jsonInString = mapper.writeValueAsString(ticket);
			else
				jsonInString = HttpUtil.getErrorMessage("No Record Found");
		}
		
		//send success response to client
		resp.getWriter().print(jsonInString);
		resp.setContentType(AppConstants.HTTP_JSON_CONTENT);
		resp.setStatus(AppConstants.HTTP_OK);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			//get JSON data from HTTP body
			ObjectMapper mapper = new ObjectMapper();
			Ticket ticket = mapper.readValue(HttpUtil.getJSONData(req), Ticket.class);
			//persist data to backend
			manager.create(ticket);
			//send success response to client
			String jsonResponse = mapper.writeValueAsString(ticket);
			resp.getWriter().print(jsonResponse);
			resp.setStatus(AppConstants.HTTP_OK);
		} catch (Exception e) {
			//send failure response to client
			resp.getWriter().print(HttpUtil.getErrorMessage(e.getMessage()));
			resp.setStatus(AppConstants.HTTP_ERROR);
		}

		resp.setContentType(AppConstants.HTTP_JSON_CONTENT);

	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String[] pathVariables = HttpUtil.getPathVariables(req);
		// GET /menus/:id
		// fetch individual menu item
		if(pathVariables != null && pathVariables.length == 2) {
			//get data from backend
			int id = Integer.parseInt(pathVariables[1]);
			manager.delete(id);
			resp.setStatus(AppConstants.HTTP_OK);
		}
	}
}
