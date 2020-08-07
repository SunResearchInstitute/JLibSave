package net.sunthecourier.jlibsave;

import lombok.Getter;

import java.io.File;
import java.util.HashMap;

public class SaveController {
    @Getter
    private final File saveDirectory;
    @Getter
    private final HashMap<String, ISaveFile> saveFiles;

    public SaveController(String directory) {
        saveDirectory = new File(directory);
        if (!saveDirectory.exists())
            saveDirectory.mkdirs();
        saveFiles = new HashMap<>();
    }


    public ISaveFile getSave(String saveName) {
        if (saveFiles.containsKey(saveName)) {
            return saveFiles.get(saveName);
        }
        return null;
    }

    public void registerSave(ISaveFile saveFile, String saveName) {
        saveFiles.put(saveName, saveFile);
    }

    public ISaveFile removeSave(String saveName) {
        return saveFiles.remove(saveName);
    }

    public void saveAll() {
        saveAll(true);
    }

    public void saveAll(boolean clean) {
        saveFiles.forEach((s, saveFile) -> saveFile.write());
        if (clean)
            clearSaveFiles();
    }

    public void clearSaveFiles() {
        saveFiles.clear();
    }

    public File getFilePath(String saveName) {
        return Utils.getFile(saveDirectory, saveName);
    }
}
