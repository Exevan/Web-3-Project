package controller.handler;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import annotation.RequestMapping;
import db.WebshopFacade;
import domain.person.Person;

@RequestMapping(action="deleteperson_complete")
public class DeletePersonHandler extends Handler {

	public DeletePersonHandler(WebshopFacade webshopFacade) {
		super(webshopFacade);
	}

	@Override
	public String handleRequest(HttpServletRequest request,
			HttpServletResponse response) {
		webshopFacade.deletePerson(request.getParameter("mail"));
		
		List<Person> persons = webshopFacade.getPersons();
		request.setAttribute("persons", persons);
		return "personoverview.jsp";
		
		
	}

}
