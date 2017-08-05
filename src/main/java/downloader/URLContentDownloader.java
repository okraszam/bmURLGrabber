package downloader;

import dao.DBArchiver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.ejb.Stateless;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@Stateless
public class URLContentDownloader {

    private static Logger LOG = LoggerFactory.getLogger(URLContentDownloader.class);
    private static final String downloadDirectory = "src/main/resources";

    public void downloadURLContent (String givenURL){

        URL contentURL = ContentURLCreator.createContentURL(givenURL);

        String timePartOfName = URLContentFileNamer.createTimePartOfName();
        String urlPartOfName = URLContentFileNamer.createURLPrtOfName(givenURL);
        String urlContentPartOfName = URLContentFileNamer.createURLContentPartOfName(givenURL);
        String urlContentPackageName = timePartOfName + "_" + urlPartOfName + "_" + urlContentPartOfName;

        Path contentDownloadPath = new File(downloadDirectory
                                                    + File.separator
                                                    + urlContentPackageName).toPath();

        try {
            Files.copy(contentURL.openStream(), contentDownloadPath, StandardCopyOption.REPLACE_EXISTING);
            LOG.info("Downloaded package: " + urlContentPackageName);
        } catch (IOException e) {
            e.printStackTrace();
        }

//        DBArchiver.addURLDescriptionToDB(timePartOfName, urlPartOfName, givenURL);
        LOG.info("Archived URLDescription");

        File urlContent = new File(downloadDirectory
                                           + File.separator
                                           + urlContentPackageName);
        DBArchiver.addURLContentToDB(timePartOfName, urlContent);
        LOG.info("Archived URLContent: " + urlContentPackageName);

//        URLContentDownloader.deleteURLContentFromTemp(contentDownloadPath);

    }

    private static void deleteURLContentFromTemp (Path contentDownloadPath) {

        try {
            Files.delete(contentDownloadPath);
            LOG.info("Deleting URLContent from TEMP");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
