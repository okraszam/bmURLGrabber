package restAPI;

import downloader.URLContentAnalyzer;
import downloader.URLContentDownloader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.inject.Inject;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
public class bmUserInterface {

    private Logger LOG = LoggerFactory.getLogger(bmUserInterface.class);
    String bmURLGrabberPath = "http://localhost:8080/bmURLGrabberApp/";

    public bmUserInterface() {
    }

    @Inject
    URLContentDownloader urlContentDownloader;

    @Inject
    URLContentAnalyzer urlContentAnalyzer;

//    @Inject
//    DBLister dbLister;

    @GET
    @Path("/")
    @Produces(MediaType.TEXT_PLAIN)
    public Response bmGrabberGreeter() {

        return Response.ok("Welcome to bmURLGrabberApp, v0.1:"
                                   + "\n\nPlease chose one of two basic ways of using application (more info in guide):"
                                   + "\n1. Check user guide: {your local URL}/bmURLGrabberApp/readguide (GET)"
                                   + "\n2. Archive URL content to database: {your local URL}/bmURLGrabberApp/download (POST)"
                                   + "\nIn request body please use 'x-www-form-urlencoded' content type 'contentURL' KEY and target 'URLaddress' VALUE.").build();

    }

    @GET
    @Path("/readguide")
    @Produces (MediaType.TEXT_PLAIN)
    public Response bmGrabberGuide() {

        return Response.ok("bmURLGrabberApp, v0.1, guide:"
                                   + "\n\nIn current version application allows to save content of choosen URL to database."
                                   + "\nBest way to use app is to install tool like Postman, it has good features and lots of options."
                                   + "\nApplication is working as RESTful service, runs on Wildfly application server and using MySql database."
                                   + "\nBelow are listed every planned app features:"
                                   + "\n1. Guide <you are here>"
                                   + "\n2. URLContentArchiver: {your local URL}/bmURLGrabberApp/download (POST)"
                                   + "\nIn request body please use 'x-www-form-urlencoded' content type 'contentURL' KEY and target 'URLaddress' VALUE."
                                   + "\n\n3. Check current URL archivisation count: {your local URL}/bmURLGrabberApp/dbcount (GET) - comming soon"
                                   + "\n4. List database rows: {your local URL}/bmURLGrabberApp/dbcontent (GET) - comming soon"
                                   + "\n5. Download one of archived URL contents: {your local URL}/bmURLGrabberApp/dbretriever/{dateOfArchivisation} (GET) - comming soon"
                                   + "\n6. Delete one of archived URL contents: {your local URL}/bmURLGrabberApp/dbremoverow/{dateOfArchivisation} (GET) - comming soon"
                                   + "\n7. Welcome screen -> {your local URL}/bmURLGrabberApp/"
                                   + "\nYou can find description of development enviromnent in file 'bmULRGrabberDevEnv.pdf' attached to this .war.").build();

    }

    @POST
    @Path("/download")
    @Produces(MediaType.TEXT_PLAIN)
    public Response bmGrabberPersist(@FormParam("contentURL") String givenURL) {

        LOG.info("Verifying content file size and creating response.");
        int contentSize = urlContentAnalyzer.getContentSize(givenURL);
        String contentType = urlContentAnalyzer.getContentType(givenURL);

        if (urlContentAnalyzer.verifyMaxContentSize(contentSize)) {
            urlContentDownloader.downloadURLContent(givenURL);
            return Response.ok("bmURLGrabberApp, v0.1:"
                                       + "\n\nURL Content was successfuly saved."
                                       + "\nContent-type | file size: " + contentType + " | "
                                       + contentSize + " bytes.").build();
        } else {
            return Response.ok("bmURLGrabberApp, v0.1:"
                                       + "\n\nFile is too big, maximum content size is 16mb."
                                       + "\nPlease try with another URL.").build();
        }

    }

    @GET
    @Path("/download")
    @Produces(MediaType.TEXT_PLAIN)
    public Response bmGrabberDownloadInfo() {

        return Response.ok("bmURLGrabberApp, v0.1: "
                                   + "\n\nTo use archivisation feature please switch to POST method."
                                   + "\nMore information in application guide: {your local URL}/bmURLGrabberApp/readguide (GET)").build();

    }

//    @GET
//    @Path("/dbcount")
//    @Produces (MediaType.TEXT_PLAIN)
//    public Response bmDatabasePrint() {
//
//        return Response.ok(dbLister.checkCurrentDBRowCount()).build();
//
//    }

//    @GET
//    @Path("/dbcontent")
//    @Produces (MediaType.TEXT_PLAIN)
//    public Response bmDatabaseContent() {
//
//        return Response.ok(dbLister.printEntireBDTable()).build();
//
//    }

//    @GET
//    @Path("/dbretriever/{dateOfArchivisation}")
//    @Produces (MediaType.MULTIPART_FORM_DATA_TYPE)
//    public Response bmDatabaseContentRetriever(@PathParam("dateOfArchivisation") String dateOfArchivisation) {
//
//        return Response.ok(    ).build();
//
//    }

//    @POST
//    @Path("/dbrowremover")
//    @Produces (MediaType.TEXT_PLAIN)
//    public Response bmDatabaseRowRemover(@FormParam("dateOfArchivisation") String dateOfArchivisation) {
//
//        return Response.ok(    ).build();
//
//    }

}
