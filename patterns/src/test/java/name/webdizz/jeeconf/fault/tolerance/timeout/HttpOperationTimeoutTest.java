package name.webdizz.jeeconf.fault.tolerance.timeout;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Test;

public class HttpOperationTimeoutTest {

    public static final int MS = 100;

    @Test(expected = ConnectTimeoutException.class)
    public void shouldMakeSureTimeOutCaughtUsingApacheHttpClient() throws Exception {
        int timeout = 1;
        RequestConfig config = RequestConfig.custom()
                .setConnectTimeout(timeout * MS)
                .setConnectionRequestTimeout(timeout * MS)
                .setSocketTimeout(timeout * MS).build();
        CloseableHttpClient client =
                HttpClientBuilder.create().setDefaultRequestConfig(config).build();
        client.execute(new HttpGet("http://yahoo.com"));
    }
}
