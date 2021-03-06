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

import controller.handler.Handler;
import controller.handler.HandlerFactory;
import domain.NotAuthorizedException;
import domain.WebshopFacade;
import domain.person.Person;
import domain.person.Role;
import domain.product.Product;
import domain.shoppingcartproduct.ShoppingCartProduct;

//test line

/**
 * Servlet implementation class Controller
 */
@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HandlerFactory handlerFactory;
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
		try {
			handlerFactory = new HandlerFactory(webshopFacade, properties.getProperty("path"));
		} catch (IOException e) {
			e.printStackTrace();
		}
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
		case "home":
			forward("index.jsp", request, response);
			break;		
			
		case "change_style":
			changeStyle(request, response);
			String origin = request.getParameter("origin");
			processRequest(origin, request, response);
			break;
			
		default: 
			Handler handler;
			try {
				handler = handlerFactory.createHandler(action);
				String newAction = handler.handleRequest(action, request, response);
				if (isRedirectEndpoint(newAction))
					forward(newAction, request, response);
				else
					processRequest(newAction, request, response);
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			} catch (NotAuthorizedException e) {
				List<String> errors = new ArrayList<>();
				errors.add(e.getMessage());
				request.setAttribute("errors", errors);
				forward("index.jsp", request, response);
			} catch (NumberFormatException e) {
				List<String> errors = new ArrayList<>();
				errors.add(e.getMessage());
				request.setAttribute("errors", errors);
				forward("index.jsp", request, response);
			}
		}			
	}
	
	private boolean isRedirectEndpoint(String action) {
		return action.endsWith(".jsp") || action.endsWith(".html");
	}
	

	@SuppressWarnings("unused")
	@Deprecated
	private void processRequest_old(String action, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		action = (action == null) ? "home" : action;

		try {

			switch (action) {

			case "login_start":
				forward("login.jsp", request, response);
				break;
			case "login":
				// DONE
				processLogin(request, response);
				break;
			case "logout":
				// DONE
				processLogout(request, response);
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
						webshopFacade.getProduct(Integer.parseInt(request
								.getParameter("id"))));
				forward("deleteproduct.jsp", request, response);
				break;
			case "deleteproduct_complete":
				processProductDelete(request, response);
				break;
			case "updateproduct_start":
				request.setAttribute("product",
						webshopFacade.getProduct(Integer.parseInt(request
								.getParameter("id"))));
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
			case "addtocart":
				processAddToCart(request, response);
				break;
			case "cartoverview":
				processCartOverview(request, response);
				break;
			case "updateshoppingcart":
				processUpdateShoppingcart(request, response);
				break;
			case "deleteshoppingcart":
				processDeleteShoppingcart(request, response);
				break;
			}
		} catch (NotAuthorizedException e) {
			List<String> errors = new ArrayList<>();
			errors.add(e.getMessage());
			request.setAttribute("errors", errors);
			forward("index.jsp", request, response);
		} catch (NumberFormatException e) {
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
		List<String> errors = new ArrayList<String>();
		List<String> values = new ArrayList<String>();
		values.add("");

		String username = request.getParameter("username");
		String password = request.getParameter("passwd");
		Person user = webshopFacade.getPerson(username);

		if (user == null) {
			errors.add("A user with this email does not exist");
		} else if (user.isCorrectPassword(password)) {
			request.getSession().setAttribute("user", user);
			String userId = user.getUserId();
			if (webshopFacade.getCartFromUser(userId) == null) {
				webshopFacade.createCart(userId);
			}
		} else {
			values.set(0, username);
			errors.add("The password is incorrect");
		}

		if (errors.size() > 0) {
			request.setAttribute("errors", errors);
			request.setAttribute("values", values);
			processRequest("login_start", request, response);
		} else
			processRequest("home", request, response);
	}

	private void processLogout(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.getSession().removeAttribute("user");
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

	private void processCartOverview(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String userId = getUser(request).getUserId();
		List<ShoppingCartProduct> products = webshopFacade.getCartFromUser(
				userId).getProducts();
		request.setAttribute("products", products);
		forward("cartoverview.jsp", request, response);
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
		List<String> errors = new ArrayList<String>();
		List<String> values = new ArrayList<String>();
		values.add("");
		values.add("");
		values.add("");
		values.add("");

		int id = 0;
		try {
			String raw_id = request.getParameter("id");
			id = Integer.parseInt(raw_id);
			Product.isValidId(id);
			values.set(0, raw_id);
		} catch (IllegalArgumentException e) {
			errors.add(e.getMessage());
		}

		String name = "";
		try {
			name = request.getParameter("name");
			Product.isValidName(name);
			values.set(1, name);
		} catch (IllegalArgumentException e) {
			errors.add(e.getMessage());
		}

		String desc = "";
		try {
			desc = request.getParameter("desc");
			Product.isValidDescription(desc);
			values.set(2, desc);
		} catch (IllegalArgumentException e) {
			errors.add(e.getMessage());
		}

		double price = 0;
		try {
			String raw_price = request.getParameter("price");
			price = Double.parseDouble(raw_price);
			Product.isValidPrice(price);
			values.set(3, Double.toString(price));
		} catch (NumberFormatException e1) {
			errors.add(e1.getMessage());

		} catch (IllegalArgumentException e2) {
			errors.add(e2.getMessage());
		}

		try {
			if (errors.size() == 0)
				webshopFacade.addProduct(new Product(id, name, desc, price));
		} catch (IllegalArgumentException e) {
			errors.add(e.getMessage());
		}

		String action = "home";

		if (!errors.isEmpty()) {
			request.setAttribute("errors", errors);
			action = "addproduct_start";
		}

		request.setAttribute("values", values);
		processRequest(action, request, response);
	}

	private void processProductDelete(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		webshopFacade
				.deleteProduct(Integer.parseInt(request.getParameter("id")));
		processProductOverview(request, response);
	}

	private void processPersonDelete(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		webshopFacade.deletePerson(request.getParameter("mail"));
		processUserOverview(request, response);
	}

	private void processProductUpdate(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		List<String> errors = new ArrayList<String>();
		List<String> values = new ArrayList<String>();
		values.add("");
		values.add("");
		values.add("");

		int id = Integer.parseInt(request.getParameter("id"));

		String name = "";
		try {
			name = request.getParameter("name");
			Product.isValidName(name);
			values.set(0, name);
		} catch (IllegalArgumentException e) {
			errors.add(e.getMessage());
		}

		String desc = "";
		try {
			desc = request.getParameter("desc");
			Product.isValidDescription(desc);
			values.set(1, desc);
		} catch (IllegalArgumentException e) {
			errors.add(e.getMessage());
		}

		double price = 0;
		try {
			String raw_price = request.getParameter("price");
			price = Double.parseDouble(raw_price);
			Product.isValidPrice(price);
			values.set(2, Double.toString(price));
		} catch (NumberFormatException e1) {
			errors.add(e1.getMessage());

		} catch (IllegalArgumentException e2) {
			errors.add(e2.getMessage());
		}

		try {
			if (errors.size() == 0)
				webshopFacade.updateProduct(new Product(id, name, desc, price));
		} catch (IllegalArgumentException e) {
			errors.add(e.getMessage());
		}

		String action = "home";

		if (!errors.isEmpty()) {
			request.setAttribute("errors", errors);
			action = "updateproduct_start";
		}

		request.setAttribute("values", values);
		processRequest(action, request, response);
	}

	private void processPersonUpdate(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
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

		String action = "home";

		if (!errors.isEmpty()) {
			request.setAttribute("errors", errors);
			action = "updateperson_start";
		}

		request.setAttribute("values", values);
		processRequest(action, request, response);
	}

	private void processAddToCart(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		List<String> errors = new ArrayList<String>();
		List<String> values = new ArrayList<String>();
		values.add("");

		int productId = Integer.parseInt(request.getParameter("id"));
		String userId = getUser(request).getUserId();

		int quantity = 0;
		try {
			String raw_quantity = request.getParameter("quantity");
			quantity = Integer.parseInt(raw_quantity);
			Product.isValidQuantity(quantity);
			values.set(0, raw_quantity);
		} catch (NumberFormatException e1) {
			errors.add("Please input a valid number");
		} catch (IllegalArgumentException e2) {
			errors.add(e2.getMessage());
		}

		Product product = webshopFacade.getProduct(productId);
		if (product == null) {
			errors.add("The product does not exist");
		}

		if (!errors.isEmpty()) {
			request.setAttribute("errors", errors);
			request.setAttribute("values", values);
		} else {
			webshopFacade.addProductToCartFromUser(userId, product, quantity);
		}

		processRequest("productoverview", request, response);
	}

	private void processDeleteShoppingcart(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		List<String> errors = new ArrayList<String>();
		List<String> values = new ArrayList<String>();
		values.add("");

		int productId = Integer.parseInt(request.getParameter("id"));
		String userId = getUser(request).getUserId();

		if (!errors.isEmpty()) {
			request.setAttribute("errors", errors);
			request.setAttribute("values", values);
		} else {
			webshopFacade.getCartFromUser(userId).alterProduct(
					productId, 0);
		}

		processRequest("cartoverview", request, response);
	}

	private void processUpdateShoppingcart(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		List<String> errors = new ArrayList<String>();
		List<String> values = new ArrayList<String>();
		values.add("");

		int productId = Integer.parseInt(request.getParameter("id"));
		String userId = getUser(request).getUserId();

		int quantity = 0;
		try {
			String raw_quantity = request.getParameter("quantity");
			quantity = Integer.parseInt(raw_quantity);
			if (quantity < 0)
				errors.add("quantity must be greater than or equal to 0");
			else
				values.set(0, raw_quantity);
		} catch (NumberFormatException e1) {
			errors.add("Please input a valid number");
		} catch (IllegalArgumentException e2) {
			errors.add(e2.getMessage());
		}

		if (!errors.isEmpty()) {
			request.setAttribute("errors", errors);
			request.setAttribute("values", values);
		} else {
			webshopFacade.getCartFromUser(userId).alterProduct(
					productId, quantity);
		}

		processRequest("cartoverview", request, response);
	}

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
		if (user != null) {
			request.setAttribute("username", user.getFirstName());
			request.setAttribute("cartamount",
					webshopFacade.getTotalQtyFromUser(user.getUserId()));
		}

		String style = getStyle(request);
		request.setAttribute("style", style);
		request.getRequestDispatcher(destination).forward(request, response);
	}
}
