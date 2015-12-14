package controller.handler;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import annotation.RequestMapping;
import domain.person.Person;
import domain.person.Role;

@RequestMapping(action="addperson_complete")
public class RegisterHandler extends Handler {

	@Override
	public String handleRequest(String action, HttpServletRequest request,
			HttpServletResponse response) {
		List<String> errors = new ArrayList<String>();
		List<String> values = new ArrayList<String>();
		values.add("");
		values.add("");
		values.add("");

		String firstName = "";
		try {
			firstName = request.getParameter("first");
			Person.isValidFirstName(firstName);
			values.set(0, firstName);
		} catch (IllegalArgumentException e) {
			errors.add(e.getMessage());
		}

		String lastName = "";
		try {
			lastName = request.getParameter("last");
			Person.isValidLastName(lastName);
			values.set(1, lastName);
		} catch (IllegalArgumentException e) {
			errors.add(e.getMessage());
		}

		String email = "";
		try {
			email = request.getParameter("mail");
			Person.isValidUserId(email);
			values.set(2, email);
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

		try {
			if (errors.size() == 0)
				webshopFacade.addPerson(new Person(email, password, firstName,
						lastName, Role.CUSTOMER));
		} catch (IllegalArgumentException e) {
			errors.add(e.getMessage());
		}

		action = "home";

		if (!errors.isEmpty()) {
			request.setAttribute("errors", errors);
			action = "addperson_start";
		}

		request.setAttribute("values", values);
		return action;
	}

}
