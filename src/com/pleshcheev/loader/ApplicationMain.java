package com.pleshcheev.loader;

import java.io.IOException;

public class ApplicationMain {

    public static void main(String... args) throws IOException  {
        String dir = "test-downloader";
        String uri;
        boolean open;

        uri = Dialog.getUri();
        open = Dialog.getOpenState();
        dir = Dialog.checkDir(dir, uri);

        DownloaderUtils utils = new DownloaderUtils();
        utils.DownloadUtil(uri, dir, open);
    }
}
