package util;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ClassFinder {

	public List<Class<?>> getAllClasses(String packageName) throws IOException {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		String path = packageName.replace(".", System.getProperty(SLASH));
		URL fullPath = classLoader.getResource(path);
		File folder = new File(fullPath.getFile());
		List<Class<?>> classes = new ArrayList<Class<?>>();
		for (File file : folder.listFiles()) {
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

	private static final char DOT = '.';
	private static final String SLASH = System.getProperty("file.separator");
	private static final String CLASS_SUFFIX = ".class";

}
