import downloader.URLContentDownloader;

public class TerminalApp {

    public static void main(String[] args) {

        URLContentDownloader urlContentDownloader = new URLContentDownloader();
        urlContentDownloader.downloadURLContent("https://dietety.pl/wp-content/uploads/2016/09/MTAyNHg3Njg13510781_13510767.jpg");

    }

}
