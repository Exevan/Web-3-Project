package controller.handler;

import java.io.IOException;
import java.lang.reflect.AnnotatedType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import util.ClassFinder;
import annotation.RequestMapping;
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
			RequestMapping annotation = klass.getAnnotation(RequestMapping.class);
			if (annotation != null)
				requestMapping.put(annotation.action(), klass);
		}
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
}
