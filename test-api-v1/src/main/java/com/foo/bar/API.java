package com.foo.bar;

import org.adoptopenjdk.sigtest.api.Stable;

/**
 * @author Dmitry Buzdin
 */
public class API {

    public void m1() {}

    @Stable
    public void m2() {}

}
