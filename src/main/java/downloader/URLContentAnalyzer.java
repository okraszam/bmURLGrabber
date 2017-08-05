package downloader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.ejb.Stateless;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

@Stateless
public class URLContentAnalyzer {

    private static Logger LOG = LoggerFactory.getLogger(URLContentAnalyzer.class);

    public static String getContentType(String givenURL) {

        HttpURLConnection httpURLConnection = URLContentAnalyzer.openHttpConnection(givenURL);
        String contentType = httpURLConnection.getContentType();

        LOG.info("File content-type is: " + contentType);

        httpURLConnection.disconnect();

        return contentType;

    }

    public int getContentSize (String givenURL) {

        HttpURLConnection httpURLConnection = URLContentAnalyzer.openHttpConnection(givenURL);
        int contentSize = httpURLConnection.getContentLength();

        LOG.info("Size of content file is: " + contentSize + " bytes.");

        httpURLConnection.disconnect();

        return contentSize;

    }

    public Boolean verifyMaxContentSize (int contentSize) {

        int maximumContentSize = 16777216;

        if (contentSize < maximumContentSize) {
            return true;
        } else {
            return false;
        }

    }

    private static HttpURLConnection openHttpConnection (String givenURL) {

        URL contentURL = ContentURLCreator.createContentURL(givenURL);

        HttpURLConnection httpURLConnection = null;
        try {
            httpURLConnection = (HttpURLConnection) contentURL.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return httpURLConnection;

    }

}
