package org.adoptopenjdk.sigtest;

import com.google.common.base.Joiner;
import com.google.common.base.Throwables;
import com.sun.tdk.signaturetest.Setup;
import org.adoptopenjdk.sigtest.api.Stable;
import org.reflections.Reflections;
import org.reflections.util.ConfigurationBuilder;
import org.scannotation.AnnotationDB;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

/**
 * @author Dmitry Buzdin
 */
public class SigfileCreate extends Setup {

    public static void main(String[] args) {


        URL[] urls = prepareUrls();
        Entries entries = scanForAnnotations(urls);
        scanClassPath(urls);

        for (String type : entries.getTypes()) {
            // TODO add options
        }

        SigfileCreate create = new SigfileCreate();
        String[] sigArgs = {
                FILENAME_OPTION, Stuff.SIGFILE,
                APIVERSION_OPTION, "1",
                CLOSEDFILE_OPTION,
                CLASSPATH_OPTION, Joiner.on(":").join(Stuff.CLASSPATH_V1),
                PACKAGE_OPTION, Stuff.PACKAGE
        };
        create.run(sigArgs, new PrintWriter(System.err, true), null);
        create.exit();
    }

    private static Entries scanForAnnotations(URL[] urls) {
        AnnotationDB db = new AnnotationDB();
        try {
            db.scanArchives(urls);
        } catch (IOException e) {
            throw Throwables.propagate(e);
        }

        Map<String,Set<String>> annotationIndex = db.getAnnotationIndex();
        Set<String> types = annotationIndex.get(Stable.class.getName());
        return new Entries(types, Collections.<String>emptyList());
    }

    private static URL[] prepareUrls() {

        // TODO filter rt.jar
        URL[] urls = new URL[Stuff.CLASSPATH_V1.length];
        String[] classpath_v1 = Stuff.CLASSPATH_V1;
        for (int i = 0; i < classpath_v1.length; i++) {
            String jar = classpath_v1[i];
            try {
                urls[i] = new URL("file:" + jar);
            } catch (MalformedURLException e) {
                throw Throwables.propagate(e);
            }
        }
        return urls;
    }

    private static void scanClassPath(URL[] urls) {
        Reflections reflections = new Reflections(new ConfigurationBuilder()
                .setUrls(urls));
        Set<Class<?>> types = reflections.getTypesAnnotatedWith(Stable.class);
        for (Class<?> type : types) {
            System.out.println(type.getName());
        }
    }

    public static class Entries {

        Iterable<String> types;
        Iterable<String> packages;

        public Entries(Iterable<String> types, Iterable<String> packages) {
            this.types = types;
            this.packages = packages;
        }

        public Iterable<String> getTypes() {
            return types;
        }

        public Iterable<String> getPackages() {
            return packages;
        }

    }

}
