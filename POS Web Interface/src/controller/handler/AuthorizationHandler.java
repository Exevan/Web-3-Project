package controller.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import annotation.RequestMapping;
import domain.NotAuthorizedException;
import domain.WebshopFacade;
import domain.person.Person;
import domain.person.Role;

@RequestMapping(action="addperson_start")
public class AuthorizationHandler extends Handler {

	public AuthorizationHandler(WebshopFacade webshopFacade) {
		super(webshopFacade);
	}

	@Override
	public String handleRequest(HttpServletRequest request,
			HttpServletResponse response) {
		if (isFromUserWithRole(request, Role.ADMINISTRATOR))
			return "register.jsp";
		else
			throw new NotAuthorizedException(
					"You aren't authorized to view this page");
	}
	
	private boolean isFromUserWithRole(HttpServletRequest request, Role role) {
		Person user = getUser(request);
		return (user == null) ? false : user.getRole().equals(role);
	}
	
	private Person getUser(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		Person user = null;
		if (session != null)
			user = (Person) session.getAttribute("user");
		return user;
	}

}
