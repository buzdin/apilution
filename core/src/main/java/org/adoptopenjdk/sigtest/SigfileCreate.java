package org.adoptopenjdk.sigtest;

import com.sun.tdk.signaturetest.Setup;

import java.io.PrintWriter;

/**
 * @author Dmitry Buzdin
 */
public class SigfileCreate extends Setup {

    public static void main(String[] args) {
        SigfileCreate create = new SigfileCreate();
        String[] sigArgs = {
                FILENAME_OPTION, Stuff.SIGFILE,
                APIVERSION_OPTION, "1",
                CLOSEDFILE_OPTION,
                CLASSPATH_OPTION, Stuff.CLASSPATH_V1,
                PACKAGE_OPTION, Stuff.PACKAGE

        };
        create.run(sigArgs, new PrintWriter(System.err, true), null);
        create.exit();
    }

}
