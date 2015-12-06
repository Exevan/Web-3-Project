package controller.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.WebshopFacade;

public abstract class Handler {

	protected WebshopFacade webshopFacade;

	public Handler(WebshopFacade webshopFacade) {
		this.webshopFacade = webshopFacade;
	}

	public abstract String handleRequest(HttpServletRequest request, HttpServletResponse response);

}