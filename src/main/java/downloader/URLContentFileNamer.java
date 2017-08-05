package downloader;

import javax.ejb.Stateless;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Stateless
public class URLContentFileNamer {

    static String createFileName (String givenURL) {

        String timePartOfName = URLContentFileNamer.createTimePartOfName();
        String urlPartOfName = URLContentFileNamer.createURLPrtOfName(givenURL);
        String urlContentPartOfName = URLContentFileNamer.createURLContentPartOfName(givenURL);

        return timePartOfName + "_" + urlPartOfName + urlContentPartOfName;

    }

    static String createTimePartOfName () {

        LocalDateTime timeOfArchivisation = LocalDateTime.now();
        DateTimeFormatter archivisationTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss.SSS");
        return timeOfArchivisation.format(archivisationTimeFormatter);

    }

    static String createURLPrtOfName (String givenURL) {

        String urlPartOfName = givenURL.replaceAll("[\\/\\\\:*?<>|.]", "");

        if (urlPartOfName.length() > 20) {
            return urlPartOfName.substring(0, 20);
        } else {
            return urlPartOfName;
        }

    }

    static String createURLContentPartOfName (String givenURL) {

        String[] urlContent = URLContentAnalyzer.getContentType(givenURL).replace(" ", "")
                                                .replaceAll("[\\/;]", " ").trim().split(" ");
        return "." + urlContent[1];

    }

}
