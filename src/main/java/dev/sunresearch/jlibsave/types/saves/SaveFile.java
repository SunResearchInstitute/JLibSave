package dev.sunresearch.jlibsave.types.saves;

import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import dev.sunresearch.jlibsave.SaveController;
import dev.sunresearch.jlibsave.Utils;
import lombok.Getter;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

import static java.nio.file.StandardOpenOption.*;

public abstract class SaveFile<T> extends ISaveFile {
    @Getter
    protected T data;

    /**
     * @param path         Path of save. Can be found using {@link SaveController#getSavePath(String)}.
     * @param fallbackData If the class fails to read data from the JSON this will be used instead
     * @param typeToken    The type token including the Save. Best gotten using {@link TypeToken}.
     */
    public SaveFile(File path, Supplier<T> fallbackData, Type typeToken) {
        this(path, fallbackData, typeToken, false);
    }

    public SaveFile(File path, Supplier<T> fallbackData, Type typeToken, boolean isConfig) {
        super(path, typeToken, false);
        loadData(fallbackData);
        write();
    }

    @Override
    public void write() {
        OpenOption[] options = new OpenOption[]{WRITE, CREATE, TRUNCATE_EXISTING};
        try {
            Files.write(this.saveInfo.toPath(), Utils.getPrettyGson().toJson(data, type).getBytes(), options);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public CompletableFuture<Void> writeAsync() {
        return CompletableFuture.runAsync(this::write);
    }

    @Override
    public void reload() {
        reload(() -> null);
    }

    public void reload(Supplier<T> fallbackData) {
        loadData(fallbackData);
        write();
    }

    private void loadData(Supplier<T> defaultData) {
        T res;
        try {
            if (getSaveInfo().exists()) {
                JsonReader reader = new JsonReader(new FileReader(this.getSaveInfo()));
                res = Utils.getPrettyGson().fromJson(reader, type);
                if (res != null) {
                    data = res;
                }
            }
            data = defaultData.get();
        } catch (Exception e) {
            data = defaultData.get();
        }
    }
}
