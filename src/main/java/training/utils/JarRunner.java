package training.utils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.jar.Attributes;

public class JarRunner {
    public static void runjar(File jarFile, String[] args) throws Exception {
        URLClassLoader cl = new URLClassLoader(new URL[] {jarFile.toURL()}, ClassLoader.getSystemClassLoader().getParent());
        String mainClassName = getMainClassName(new URL("jar", "", jarFile.toURL().toString() + "!/"));
        invokeMain(cl, mainClassName, args);
    }

    private static String getMainClassName(URL u) throws IOException {
        JarURLConnection uc = (JarURLConnection)u.openConnection();
        Attributes attr = uc.getMainAttributes();
        return attr != null ? attr.getValue(Attributes.Name.MAIN_CLASS) : null;
    }

    private static void invokeMain(URLClassLoader cl, String mainClassName, String[] args) throws Exception {
        Class c = cl.loadClass(mainClassName);
        Method m = c.getMethod("main", new Class[] { args.getClass() });
        m.setAccessible(true);
        int mods = m.getModifiers();
        if (m.getReturnType() != void.class || !Modifier.isStatic(mods) || !Modifier.isPublic(mods)) {
            throw new NoSuchMethodException("main");
        }
        m.invoke(null, new Object[] { args });
    }
}
