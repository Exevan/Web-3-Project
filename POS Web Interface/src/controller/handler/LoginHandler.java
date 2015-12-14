package controller.handler;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import annotation.RequestMapping;
import domain.person.Person;

@RequestMapping(action="login_start")
@RequestMapping(action="login")
public class LoginHandler extends Handler{
	
	@Override
	public String handleRequest(String action, HttpServletRequest request, 
			HttpServletResponse response) {
		
		if (action.equals("login_start"))
			return "login.jsp";
		
		
		List<String> errors = new ArrayList<String>();
		List<String> values = new ArrayList<String>();
		values.add("");
	
		String username = request.getParameter("username");
		String password = request.getParameter("passwd");
		Person user = webshopFacade.getPerson(username);
		
		if (user == null) {
			errors.add("A user with this email does not exist");
		} else if (user.isCorrectPassword(password)) {
			request.getSession().setAttribute("user", user);
		} else {
			values.set(0, username);
			errors.add("The password is incorrect");
		}
		
		if (errors.size() > 0) {
			request.setAttribute("errors", errors);
			request.setAttribute("values", values);
			return "login.jsp";
		} else
			return "home";
		
	}

}
