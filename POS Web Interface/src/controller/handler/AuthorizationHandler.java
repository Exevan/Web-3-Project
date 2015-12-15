package controller.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import annotation.RequestMapping;
import domain.NotAuthorizedException;
import domain.person.Person;
import domain.person.Role;

@RequestMapping(action="addperson_start")
public class AuthorizationHandler extends Handler {

	@Override
	public String handleRequest(String action, HttpServletRequest request,
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

}
