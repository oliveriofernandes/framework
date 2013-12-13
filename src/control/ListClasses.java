package control;

import interfaces.Visible;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import cFAlgsImpl.ItemBasedClassicStrategy;


import util.Visibility;

/**
 * @author Oliverio
 * 
 */
public class ListClasses {

	@SuppressWarnings("unchecked")
	public static Class[] getClassesByPkg(String packageName) throws ClassNotFoundException,
			IOException {

		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		assert classLoader != null;
		
		Package [] p = Package.getPackages();
		
		String path = packageName.replace('.', '/');
		Enumeration<URL> resources = classLoader.getResources(path);
		List<File> dirs = new ArrayList<File>();

		while (resources.hasMoreElements()) {
			URL resource = resources.nextElement();
			dirs.add(new File(resource.getFile()));
		}
		ArrayList<Class> classes = new ArrayList<Class>();
		for (File directory : dirs) {
			classes.addAll(findClasses(directory, packageName));
		}
		return classes.toArray(new Class[classes.size()]);
	}

	@SuppressWarnings("unchecked")
	private static List<Class> findClasses(File directory, String packageName)
			throws ClassNotFoundException {

		List<Class> classes = new ArrayList<Class>();
		if (!directory.exists()) {
			return classes;
		}

		File[] files = directory.listFiles();
		for (File file : files) {
			if (file.isDirectory()) {
				assert !file.getName().contains(".");
				classes.addAll(findClasses(file, packageName + "." + file.getName()));
			} else if (file.getName().endsWith(".class")) {
				classes.add(Class.forName(packageName + '.'
						+ file.getName().substring(0, file.getName().length() - 6)));
			}
		}
		return classes;
	}

	@SuppressWarnings("unchecked")
	public static String[] getClassNamesByPkg(String packageName, boolean presentedAnnotation, boolean addEmptyInReturnValue)
			throws ClassNotFoundException, IOException {
		Class[] getClassesByPkg = getClassesByPkg(packageName);
		List<String> classNames = new ArrayList<String>();
		if (addEmptyInReturnValue){
			classNames.add("");
		}

		if (!presentedAnnotation) {

			for (Class<Visible> klass : getClassesByPkg) {
				if (klass.isAnnotationPresent(Visibility.class))
					classNames.add(klass.getAnnotation(Visibility.class).name());
				else
					classNames.add(klass.getSimpleName());
			}

		} else {
			for (Class<Visible> klass : getClassesByPkg) {
				if (klass.isAnnotationPresent(Visibility.class))
					classNames.add(klass.getAnnotation(Visibility.class).name());

			}
		}
		return classNames.toArray(new String[classNames.size()]);
	}

	public static Class getClassByPresentedAnnotation(String anotationName, String packageName) {
		Class[] arrayClassNames;
		try {
			arrayClassNames = ListClasses.getClassesByPkg(packageName);

			for (int i = 0; i < arrayClassNames.length; i++) {
				if (anotationName != null) {
					if (arrayClassNames[i].isAnnotationPresent(Visibility.class)) {
						if ((boolean) ((Visibility) arrayClassNames[i]
								.getAnnotation(Visibility.class)).name().equals(anotationName))
							return arrayClassNames[i];

					}
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

	}
}
