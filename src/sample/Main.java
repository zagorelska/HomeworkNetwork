package sample;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//      check the availability of sites from a file
        File file = new File("Sites.txt");

        String site = "";
        try (Scanner sc = new Scanner(file)) {
            for (; sc.hasNextLine(); ) {
                site = sc.nextLine() + System.lineSeparator();
                try {
                    NetworkService.checkAvailabilitySite(site);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

//      save links to a file from the html document of the site
        String spec = "https://www.apple.com/";
        File fileLinks = new File("Links.txt");
        try {
            String htmlText = NetworkService.getFromURL(spec, "UTF-8");
            String linksText = NetworkService.getLinksListFromText(htmlText);
            NetworkService.writeTextInFile(linksText, fileLinks);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
