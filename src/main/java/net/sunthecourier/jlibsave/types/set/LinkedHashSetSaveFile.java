package net.sunthecourier.jlibsave.types.set;

import net.sunthecourier.jlibsave.SaveFile;

import java.io.File;
import java.util.LinkedHashSet;

public class LinkedHashSetSaveFile<T> extends SaveFile<LinkedHashSet<T>> {
    public LinkedHashSetSaveFile(File path, LinkedHashSet<T> defaultValues) {
        super(path, defaultValues != null ? defaultValues : new LinkedHashSet<>());
    }
}
