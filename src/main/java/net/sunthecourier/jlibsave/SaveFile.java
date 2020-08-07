package net.sunthecourier.jlibsave;

import com.google.gson.stream.JsonReader;
import lombok.Getter;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.OpenOption;

import static java.nio.file.StandardOpenOption.*;
import static net.sunthecourier.jlibsave.Utils.PRETTY_GSON;

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
    public void writeAsync() {
        new Thread(this::write).start();
    }

    @Override
    public void write() {
        OpenOption[] options = new OpenOption[]{WRITE, CREATE, TRUNCATE_EXISTING};
        try {
            Files.write(this.saveInfo.toPath(), PRETTY_GSON.toJson(data, type).getBytes(), options);
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
                result = PRETTY_GSON.fromJson(reader, type);
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
