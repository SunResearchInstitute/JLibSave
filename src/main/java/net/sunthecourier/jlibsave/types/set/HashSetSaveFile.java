package net.sunthecourier.jlibsave.types.set;

import net.sunthecourier.jlibsave.SaveFile;

import java.io.File;
import java.util.HashSet;

public class HashSetSaveFile<T> extends SaveFile<HashSet<T>> {
    public HashSetSaveFile(File path, HashSet<T> defaultValues) {
        super(path, defaultValues != null ? defaultValues : new HashSet<>());
    }
}
