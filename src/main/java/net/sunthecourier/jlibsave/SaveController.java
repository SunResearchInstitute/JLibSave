package net.sunthecourier.jlibsave;

import lombok.Getter;

import java.io.File;
import java.util.HashMap;
import java.util.concurrent.CompletableFuture;

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


    public <T extends ISaveFile> T getSave(String saveName) {
        if (saveFiles.containsKey(saveName)) {
            return (T) saveFiles.get(saveName);
        }
        return null;
    }

    public void registerSave(ISaveFile saveFile, String saveName) {
        saveFiles.put(saveName, saveFile);
    }

    public <T extends ISaveFile> T removeSave(String saveName) {
        return (T) saveFiles.remove(saveName);
    }

    public void saveAll() {
        saveAll(false);
    }

    public void saveAll(boolean clean) {
        saveFiles.forEach((s, saveFile) -> saveFile.write());
        if (clean)
            clearSaveFiles();
    }

    public CompletableFuture<Void> saveAllAsync() {
        return saveAllAsync(false);
    }

    public CompletableFuture<Void> saveAllAsync(boolean clean) {
        return CompletableFuture.runAsync(() -> saveAll(clean));
    }

    public void clearSaveFiles() {
        saveFiles.clear();
    }

    public File getFilePath(String saveName) {
        return Utils.getFile(saveDirectory, saveName);
    }
}
