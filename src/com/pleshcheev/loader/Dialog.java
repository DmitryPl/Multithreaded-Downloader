package com.pleshcheev.loader;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Dialog {
    public static String checkDir(String dir, String uri) throws IOException {
        if (dir == null) {
            return null;
        }
        String name = DownloaderUtils.extractName(URI.create(uri).toURL());
        if (Files.exists(Paths.get(dir, name).getParent())){
            String yes = "y";
            String no  = "n";
            System.out.printf("The folder exists.\n");
            System.out.printf("Do you want to use a unique name or want to overwrite a file?\n");
            int flag_of_work = 100;
            while (flag_of_work-- > 0) {
                System.out.printf("Press \'%s\' to use unique name or press \'%s\'\n", yes, no);
                //Scanner in = new Scanner(System.in);
                //String input = in.nextLine();
                String input = "n";
                if (input.equals(yes)) {
                    int i = 1;
                    while (Files.exists(Paths.get(dir + "-" + Integer.toString(i), name).getParent())) {
                        i++;
                    }
                    dir = dir + "-" + Integer.toString(i);
                    return dir;
                }

                if (input.equals(no)) {
                    return dir;
                }
            }
        }
        return dir;
    }

    public static String getUri() {
        String uri = "https://en.wikipedia.org/wiki/Main_Page";
        String yes = "y";
        String no  = "n";
        System.out.printf("Do you want to enter url? Otherwise the test uri will be used\n");
        System.out.printf("Press \'%s\' to use test uri or press \'%s\'\n", no, yes);

        //Scanner in = new Scanner(System.in);
        //String input = in.nextLine();
        String input = "n";
        if (input.equals(yes)) {
            System.out.printf("Enter address\n");
            //uri = in.nextLine();
        }

        return uri;
    }

    public static boolean getOpenState() {
        String yes = "y";
        String no  = "n";
        System.out.printf("Do you want to open after download?\n");
        System.out.printf("Press \'%s\' or \'%s\'\n", yes, no);

        //Scanner in = new Scanner(System.in);
        //String input = in.nextLine();
        String input = "n";
        if (input.equals(yes)) {
            return true;
        }

        return false;
    }
}
