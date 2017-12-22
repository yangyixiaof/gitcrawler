package cn.yyx.research.gitcrawler.gitcrawler;

import java.io.*;

public class FileUtility {

    public static final String starFilePath = "gitcrawler/resources/star.txt";
    public static final String pageFilePath = "gitcrawler/resources/page.txt";

    public static int getNumber(String filePath) {
        int result = -1;
        File file = new File(filePath);
        FileReader frReader = null;
        BufferedReader reader = null;

        try {
            frReader = new FileReader(file);
            reader = new BufferedReader(frReader);
            String text = reader.readLine();
            result = Integer.parseInt(text);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
                if (frReader != null) {
                    frReader.close();
                }
            } catch (IOException e) {
            }
        }

        return result;
    }

    public static void setNumber(String filePath, int number) {
        try {
            PrintWriter writer = new PrintWriter(filePath, "UTF-8");
            writer.println(number);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int getProperRange(int x) {
        if (10 <= x && x < 100) return 1;
        if (100 <= x && x < 1000) return 10;
        if (1000 <= x && x < 10000) return 100;
        if (10000 <= x && x < 100000) return 1000;
        if (100000 <= x && x < 1000000) return 10000;
        if (1000000 <= x && x < 10000000) return 100000;
        return 1;
    }
}
