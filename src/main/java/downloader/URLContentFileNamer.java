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

        if (urlPartOfName.length() > 20) {
            return timePartOfName + "_" + urlPartOfName.substring(0, 20) + urlContentPartOfName;
        } else {
            return timePartOfName + "_" + urlPartOfName + urlContentPartOfName;
        }

    }

    static String createTimePartOfName () {

        LocalDateTime timeOfArchivisation = LocalDateTime.now();
        DateTimeFormatter archivisationTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss.SSS");
        return timeOfArchivisation.format(archivisationTimeFormatter);

    }

    static String createURLPrtOfName (String givenURL) {

        return givenURL.replaceAll("[\\/\\\\:*?<>|.]", "");

    }

    static String createURLContentPartOfName (String givenURL) {

        String[] urlContent = URLContentAnalyzer.getContentType(givenURL).replace(" ", "")
                                                .replaceAll("[\\/;]", " ").trim().split(" ");
        return "." + urlContent[1];

    }

}
