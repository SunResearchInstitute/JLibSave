package net.sunthecourier.jlibsave.types;

import net.sunthecourier.jlibsave.SaveFile;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

public class SetSaveFile<T> extends SaveFile<Set<T>> {
    public SetSaveFile(File path, Set<T> defaultValues) {
        super(path, () -> {
            if (defaultValues != null) return defaultValues;
            else return new HashSet<>();
        });
    }
}
