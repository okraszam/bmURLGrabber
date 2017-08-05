import downloader.URLContentDownloader;

public class TerminalApp {

    public static void main(String[] args) {

        URLContentDownloader urlContentDownloader = new URLContentDownloader();

        urlContentDownloader.downloadURLContent("https://regex101.com/");
        urlContentDownloader.downloadURLContent("https://t3.ftcdn.net/jpg/01/08/02/28/240_F_108022820_QUowbWsLW7nD2CfgFxapM4QFvCkGWtWn.jpg");
        urlContentDownloader.downloadURLContent("https://www.youtube.com/watch?v=iloHKUT_FtU");
        urlContentDownloader.downloadURLContent("https://upload.wikimedia.org/wikipedia/commons/3/3d/LARGE_elevation.jpg");

    }

}
