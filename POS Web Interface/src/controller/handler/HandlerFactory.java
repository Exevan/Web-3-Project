package controller.handler;

import java.lang.reflect.Constructor;

import db.WebshopFacade;

public class HandlerFactory {
	private final WebshopFacade facade;
	
	public HandlerFactory(WebshopFacade facade) {
		this.facade = facade;
	}
	
	public Handler createHandler(HandlerType type) {
		Handler handler = null;
		try {
			Class<?> handlerClass = Class.forName(type.getClassPath());
			Constructor<?> handlerConstructor = handlerClass.getConstructor(WebshopFacade.class);
			handler = (Handler)handlerConstructor.newInstance(facade);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return handler;
	}
}
