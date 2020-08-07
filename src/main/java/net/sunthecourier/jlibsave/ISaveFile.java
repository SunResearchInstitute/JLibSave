package net.sunthecourier.jlibsave;

import lombok.Getter;

import java.io.File;
import java.lang.reflect.Type;

public abstract class ISaveFile {
    @Getter
    protected final File saveInfo;

    @Getter
    protected Type type;

    public ISaveFile(File path, Type typeToken) {
        saveInfo = path;
        type = typeToken;
    }
    public abstract void writeAsync();
    public abstract void write();
    public abstract void reload();
}
