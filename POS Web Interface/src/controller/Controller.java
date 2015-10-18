package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.person.Person;
import domain.person.PersonService;
import domain.product.Product;
import domain.product.ProductService;

/**
 * Servlet implementation class Controller
 */
@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PersonService personService;
	private ProductService productService;

	/**
	 * @throws SQLException
	 * @see HttpServlet#HttpServlet()
	 */
	public Controller() throws SQLException {
		super();

		personService = new PersonService();
		productService = new ProductService();

		establishDbConnection();

		// //pre-populate, not a good idea when working with a db
		// personService.addPerson(new Person("milan.sanders@ucll.be", "pw001",
		// "Milan", "Sanders"));
		// personService.addPerson(new Person("wouter.dumoulin@ucll.be",
		// "pw002", "Wouter", "Dumoulin"));
		// personService.addPerson(new Person("senne.malcorps@ucll.be", "pw003",
		// "Senne", "Malcorps"));
		//
		// productService.addProduct(new Product("BTO 17CL58",
		// "BTO's main series laptop", 1200));
		// productService.addProduct(new Product("Alienware 17",
		// "High-end alienware laptop", 2400));
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
			forward("register.jsp", request, response);
			break;
		case "addperson_complete":
			processRegister(request, response);
			break;
		case "deleteperson_start":
			request.setAttribute("person",
					personService.getPerson(request.getParameter("mail")));
			forward("deleteperson.jsp", request, response);
			break;
		case "deleteperson_complete":
			processPersonDelete(request, response);
			break;
		case "updateperson_start":
			request.setAttribute("person",
					personService.getPerson(request.getParameter("mail")));
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
					productService.getProduct(request.getParameter("name")));
			forward("deleteproduct.jsp", request, response);
			break;
		case "deleteproduct_complete":
			processProductDelete(request, response);
			break;
		case "updateproduct_start":
			request.setAttribute("product",
					productService.getProduct(request.getParameter("name")));
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

		//		String username = request.getParameter("username");
		//		String password = request.getParameter("passwd");
		//
		//		personService = new PersonService(username, password);
		//		productService = new ProductService(username, password);
		//
		//		String action = request.getParameter("prevaction");
		//
		//		processRequest(action, request, response);
	}

	private void processUserOverview(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Person> persons = personService.getPersons();
		request.setAttribute("persons", persons);
		forward("personoverview.jsp", request, response);
	}

	private void processProductOverview(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		List<Product> products = productService.getProducts();
		request.setAttribute("products", products);
		forward("productoverview.jsp", request, response);
	}

	private void processRegister(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String firstName = request.getParameter("first");
		String lastName = request.getParameter("last");
		String email = request.getParameter("mail");
		String password = request.getParameter("passwd");

		Person person = new Person(email, password, firstName, lastName);
		personService.addPerson(person);
		
		processRequest("home", request, response);
	}

	private void processAddProduct(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String desc = request.getParameter("desc");
		double price = Double.parseDouble(request.getParameter("price"));

		Product product = new Product(name, desc, price);
		productService.addProduct(product);

		processRequest("home", request, response);
	}

	private void processProductDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		productService.deleteProduct(request.getParameter("name"));

		processProductOverview(request, response);
	}

	private void processPersonDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		personService.deletePerson(request.getParameter("mail"));
		processUserOverview(request, response);
	}

	private void processProductUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String desc = request.getParameter("desc");
		double price = Double.parseDouble(request.getParameter("price"));

		Product product = new Product(name, desc, price);
		productService.updateProduct(product);

		processProductOverview(request, response);
	}

	private void processPersonUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String firstName = request.getParameter("first");
		String lastName = request.getParameter("last");
		String email = request.getParameter("mail");
		String password = request.getParameter("passwd");
		byte[] salt = personService.getPerson(email).getSalt();

		Person person = new Person(email, password, salt, firstName, lastName);
		personService.updatePerson(person);

		processUserOverview(request, response);
	}

	private boolean establishDbConnection() {
		try {
			Context env = (Context)new InitialContext().lookup("java:comp/env");

			String username = (String) env.lookup("username");
			String password = (String) env.lookup("password");

			boolean person = personService.establishConnection(username, password);
			boolean product = productService.establishConnection(username, password);

			return person && product;
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return false;
	}

	private void forward(String destination, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String style = getStyle(request);
		request.setAttribute("style", style);
		request.getRequestDispatcher(destination).forward(request, response);
	}
}
