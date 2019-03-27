package org.maximkir.shcf4j.api.httpcomponents.client4;

import org.apache.http.auth.Credentials;

import java.security.Principal;

class CredentialsAdapter implements Credentials {

    private final org.maximkir.shcf4j.api.auth.Credentials credentials;

    public CredentialsAdapter(org.maximkir.shcf4j.api.auth.Credentials credentials) {
        this.credentials = credentials;
    }

    public Principal getUserPrincipal() {
        return this.credentials.getUserPrincipal();
    }

    @Override
    public String getPassword() {
        return this.credentials.getPassword();
    }
}
