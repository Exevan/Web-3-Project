package controller.handler;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import annotation.RequestMapping;
import domain.product.Product;

@RequestMapping(action = "updateproduct_start")
@RequestMapping(action = "updateproduct_complete")
public class UpdateProductHandler extends Handler {
	@Override
	public String handleRequest(String action, HttpServletRequest request,
			HttpServletResponse response) {
		
		if(action.equals("updateproduct_start")) {
			request.setAttribute("product",
					webshopFacade.getProduct(Integer.parseInt(request
							.getParameter("id"))));
			return "updateproduct.jsp";
		}
		
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

		if (!errors.isEmpty()) {
			request.setAttribute("values", values);
			request.setAttribute("errors", errors);
			return "updateproduct_start";
		}

		List<Product> products = webshopFacade.getProducts();
		request.setAttribute("products", products);
		return "productoverview";
	}

}
