package test;


import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Set;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;

/**
 * @author Oliverio
 */
    public class TestListClasses {  
        public static void scan(ClassLoader classLoader, Set<String> locations,  
                Set<String> packages) {  
            if (!(classLoader instanceof URLClassLoader)) {  
                return;  
            }  
      
            URLClassLoader urlLoader = (URLClassLoader) classLoader;  
            URL[] urls = urlLoader.getURLs();  
      
            for (URL url : urls) {  
                String path = url.getFile();  
                File location = null;  
                try {  
                    location = new File(url.toURI());  
                } catch (URISyntaxException e) {  
                    e.printStackTrace();  
                    return;  
                }  
      
                // Only process the URL if it matches one of our filter strings  
                if (matchesAny(path, locations)) {  
                    if (location.isDirectory()) {  
                        getClassesInDirectory(null, location, packages);  
                    } else {  
                        getClassesInJar(location, packages);  
                    }  
                }  
            }  
        }  
      
        public static void getClassesInDirectory(String parent, File location,  
                Set<String> packagePatterns) {  
            File[] files = location.listFiles();  
            StringBuilder builder = null;  
      
            for (File file : files) {  
                builder = new StringBuilder(100);  
                builder.append(parent).append("/").append(file.getName());  
                String packageOrClass = (parent == null ? file.getName() : builder  
                        .toString());  
      
                if (file.isDirectory()) {  
                    getClassesInDirectory(packageOrClass, file, packagePatterns);  
                } else if (file.getName().endsWith(".class")) {  
                    if (matchesAny(packageOrClass, packagePatterns)) {  
                        System.out.println(packageOrClass);  
                    }  
                }  
            }  
        }  
      
        public static void getClassesInJar(File location,  
                Set<String> packagePatterns) {  
            try {  
                JarFile jar = new JarFile(location);  
                Enumeration entries = jar.entries();  
      
                while (entries.hasMoreElements()) {  
                    ZipEntry entry = (ZipEntry) entries.nextElement();  
                    String name = entry.getName();  
                    if (!entry.isDirectory() && name.endsWith(".class")) {  
                        if (matchesAny(name, packagePatterns)) {  
                            System.out.println(name);  
                        }  
                    }  
                }  
            } catch (IOException ioe) {  
                ioe.printStackTrace();  
            }  
        }  
      
        public static boolean matchesAny(String text, Set<String> filters) {  
            if (filters == null || filters.size() == 0) {  
                return true;  
            }  
            for (String filter : filters) {  
                if (text.indexOf(filter) != -1) {  
                    return true;  
                }  
            }  
            return false;  
        }  
      
        public static File[] getPackageContent(String packageName) throws IOException{
            ArrayList<File> list = new ArrayList<File>();
            Enumeration<URL> urls = Thread.currentThread().getContextClassLoader().getResources(packageName);
            while (urls.hasMoreElements()) {
                URL url = urls.nextElement();
                File dir = new File(url.getFile());
                for (File f : dir.listFiles()) {
                    list.add(f);
                }
            }
            return list.toArray(new File[]{});
        }
        
        public static void main(String[] args) {
        	
        	
            scan(Thread.currentThread().getContextClassLoader(),  
                   Collections.EMPTY_SET, Collections.EMPTY_SET);  
        
   try {
	File [] f = getPackageContent("/Framework/src/control");
	System.out.println(f.length);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
        
        }  
    }  