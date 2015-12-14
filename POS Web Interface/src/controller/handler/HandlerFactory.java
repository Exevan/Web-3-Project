package controller.handler;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import util.ClassFinder;
import annotation.RequestMapping;
import annotation.RequestMappings;
import domain.WebshopFacade;

public class HandlerFactory {

	private final ClassFinder classFinder;
	private Map<String, Class<?>> requestMapping;
	private final WebshopFacade facade;

	public HandlerFactory(WebshopFacade facade) throws IOException {
		this.facade = facade;
		this.requestMapping = new HashMap<String, Class<?>>();
		this.classFinder = new ClassFinder();
		initializeHandlerMapping();
	}

	private void initializeHandlerMapping() throws IOException {
		List<Class<?>> classes = classFinder.getAllClasses("controller.handler");
		for (Class<?> klass : classes) {
			RequestMappings annotations = klass.getAnnotation(RequestMappings.class);
			if (annotations != null) {
				for(RequestMapping annotation : annotations.value()) {
					requestMapping.put(annotation.action(), klass);
				}
			} else if (klass.getAnnotation(RequestMapping.class) != null){
				RequestMapping annotation = klass.getAnnotation(RequestMapping.class);
					requestMapping.put(annotation.action(), klass);

			}
		}
	}

	public Handler createHandler(String action) throws InstantiationException, IllegalAccessException {
		Class<?> klass = requestMapping.get(action);
		Handler handler = (Handler) klass.newInstance();
		handler.setWebshopFacade(facade);
		return handler;
	}

	//	public Handler createHandler(HandlerType type) {
	//		Handler handler = null;
	//		try {
	//			Class<?> handlerClass = Class.forName(type.getClassPath());
	//			Constructor<?> handlerConstructor = handlerClass.getConstructor(WebshopFacade.class);
	//			handler = (Handler)handlerConstructor.newInstance(facade);
	//		} catch (Exception e) {
	//			e.printStackTrace();
	//		}
	//		return handler;
	//	}
	
	@Override
	public String toString() {
		String reqmap = "";
		for(String req : requestMapping.keySet()) {
			reqmap += req += requestMapping.get(req).toString() + "/n";
		}
		return reqmap;
	}
}
