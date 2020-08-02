package net.sunthecourier.jlibsave.types.map;

import net.sunthecourier.jlibsave.SaveFile;

import java.io.File;
import java.util.HashMap;

public class HashMapSaveFile<T, TK> extends SaveFile<HashMap<T, TK>> {
    public HashMapSaveFile(File path, HashMap<T, TK> defaultValue) {
        super(path, () -> {
            if (defaultValue != null) return defaultValue;
            else return new HashMap<>();
        });
    }
}
