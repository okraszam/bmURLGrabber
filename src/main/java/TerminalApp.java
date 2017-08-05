import downloader.URLContentDownloader;

public class TerminalApp {

    public static void main(String[] args) {

        URLContentDownloader urlContentDownloader = new URLContentDownloader();
        urlContentDownloader.downloadURLContent("https://regex101.com/");

    }

}
