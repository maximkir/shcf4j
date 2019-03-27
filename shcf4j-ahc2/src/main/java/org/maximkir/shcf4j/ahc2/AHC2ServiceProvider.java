package org.maximkir.shcf4j.ahc2;

import org.maximkir.shcf4j.api.AsyncHttpClientBuilder;
import org.maximkir.shcf4j.api.NotSupportedException;
import org.maximkir.shcf4j.api.SyncHttpClientBuilder;
import org.maximkir.shcf4j.ahc2.client.async.AsyncAhcClientBuilder;
import org.maximkir.shcf4j.api.helpers.Util;
import org.maximkir.shcf4j.api.spi.SHC4JServiceProvider;
import org.asynchttpclient.config.AsyncHttpClientConfigDefaults;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * <b>AHC2ServiceProvider</b>
 *
 * @author maxim.kirilov
 */
public class AHC2ServiceProvider implements SHC4JServiceProvider {

    private static final NotSupportedException NOT_SUPPORTED_EXCEPTION = new NotSupportedException();
    private static final Pattern AHC2_VERSION_PATTERN = Pattern.compile("(\\d+)\\.(\\d+)\\.(\\d+)");


    private static final int AHC2_SUPPORTED_VERSION_MAJOR = 2;
    private static final int AHC2_SUPPORTED_VERSION_MINOR = 6;


    public AHC2ServiceProvider() {

        Matcher m = AHC2_VERSION_PATTERN.matcher(AsyncHttpClientConfigDefaults.AHC_VERSION);

        if (m.matches()) {
            int major = Integer.parseInt(m.group(1));
            int minor = Integer.parseInt(m.group(2));
            if (AHC2_SUPPORTED_VERSION_MAJOR != major || minor < AHC2_SUPPORTED_VERSION_MINOR) {
                Util.report("The runtime version of AHC2: "
                        + AsyncHttpClientConfigDefaults.AHC_VERSION + " not supported, " +
                        "please use version higher than: " + AHC2_SUPPORTED_VERSION_MAJOR + "." + AHC2_SUPPORTED_VERSION_MINOR + ".x");
            }
        }
    }

    @Override
    public SyncHttpClientBuilder getHttpClientBuilder() {
        throw NOT_SUPPORTED_EXCEPTION;
    }

    @Override
    public AsyncHttpClientBuilder getHttpAsyncClientBuilder() {
        return new AsyncAhcClientBuilder();
    }
}
