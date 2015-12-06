package controller.handler;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import annotation.RequestMapping;
import domain.WebshopFacade;
import domain.product.Product;

@RequestMapping(action="addproduct_complete")
public class AddProductHandler extends Handler {

	public AddProductHandler(WebshopFacade webshopFacade) {
		super(webshopFacade);
	}

	@Override
	public String handleRequest(HttpServletRequest request,
			HttpServletResponse response) {
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
		return action;
	}

}
