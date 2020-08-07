package net.sunthecourier.jlibsave;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import lombok.Getter;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.OpenOption;

import static java.nio.file.StandardOpenOption.*;

public abstract class SaveFile<T> extends ISaveFile {
    @Getter
    protected T data;

    /**
     * @param fallbackData If the class fails to read data from the JSON this will be used instead
     */
    public SaveFile(File path, T fallbackData, Type typeToken) {
        super(path, typeToken);
        data = loadData(fallbackData);
        write();
    }

    @Override
    public void write() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();
        OpenOption[] options = new OpenOption[]{WRITE, CREATE, TRUNCATE_EXISTING};
        try {
            Files.write(this.saveInfo.toPath(), gson.toJson(data, type).getBytes(), options);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void reload() {
        data = loadData(null);
    }

    public void reload(T fallbackData) {
        data = loadData(fallbackData);
    }

    private T loadData(T defaultData) {
        T result;
        try {
            if (getSaveInfo().exists()) {
                JsonReader reader = new JsonReader(new FileReader(this.getSaveInfo()));
                Gson gson = new Gson();
                result = gson.fromJson(reader, type);
                if (result != null) {
                    return result;
                }
            }
            result = defaultData;
        } catch (Exception e) {
            result = defaultData;
        }
        return result;
    }
}
