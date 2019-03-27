package org.maximkir.shcf4j.api.client;

import org.maximkir.shcf4j.api.auth.AuthScope;
import org.maximkir.shcf4j.api.auth.Credentials;
import lombok.Builder;
import lombok.Singular;
import lombok.Value;

import java.util.Map;

/**
 * <b>CredentialsProvider</b>
 * <p>
 * Basic credentials provider that maintains a collection of user
 * credentials.
 * </p>
 *
 * @author maxim.kirilov
 */
@Builder
@Value
public class CredentialsProvider {

    @Singular
    private final Map<AuthScope, Credentials> credentials;


}
