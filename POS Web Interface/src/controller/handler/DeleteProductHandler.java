package controller.handler;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import annotation.RequestMapping;
import db.WebshopFacade;
import domain.product.Product;

@RequestMapping(action="deleteproduct_complete")
public class DeleteProductHandler extends Handler {

	public DeleteProductHandler(WebshopFacade webshopFacade) {
		super(webshopFacade);
	}

	@Override
	public String handleRequest(HttpServletRequest request,
			HttpServletResponse response) {
		webshopFacade.deleteProduct(Integer.parseInt(request.getParameter("id")));
		
		List<Product> products = webshopFacade.getProducts();
		request.setAttribute("products", products);
		return "productoverview.jsp";
		
	}
	
	
}
