package net.sunthecourier.jlibsave;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import lombok.Getter;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.OpenOption;

import static java.nio.file.StandardOpenOption.*;

public abstract class SaveFile<T> extends ISaveFile {
    @Getter
    protected T data;

    /**
     * @param defaultData If the class fails to read data from the JSON this will be used instead
     */
    public SaveFile(File path, T defaultData) {
        super(path);
        try {
            if (path.exists()) {
                JsonReader reader = new JsonReader(new FileReader(this.getSaveInfo()));
                Gson gson = new Gson();
                data = gson.fromJson(reader, defaultData.getClass());
                if (data != null) {
                    return;
                }
            }
            data = defaultData;
        } catch (Exception e) {
            data = defaultData;
        }
    }

    @Override
    public void write() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();
        OpenOption[] options = new OpenOption[]{WRITE, CREATE, TRUNCATE_EXISTING};
        try {
            Files.write(this.saveInfo.toPath(), gson.toJson(data).getBytes(), options);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
