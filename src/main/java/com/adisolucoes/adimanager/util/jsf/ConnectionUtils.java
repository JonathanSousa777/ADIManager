package com.adisolucoes.adimanager.util.jsf;

/**
 *
 * @author ADI Soluções
 */
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

public class ConnectionUtils {

    public int timeout;
    public static final String USER_AGENT = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/48.0.2564.97 Safari/537.36";
    private static final Logger LOG = Logger.getLogger(ConnectionUtils.class.getName());

    public ConnectionUtils() {
        this.timeout = 30000;
    }

    public Connection getConnection(String url, String refer, Map<String, String> cookies) throws IOException {
        if (url == null || "".equals(url)) {
            throw new IOException("A URL é inválida ou nula.");
        }
        String domain = getDomain(url);
        Connection connection = Jsoup.connect(url);
        if (domain != null) {
            connection.header("Host", domain);
        }
        connection.userAgent(USER_AGENT);
        connection.timeout(timeout);
        if (cookies != null) {
            connection.cookies(cookies);
        }
        if (refer != null) {
            connection.referrer(refer);
        }
        connection.validateTLSCertificates(false);
        return connection;
    }

    private String getDomain(String url) {
        try {
            Pattern p = Pattern.compile(".*?([^.]+\\.[^.]+)");
            URI uri = new URI(url);
            String domain = uri.getHost();
            return domain.startsWith("www.") ? domain.substring(4) : domain;
        } catch (URISyntaxException ex) {
            LOG.log(Level.SEVERE, null, ex);
            return null;
        }
    }

}
