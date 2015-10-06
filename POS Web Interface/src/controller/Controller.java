package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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
		String action = request.getParameter("action");
		action = (action == null) ? "home" : action;
		try {
			switch (action) {

			case "useroverview":
				processUserOverview(request, response);
				break;
			case "productoverview":
				processProductOverview(request, response);
				break;
			case "signUp": // go to the page with the form
				request.getRequestDispatcher("register.html").forward(request,
						response);
				break;
			case "register": 
				processRegister(request, response);
				break;
			case "home":
				request.getRequestDispatcher("index.html").forward(request,
						response);
				break;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage()); // very dirty, TODO ask lectors what do?
		}
	}

	private void processUserOverview(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SQLException {
		List<Person> persons = personService.getPersons();
		request.setAttribute("persons", persons);
		request.getRequestDispatcher("useroverview.jsp").forward(request,
				response);
	}

	private void processProductOverview(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException, SQLException {
		List<Product> products = productService.getProducts();
		request.setAttribute("products", products);
		request.getRequestDispatcher("productoverview.jsp").forward(request,
				response);
	}

	private void processRegister(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException, SQLException {
		// This is where the actual registration takes place
		// init
		String firstName,
		lastName,
		email,
		password;
		// grab stuff from request
		firstName = request.getParameter("first");
		lastName = request.getParameter("last");
		email = request.getParameter("mail");
		password = request.getParameter("passwd");
		// create, also errors get thrown
		Person person = new Person(email, password, firstName, lastName);
		personService.addPerson(person);
		// go back home by falling through the case, clever huh?
	}

}
