package org.adoptopenjdk.sigtest;

import com.google.common.base.Joiner;
import com.sun.tdk.signaturetest.SignatureTest;
import com.sun.tdk.signaturetest.model.ClassDescription;

import java.io.PrintWriter;

/**
 * @author Dmitry Buzdin
 */
public class Verification extends SignatureTest {

    public static void main(String[] args) {
        Verification verification = new Verification();
        String[] a = {
                MODE_OPTION, "bin",
                FILENAME_OPTION, Stuff.SIGFILE,
                APIVERSION_OPTION, "2",
                FORMATHUMAN_OPTION,
                BACKWARD_OPTION,
                STATIC_OPTION,
                CLASSPATH_OPTION, Joiner.on(":").join(Stuff.CLASSPATH_V2),
                PACKAGE_OPTION, Stuff.PACKAGE
        };
        verification.run(a, new PrintWriter(System.err, true), null);
        verification.exit();
    }

    @Override
    protected void verifyClass(ClassDescription required, ClassDescription found) {
        super.verifyClass(required, found);
    }

}
