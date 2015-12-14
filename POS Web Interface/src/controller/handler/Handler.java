package controller.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import db.WebshopFacade;
import domain.person.Person;

public abstract class Handler {

	protected WebshopFacade webshopFacade;
	
	public WebshopFacade getWebshopFacade() {
		return webshopFacade;
	}

	public void setWebshopFacade(WebshopFacade webshopFacade) {
		this.webshopFacade = webshopFacade;
	}

	public abstract String handleRequest(String action, HttpServletRequest request, HttpServletResponse response);

	protected Person getUser(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		Person user = null;
		if (session != null)
			user = (Person) session.getAttribute("user");
		return user;
	}
	
	
}