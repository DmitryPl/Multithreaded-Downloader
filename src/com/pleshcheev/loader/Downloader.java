package com.pleshcheev.loader;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Stack;
import java.util.concurrent.ConcurrentHashMap;

import static com.pleshcheev.loader.DownloaderUtils.fileDownload;

public class Downloader implements Runnable {
    Thread thread;
    Stack<Data> dataStack;
    ConcurrentHashMap<String, Path> filesDownloaded;

    private Downloader() throws IllegalAccessException {
        throw new IllegalAccessException();
    }
    
    Downloader(String name, Stack<Data> dataStack, ConcurrentHashMap<String, Path> filesDownloaded) {
        thread = new Thread(this, name);
        this.dataStack = dataStack;
        this.filesDownloaded = filesDownloaded;
        System.out.printf("Started >>> %s\n", name);
        thread.start();
    }

    @Override
    public void run() {
        boolean flag = true;
        while (flag) {
            synchronized (dataStack) {
                if (dataStack.empty()) {
                    System.out.printf("End:%s\n", Thread.currentThread().getName());
                    return;
                }

                Data that = dataStack.pop();
                try {
                    Path elemPath;
                    if (null == (elemPath = filesDownloaded.get(that.getUri()))) {
                        elemPath = fileDownload(that.getPath(), that.getUri());
                        filesDownloaded.put(that.getUri(), elemPath);
                    }

                    if (elemPath != null) {
                        that.elem.attr(that.attr, elemPath.toString());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
