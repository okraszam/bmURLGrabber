package downloader;

import java.net.MalformedURLException;
import java.net.URL;

public class ContentURLCreator {

    static URL createContentURL (String givenURL) {

        URL contentURL = null;
        try {
            contentURL = new URL(givenURL);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return contentURL;

    }

}