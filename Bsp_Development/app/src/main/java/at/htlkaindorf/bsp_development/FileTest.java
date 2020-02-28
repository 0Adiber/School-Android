package at.htlkaindorf.bsp_development;

import java.io.File;
import java.nio.file.Paths;

public class FileTest {

    public static void main(String[] args) {
        String path = System.getProperty("user.dir");
        File file = Paths.get(path, "app", "main", "src", "datei.txt").toFile();
    }
}
