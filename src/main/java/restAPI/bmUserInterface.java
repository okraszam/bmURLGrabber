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
import javax.ws.rs.PathParam;
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

        @GET
        @Path("/")
        @Produces(MediaType.TEXT_PLAIN)
        public Response bmGrabberGreeter() {

            return Response.ok("Welcome to RESTCalculator, v0.2 :"
                                       + "\n\nPlease chose the way you want to use application:"
                                       + "\n1. Check user guide: {your local URL}/RESTCalculatorApp/readguide"
                                       + "\n2. Use Calculator: {your local URL}/RESTCalculatorApp/useit/{specify operation query}").build();

        }

        @GET
        @Path("/guide")
        @Produces (MediaType.TEXT_PLAIN)
        public Response bmGrabberGuide() {

            return Response.ok("RESTCalculator v0.2, guide :"
                                       + "\nand <ENTER>."
                                       + "\nI recomend to use application like Postman, it has nice interface and options."
                                       + "\n\nWelcome screen -> {your local URL}/RESTCalculatorApp/").build();

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
                return Response.ok("URL Content was successfuly saved."
                                           + "\nContent-type / file size are: " + contentType + " / "
                                           + contentSize + " bytes.").build();
            } else {
                return Response.ok("File is too big, maximum content size is 16mb.").build();
            }

        }

}
