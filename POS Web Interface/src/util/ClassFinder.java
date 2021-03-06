package util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ClassFinder {
	
	private static final char DOT = '.';
	private static final String SLASH = System.getProperty("file.separator");
	private static final String CLASS_SUFFIX = ".class";

	public List<Class<?>> getAllClasses(String packageName, String path) throws IOException {
		//ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		//String path = packageName.replace(".", SLASH);
		//String fullpath = "D:\\Dev\\Eclipse\\workspace\\Web-3-Project\\POS Web Interface\\bin\\controller\\handler";
		//URL fullPath = classLoader.getResource(path);
		File folder = new File(path);
		List<Class<?>> classes = new ArrayList<Class<?>>();
		File[] files = folder.listFiles();
		for (File file : files) {
			classes.addAll(find(file, packageName));
		}
		return classes;
	}

	private List<Class<?>> find(File file, String packageName) {
		List<Class<?>> classes = new ArrayList<Class<?>>();
		String resource = packageName + DOT + file.getName();
		if (file.isDirectory()) {
			for (File child : file.listFiles()) {
				classes.addAll(find(child, resource));
			}
		} else if (resource.endsWith(CLASS_SUFFIX)) {
			int endIndex = resource.length() - CLASS_SUFFIX.length();
			String className = resource.substring(0, endIndex);
			try {
				classes.add(Class.forName(className));
			} catch (ClassNotFoundException ignore) {}
		}
		return classes;
	}
}
