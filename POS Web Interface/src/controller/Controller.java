package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import db.WebshopFacade;
import domain.NotAuthorizedException;
import domain.person.Person;
import domain.person.Role;
import domain.product.Product;

/**
 * Servlet implementation class Controller
 */
@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private WebshopFacade webshopFacade;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Controller() {
		super();
	}

	@Override
	public void init() throws ServletException {
		super.init();

		ServletContext context = getServletContext();

		Properties properties = new Properties();
		Enumeration<String> paramNames = context.getInitParameterNames();
		while (paramNames.hasMoreElements()) {
			String paramName = paramNames.nextElement();
			properties.setProperty(paramName,
					context.getInitParameter(paramName));
		}

		webshopFacade = new WebshopFacade(properties);
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	private void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request.getParameter("action"), request, response);
	}

	private void processRequest(String action, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		action = (action == null) ? "home" : action;

		try {

			switch (action) {

			case "login":
				processLogin(request, response);
				break;
			case "personoverview":
				processUserOverview(request, response);
				break;
			case "productoverview":
				processProductOverview(request, response);
				break;
			case "addperson_start": // go to the page with the form
				processPersonStart(request, response);
				break;
			case "addperson_complete":
				processRegister(request, response);
				break;
			case "deleteperson_start":
				request.setAttribute("person",
						webshopFacade.getPerson(request.getParameter("mail")));
				forward("deleteperson.jsp", request, response);
				break;
			case "deleteperson_complete":
				processPersonDelete(request, response);
				break;
			case "updateperson_start":
				request.setAttribute("person",
						webshopFacade.getPerson(request.getParameter("mail")));
				forward("updateperson.jsp", request, response);
				break;
			case "updateperson_complete":
				processPersonUpdate(request, response);
				break;
			case "addproduct_start":
				forward("addproduct.jsp", request, response);
				break;
			case "addproduct_complete":
				processAddProduct(request, response);
				break;
			case "deleteproduct_start":
				request.setAttribute("product",
						webshopFacade.getProduct(request.getParameter("name")));
				forward("deleteproduct.jsp", request, response);
				break;
			case "deleteproduct_complete":
				processProductDelete(request, response);
				break;
			case "updateproduct_start":
				request.setAttribute("product",
						webshopFacade.getProduct(request.getParameter("name")));
				forward("updateproduct.jsp", request, response);
				break;
			case "updateproduct_complete":
				processProductUpdate(request, response);
				break;
			case "change_style":
				changeStyle(request, response);
				String origin = request.getParameter("origin");
				processRequest(origin, request, response);
				break;
			case "home":
				forward("index.jsp", request, response);
				break;
			}
		} catch (NotAuthorizedException e) {
			List<String> errors = new ArrayList<>();
			errors.add(e.getMessage());
			request.setAttribute("errors", errors);
			forward("index.jsp", request, response);
		}
	}

	private void processPersonStart(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if (isFromUserWithRole(request, Role.ADMINISTRATOR))
			forward("register.jsp", request, response);
		else
			throw new NotAuthorizedException(
					"You aren't authorized to view this page");
	}

	private boolean isFromUserWithRole(HttpServletRequest request, Role role) {
		Person user = getUser(request);
		return (user == null) ? false : user.getRole().equals(role);
	}

	private void changeStyle(HttpServletRequest request,
			HttpServletResponse response) {
		String new_style = "";
		String old_style = getStyle(request);
		switch (old_style) {
		case "yellow":
			new_style = "red";
			break;
		case "red":
			new_style = "yellow";
			break;
		}
		Cookie cookie = new Cookie("style", new_style);
		cookie.setMaxAge(60 * 60 * 24); // 1 day
		response.addCookie(cookie);
	}

	private String getStyle(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("style")) {
					return cookie.getValue();
				}
			}
		}
		return "yellow";
	}

	private void processLogin(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("passwd");
		Person user = webshopFacade.getPerson(username);
		if (user.isCorrectPassword(password)) {
			request.getSession().setAttribute("user", user);
		} else {
			// TODO say that password was incorrect
			System.out.println("incorrect password");
		}
		processRequest("home", request, response);
	}

	private void processUserOverview(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		List<Person> persons = webshopFacade.getPersons();
		request.setAttribute("persons", persons);
		forward("personoverview.jsp", request, response);
	}

	private void processProductOverview(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		List<Product> products = webshopFacade.getProducts();
		request.setAttribute("products", products);
		forward("productoverview.jsp", request, response);
	}

	private void processRegister(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
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

		String action = "home";

		if (!errors.isEmpty()) {
			request.setAttribute("errors", errors);
			action = "addperson_start";
		}

		request.setAttribute("values", values);
		processRequest(action, request, response);
	}

	private void processAddProduct(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String desc = request.getParameter("desc");
		double price = Double.parseDouble(request.getParameter("price"));

		Product product = new Product(name, desc, price);
		webshopFacade.addProduct(product);

		processRequest("home", request, response);
	}

	private void processProductDelete(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		webshopFacade.deleteProduct(request.getParameter("name"));

		processProductOverview(request, response);
	}

	private void processPersonDelete(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		webshopFacade.deletePerson(request.getParameter("mail"));
		processUserOverview(request, response);
	}

	private void processProductUpdate(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String desc = request.getParameter("desc");
		double price = Double.parseDouble(request.getParameter("price"));

		Product product = new Product(name, desc, price);
		webshopFacade.updateProduct(product);

		processProductOverview(request, response);
	}

	private void processPersonUpdate(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String firstName = request.getParameter("first");
		String lastName = request.getParameter("last");
		String email = request.getParameter("mail");
		String password = request.getParameter("passwd");
		byte[] salt = webshopFacade.getPerson(email).getSalt();

		Person person = new Person(email, password, salt, firstName, lastName,
				Role.CUSTOMER);
		webshopFacade.updatePerson(person);

		processUserOverview(request, response);
	}

	// private boolean establishDbConnection() {
	// try {
	// Context env = (Context) new InitialContext()
	// .lookup("java:comp/env");
	//
	// String username = (String) env.lookup("username");
	// String password = (String) env.lookup("password");
	//
	// boolean person = webshopFacade.establishConnection(username,
	// password);
	// boolean product = webshopFacade.establishConnection(username,
	// password);
	//
	// return person && product;
	// } catch (NamingException e) {
	// e.printStackTrace();
	// }
	// return false;
	// }

	private Person getUser(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		Person user = null;
		if (session != null)
			user = (Person) session.getAttribute("user");
		return user;
	}

	private void forward(String destination, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Person user = getUser(request);
		if (destination.equals("index.jsp") && user != null) {
			request.setAttribute("username", user.getFirstName());
		}

		String style = getStyle(request);
		request.setAttribute("style", style);
		request.getRequestDispatcher(destination).forward(request, response);
	}
}
