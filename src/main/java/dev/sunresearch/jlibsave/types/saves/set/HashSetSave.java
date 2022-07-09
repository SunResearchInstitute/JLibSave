package dev.sunresearch.jlibsave.types.saves.set;

import com.google.gson.reflect.TypeToken;
import dev.sunresearch.jlibsave.SaveController;
import dev.sunresearch.jlibsave.types.saves.SaveFile;

import java.io.File;
import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.function.Supplier;

public class HashSetSave<T> extends SaveFile<HashSet<T>> {
    /**
     * @param path         Path of save. Can be found using {@link SaveController#getSavePath(String)}.
     * @param fallbackData If the class fails to read data from the JSON this will be used instead
     * @param typeToken    The type token including the Save. Best gotten using {@link TypeToken}.
     */
    public HashSetSave(File path, Supplier<HashSet<T>> fallbackData, Type typeToken) {
        super(path, fallbackData, typeToken);
    }
}
