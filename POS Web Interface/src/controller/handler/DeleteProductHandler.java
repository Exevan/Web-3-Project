package controller.handler;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import annotation.RequestMapping;
import domain.product.Product;

@RequestMapping(action="deleteproduct_start")
@RequestMapping(action="deleteproduct_complete")
public class DeleteProductHandler extends Handler {

	@Override
	public String handleRequest(String action, HttpServletRequest request,
			HttpServletResponse response) {
		if(action.equals("deleteproduct_start")) {
			request.setAttribute("product",
					webshopFacade.getProduct(Integer.parseInt(request
							.getParameter("id"))));
			return "deleteproduct.jsp";
		}		
		
		webshopFacade.deleteProduct(Integer.parseInt(request.getParameter("id")));
		
		List<Product> products = webshopFacade.getProducts();
		request.setAttribute("products", products);
		return "productoverview";
		
	}
	
	
}
