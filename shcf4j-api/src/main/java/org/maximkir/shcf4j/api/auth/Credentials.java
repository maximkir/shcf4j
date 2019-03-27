package org.maximkir.shcf4j.api.auth;

import java.security.Principal;

public interface Credentials {

    Principal getUserPrincipal();

    String getPassword();

}
