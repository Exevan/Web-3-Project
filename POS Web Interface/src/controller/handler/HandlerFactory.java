package controller.handler;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import db.WebshopFacade;

public class HandlerFactory {
	private final WebshopFacade facade;
	
	public HandlerFactory(WebshopFacade facade) {
		this.facade = facade;
	}
	
	private void initializeHandlerMapping() throws IOException {
	    File[] files = getAllFiles("controller.handler");
	}
	
	private File[] getAllFiles(String packageName) throws IOException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        String path = packageName.replace(".", System.getProperty("file.separator"));
        URL fullPath = classLoader.getResource(path);
        File folder = new File(fullPath.getFile());
        return folder.listFiles();
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
