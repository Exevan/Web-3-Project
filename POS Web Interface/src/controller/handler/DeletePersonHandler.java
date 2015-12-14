package controller.handler;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import annotation.RequestMapping;
import domain.person.Person;

@RequestMapping(action="deleteperson_start")
@RequestMapping(action="deleteperson_complete")
public class DeletePersonHandler extends Handler {

	@Override
	public String handleRequest(String action, HttpServletRequest request,
			HttpServletResponse response) {
		if(action.equals("deleteperson_start")) {
			request.setAttribute("person",
					webshopFacade.getPerson(request.getParameter("mail")));
			return "deleteperson.jsp";
		}
		
		webshopFacade.deletePerson(request.getParameter("mail"));
		
		List<Person> persons = webshopFacade.getPersons();
		request.setAttribute("persons", persons);
		return "personoverview";
		
		
	}

}
