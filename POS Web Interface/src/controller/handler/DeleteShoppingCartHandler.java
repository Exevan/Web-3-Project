package controller.handler;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import annotation.RequestMapping;

@RequestMapping(action="deleteshoppingcart")
class DeleteShoppingCartHandler extends Handler {

	@Override
	public String handleRequest(String action, HttpServletRequest request,
			HttpServletResponse response) {
		List<String> errors = new ArrayList<String>();
		List<String> values = new ArrayList<String>();
		values.add("");

		int productId = Integer.parseInt(request.getParameter("id"));
		String userId = getUser(request).getUserId();

		if (!errors.isEmpty()) {
			request.setAttribute("errors", errors);
			request.setAttribute("values", values);
		} else {
			webshopFacade.getCartFromUser(userId).alterProductWithIndex(
					productId, 0);
		}

		return "cartoverview";
	}

}
