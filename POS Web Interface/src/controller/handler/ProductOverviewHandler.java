package controller.handler;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import annotation.RequestMapping;
import domain.product.Product;

@RequestMapping(action="productoverview")
public class ProductOverviewHandler extends Handler {

	@Override
	public String handleRequest(String action, HttpServletRequest request,
			HttpServletResponse response) {
		List<Product> products = webshopFacade.getProducts();
		request.setAttribute("products", products);
		return "productoverview.jsp";
	}

}
