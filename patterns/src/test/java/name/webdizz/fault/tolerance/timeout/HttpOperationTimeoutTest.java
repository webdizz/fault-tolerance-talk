package name.webdizz.fault.tolerance.timeout;

import java.net.SocketTimeoutException;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Test;

public class HttpOperationTimeoutTest {

    public static final int MS = 50;

    @Test(expected = SocketTimeoutException.class)
    public void shouldMakeSureTimeOutCaughtUsingApacheHttpClient() throws Exception {
        int timeout = 1 * MS;
        RequestConfig config = RequestConfig.custom()
                .setSocketTimeout(timeout)
                .build();
        CloseableHttpClient client =
                HttpClientBuilder.create().setDefaultRequestConfig(config).build();
        client.execute(new HttpGet("http://yahoo.com"));
    }
}
