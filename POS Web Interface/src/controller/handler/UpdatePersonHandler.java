package controller.handler;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import annotation.RequestMapping;
import db.WebshopFacade;
import domain.person.Person;
import domain.person.Role;

@RequestMapping(action="updateperson_complete")
public class UpdatePersonHandler extends Handler {

	public UpdatePersonHandler(WebshopFacade webshopFacade) {
		super(webshopFacade);
	}

	@Override
	public String handleRequest(HttpServletRequest request,
			HttpServletResponse response) {
		List<String> errors = new ArrayList<String>();
		List<String> values = new ArrayList<String>();
		values.add("");
		values.add("");

		String firstName = request.getParameter("first");

		String lastName = "";
		try {
			lastName = request.getParameter("last");
			Person.isValidLastName(lastName);
			values.set(0, lastName);
		} catch (IllegalArgumentException e) {
			errors.add(e.getMessage());
		}

		String email = "";
		try {
			email = request.getParameter("mail");
			Person.isValidUserId(email);
			values.set(1, email);
		} catch (IllegalArgumentException e) {
			errors.add(e.getMessage());
		}

		String password = "";
		try {
			password = request.getParameter("passwd");
			Person.isValidPassword(password);
		} catch (IllegalArgumentException e) {
			errors.add(e.getMessage());
		}

		Role userRole = webshopFacade.getRole(email);
		userRole = userRole == null ? Role.CUSTOMER : userRole;

		try {
			if (errors.size() == 0)
				webshopFacade.updatePerson(new Person(email, password,
						firstName, lastName, userRole));
		} catch (IllegalArgumentException e) {
			errors.add(e.getMessage());
		}

		if (!errors.isEmpty()) {
			request.setAttribute("values", values);
			request.setAttribute("errors", errors);
			return "updateperson.jsp";
		}

		List<Person> persons = webshopFacade.getPersons();
		request.setAttribute("persons", persons);
		return "personoverview.jsp";
	}

}
