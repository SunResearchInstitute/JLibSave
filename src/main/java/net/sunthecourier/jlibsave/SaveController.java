package net.sunthecourier.jlibsave;

import lombok.Getter;
import net.sunthecourier.jlibsave.types.MapSaveFile;
import net.sunthecourier.jlibsave.types.SetSaveFile;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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

    public <T, TK> MapSaveFile<T, TK> getMapSave(String saveName) {
        return getMapSave(saveName, Utils.getFile(saveDirectory, saveName), null);
    }

    public <T, TK> MapSaveFile<T, TK> getMapSave(String saveName, Map<T, TK> defaultValues) {
        return getMapSave(saveName, Utils.getFile(saveDirectory, saveName), defaultValues);
    }

    public <T, TK> MapSaveFile<T, TK> getMapSave(String saveName, File saveFile, Map<T, TK> defaultValues) {
        MapSaveFile<T, TK> map;
        if (!saveFiles.containsKey(saveName)) {
            map = new MapSaveFile<>(saveFile, defaultValues);
            saveFiles.put(saveName, map);
        } else {
            map = (MapSaveFile<T, TK>) saveFiles.get(saveName);
        }
        return map;
    }

    public <T> SetSaveFile<T> getSetSave(String saveName) {
        return getSetSave(saveName, Utils.getFile(saveDirectory, saveName), null);
    }

    public <T> SetSaveFile<T> getSetSave(String saveName, Set<T> defaultValues) {
        return getSetSave(saveName, Utils.getFile(saveDirectory, saveName), defaultValues);
    }

    public <T> SetSaveFile<T> getSetSave(String saveName, File saveFile, Set<T> defaultValues) {
        SetSaveFile<T> set;
        if (!saveFiles.containsKey(saveName)) {
            set = new SetSaveFile<>(saveFile, defaultValues);
            saveFiles.put(saveName, set);
        } else {

            set = (SetSaveFile<T>) saveFiles.get(saveName);
        }
        return set;
    }

    public <T extends SaveFile<T>> T getCustomSave(String saveName) {
        if (saveFiles.containsKey(saveName)) {
            return (T) saveFiles.get(saveName);
        }
        return null;
    }

    public void registerCustomSave(ISaveFile saveFile, String saveName) {
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
            saveFiles.clear();
    }
}
