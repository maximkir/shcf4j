package org.maximkir.shcf4j.test.auth;

import java.security.Principal;

public interface Credentials {

    Principal getUserPrincipal();

    String getPassword();

}
