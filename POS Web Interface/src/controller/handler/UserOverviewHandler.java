package controller.handler;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import annotation.RequestMapping;
import domain.person.Person;

@RequestMapping(action="personoverview")
public class UserOverviewHandler extends Handler {

	@Override
	public String handleRequest(String action, HttpServletRequest request,
			HttpServletResponse response) {
		List<Person> persons = webshopFacade.getPersons();
		request.setAttribute("persons", persons);
		return "personoverview.jsp";
	}

}
