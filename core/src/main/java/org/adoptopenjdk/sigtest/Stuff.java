package org.adoptopenjdk.sigtest;

/**
 * @author Dmitry Buzdin
 */
public class Stuff {

    public static final String SIGFILE = "sigfile.sig";

    public static final String PACKAGE = "com.foo.bar";

    public static final String[] CLASSPATH_V1 = new String[]{
            "/Library/Java/JavaVirtualMachines/jdk1.7.0_51.jdk/Contents/Home/jre/lib/rt.jar",
            "test-api-v1/target/test-api-v1-1.0.0-SNAPSHOT.jar",
            "apilution-api/target/apilution-api-1.0.0-SNAPSHOT.jar"
    };

    public static final String[] CLASSPATH_V2 = new String[]{
            "/Library/Java/JavaVirtualMachines/jdk1.7.0_51.jdk/Contents/Home/jre/lib/rt.jar",
            "test-api-v2/target/test-api-v2-1.0.0-SNAPSHOT.jar",
            "apilution-api/target/apilution-api-1.0.0-SNAPSHOT.jar"
    };

}
