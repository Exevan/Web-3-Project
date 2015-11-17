package controller.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import annotation.RequestMapping;
import db.WebshopFacade;

@RequestMapping(action="logout")
public class LogoutHandler extends Handler {

	public LogoutHandler(WebshopFacade webshopFacade) {
		super(webshopFacade);
	}

	@Override
	public String handleRequest(HttpServletRequest request,
			HttpServletResponse response) {
		request.getSession().removeAttribute("user");
		return "home";
	}

}
