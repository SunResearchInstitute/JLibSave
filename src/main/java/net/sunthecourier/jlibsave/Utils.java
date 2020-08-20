package net.sunthecourier.jlibsave;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;

public class Utils {
    public static final Gson PRETTY_GSON = new GsonBuilder()
            .setPrettyPrinting()
            .disableHtmlEscaping()
            .create();

    public static File getFile(File folder, String file) {
        return new File(folder, file);
    }
}
