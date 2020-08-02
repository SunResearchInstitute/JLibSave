package net.sunthecourier.jlibsave.types;

import net.sunthecourier.jlibsave.SaveFile;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class MapSaveFile<T, TK> extends SaveFile<Map<T, TK>> {
    public MapSaveFile(File path, Map<T, TK> defaultValue) {
        super(path, () -> {
            if (defaultValue != null) return defaultValue;
            else return new HashMap<>();
        });
    }
}
