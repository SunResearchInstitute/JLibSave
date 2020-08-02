package net.sunthecourier.jlibsave.types.map;

import net.sunthecourier.jlibsave.SaveFile;

import java.io.File;
import java.util.LinkedHashMap;

public class LinkedHashMapSaveFile<T, TK> extends SaveFile<LinkedHashMap<T, TK>> {
    public LinkedHashMapSaveFile(File path, LinkedHashMap<T, TK> defaultValue) {
        super(path, () -> {
            if (defaultValue != null) return defaultValue;
            else return new LinkedHashMap<>();
        });
    }
}
