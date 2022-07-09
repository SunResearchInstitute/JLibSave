package dev.sunresearch.jlibsave;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.UtilityClass;

import java.io.File;

@UtilityClass
public class Utils {
    /**
     * GSON instance used to create Save files.
     */
    @Getter
    private static final Gson prettyGson = new GsonBuilder()
            .setPrettyPrinting()
            .disableHtmlEscaping()
            .create();

    @NonNull
    public static File getFile(File folder, String file) {
        return new File(folder, file);
    }
}
