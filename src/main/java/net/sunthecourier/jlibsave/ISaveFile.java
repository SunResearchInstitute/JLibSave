package net.sunthecourier.jlibsave;

import lombok.Getter;

import java.io.File;

abstract class ISaveFile {
    @Getter
    protected final File saveInfo;

    public ISaveFile(File path) {
        saveInfo = path;
    }

    public abstract void write();
}
