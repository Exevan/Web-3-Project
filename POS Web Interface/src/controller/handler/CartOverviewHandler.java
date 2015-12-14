package controller.handler;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import annotation.RequestMapping;
import domain.product.ShoppingCartProduct;

@RequestMapping(action="cartoverview")
public class CartOverviewHandler extends Handler {

	@Override
	public String handleRequest(String action, HttpServletRequest request,
			HttpServletResponse response) {
		String userId = getUser(request).getUserId();
		List<ShoppingCartProduct> products = webshopFacade.getCartFromUser(
				userId).getProducts();
		request.setAttribute("products", products);
		return "cartoverview.jsp";
	}
}
