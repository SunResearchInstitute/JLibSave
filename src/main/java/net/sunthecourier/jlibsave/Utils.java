package net.sunthecourier.jlibsave;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;

public class Utils {
    public static final Gson PRETTY_GSON = new GsonBuilder()
            .setPrettyPrinting()
            .disableHtmlEscaping()
            .create();

    public static File getFolder(File parent, String folderName) {
        File dir = new File(parent, folderName);

        if (dir.exists() && !(dir.isDirectory())) {
            dir.delete();
        }
        if (!(dir.exists())) {
            dir.mkdir();
        }
        return dir;
    }

    public static File getFile(File folder, String file) {
        return new File(folder, file);
    }
}
