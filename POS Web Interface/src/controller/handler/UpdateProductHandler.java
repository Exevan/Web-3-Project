package controller.handler;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import annotation.RequestMapping;
import db.WebshopFacade;
import domain.product.Product;

@RequestMapping(action="updateproduct_complete")
public class UpdateProductHandler extends Handler {

	public UpdateProductHandler(WebshopFacade webshopFacade) {
		super(webshopFacade);
	}

	@Override
	public String handleRequest(HttpServletRequest request,
			HttpServletResponse response) {
		List<String> errors = new ArrayList<String>();
		List<String> values = new ArrayList<String>();
		values.add("");
		values.add("");

		String name = request.getParameter("name"); // this should be okay
													// because server supplied
													// this

		String desc = "";
		try {
			desc = request.getParameter("desc");
			Product.isValidDescription(desc);
			values.set(0, desc);
		} catch (IllegalArgumentException e) {
			errors.add(e.getMessage());
		}

		double price = 0;
		try {
			String raw_price = request.getParameter("price");
			price = Double.parseDouble(raw_price);
			Product.isValidPrice(price);
			values.set(1, Double.toString(price));
		} catch (NumberFormatException e1) {
			errors.add(e1.getMessage());

		} catch (IllegalArgumentException e2) {
			errors.add(e2.getMessage());
		}

		try {
			if (errors.size() == 0)
				webshopFacade.updateProduct(new Product(name, desc, price));
		} catch (IllegalArgumentException e) {
			errors.add(e.getMessage());
		}

		if (!errors.isEmpty()) {
			request.setAttribute("values", values);
			request.setAttribute("errors", errors);
			return "updateproduct.jsp";
		}

		List<Product> products = webshopFacade.getProducts();
		request.setAttribute("products", products);
		return "productoverview.jsp";		
	}

}
