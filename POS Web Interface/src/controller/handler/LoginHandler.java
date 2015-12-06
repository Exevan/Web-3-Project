package controller.handler;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import annotation.RequestMapping;
import domain.WebshopFacade;
import domain.person.Person;

@RequestMapping(action="login")
public abstract class LoginHandler extends Handler{
	
	public LoginHandler(WebshopFacade webshopFacade) {
		super(webshopFacade);
	}
	
	@Override
	public String handleRequest(HttpServletRequest request, 
			HttpServletResponse response) {
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
			return "login_start";
		} else
			return "home";
		
	}

}
