package downloader;

import dao.DBArchiver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.ejb.Stateless;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URL;
import java.nio.channels.Channel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.concurrent.TimeUnit;

@Stateless
public class URLContentDownloader {

    private static Logger LOG = LoggerFactory.getLogger(URLContentDownloader.class);
//    private static final String downloadDirectory = "src/main/resources";
    private static final String downloadDirectory = System.getProperty("jboss.server.temp.dir");

    public void downloadURLContent (String givenURL){

        URL contentURL = ContentURLCreator.createContentURL(givenURL);

        String timePartOfName = URLContentFileNamer.createTimePartOfName();
        String urlPartOfName = URLContentFileNamer.createURLPrtOfName(givenURL);
        String urlContentPartOfName = URLContentFileNamer.createURLContentPartOfName(givenURL);
        String urlContentPackageName = timePartOfName + "_" + urlPartOfName + urlContentPartOfName;

        Path contentDownloadPath = new File(downloadDirectory
                                                    + File.separator
                                                    + urlContentPackageName).toPath();

        try {
            Files.copy(contentURL.openStream(), contentDownloadPath, StandardCopyOption.REPLACE_EXISTING);
            LOG.info("Downloaded package: " + urlContentPackageName);
        } catch (IOException e) {
            e.printStackTrace();
        }

        DBArchiver.addURLDescriptionToDB(timePartOfName, urlContentPackageName, givenURL);
        LOG.info("Archived URLDescription");

        File urlContent = new File(downloadDirectory
                                           + File.separator
                                           + urlContentPackageName);

        DBArchiver.addURLContentToDB(timePartOfName, urlContentPackageName, urlContent);
        LOG.info("Archived URLContent: " + urlContentPackageName);

//        if (URLContentDownloader.isURLContentClosed(urlContent)) {
//
//          URLContentDownloader.deleteURLContentFromTemp(contentDownloadPath);
//
//        }

    }

    private static void deleteURLContentFromTemp (Path contentDownloadPath) {

        try {
            Files.delete(contentDownloadPath);
            LOG.info("URL content deleted from TEMP");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static boolean isURLContentClosed (File urlContent) {

        boolean isFileClosed;

        Channel channel = null;
        try {
            channel = new RandomAccessFile(urlContent, "rw").getChannel();
            isFileClosed = true;
        } catch(Exception e) {
            isFileClosed = false;
        } finally {
            if(channel!=null) {
                try {
                    channel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        if (isFileClosed == false) {

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return URLContentDownloader.isURLContentClosed(urlContent);
        } else {
            return isFileClosed;
        }

    }

}
