package controller.handler;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import annotation.RequestMapping;
import domain.product.Product;

@RequestMapping(action="addtocart")
public class AddToCartHandler extends Handler {

	@Override
	public String handleRequest(String action, HttpServletRequest request,
			HttpServletResponse response) {
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

		return "productoverview";
	}
}
