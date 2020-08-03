package net.sunthecourier.jlibsave;

import lombok.Getter;
import net.sunthecourier.jlibsave.types.map.HashMapSaveFile;
import net.sunthecourier.jlibsave.types.map.LinkedHashMapSaveFile;
import net.sunthecourier.jlibsave.types.set.HashSetSaveFile;
import net.sunthecourier.jlibsave.types.set.LinkedHashSetSaveFile;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;

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


    public <T extends SaveFile<V>, V> T getCustomSave(String saveName) {
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

    public <T> HashSetSaveFile<T> getHashSetSave(String saveName) {
        return getHashSetSave(saveName, Utils.getFile(saveDirectory, saveName), null);
    }

    public <T> HashSetSaveFile<T> getHashSetSave(String saveName, HashSet<T> defaultValues) {
        return getHashSetSave(saveName, Utils.getFile(saveDirectory, saveName), defaultValues);
    }

    public <T> HashSetSaveFile<T> getHashSetSave(String saveName, File saveFile, HashSet<T> defaultValues) {
        HashSetSaveFile<T> set;
        if (!saveFiles.containsKey(saveName)) {
            set = new HashSetSaveFile<>(saveFile, defaultValues);
            saveFiles.put(saveName, set);
        } else {

            set = (HashSetSaveFile<T>) saveFiles.get(saveName);
        }
        return set;
    }

    public <T> LinkedHashSetSaveFile<T> getLinkedHashSetSave(String saveName) {
        return getLinkedHashSetSave(saveName, Utils.getFile(saveDirectory, saveName), null);
    }

    public <T> LinkedHashSetSaveFile<T> getLinkedHashSetSave(String saveName, LinkedHashSet<T> defaultValues) {
        return getLinkedHashSetSave(saveName, Utils.getFile(saveDirectory, saveName), defaultValues);
    }

    public <T> LinkedHashSetSaveFile<T> getLinkedHashSetSave(String saveName, File saveFile, LinkedHashSet<T> defaultValues) {
        LinkedHashSetSaveFile<T> set;
        if (!saveFiles.containsKey(saveName)) {
            set = new LinkedHashSetSaveFile<>(saveFile, defaultValues);
            saveFiles.put(saveName, set);
        } else {

            set = (LinkedHashSetSaveFile<T>) saveFiles.get(saveName);
        }
        return set;
    }


    public <T, TK> LinkedHashMapSaveFile<T, TK> getLinkedHashMapSave(String saveName) {
        return getLinkedHashMapSave(saveName, Utils.getFile(saveDirectory, saveName), null);
    }

    public <T, TK> LinkedHashMapSaveFile<T, TK> getLinkedHashMapSave(String saveName, LinkedHashMap<T, TK> defaultValues) {
        return getLinkedHashMapSave(saveName, Utils.getFile(saveDirectory, saveName), defaultValues);
    }

    public <T, TK> LinkedHashMapSaveFile<T, TK> getLinkedHashMapSave(String saveName, File saveFile, LinkedHashMap<T, TK> defaultValues) {
        LinkedHashMapSaveFile<T, TK> map;
        if (!saveFiles.containsKey(saveName)) {
            map = new LinkedHashMapSaveFile<>(saveFile, defaultValues);
            saveFiles.put(saveName, map);
        } else {
            map = (LinkedHashMapSaveFile<T, TK>) saveFiles.get(saveName);
        }
        return map;
    }

    public <T, TK> HashMapSaveFile<T, TK> getHashMapSave(String saveName) {
        return getHashMapSave(saveName, Utils.getFile(saveDirectory, saveName), null);
    }

    public <T, TK> HashMapSaveFile<T, TK> getHashMapSave(String saveName, HashMap<T, TK> defaultValues) {
        return getHashMapSave(saveName, Utils.getFile(saveDirectory, saveName), defaultValues);
    }

    public <T, TK> HashMapSaveFile<T, TK> getHashMapSave(String saveName, File saveFile, HashMap<T, TK> defaultValues) {
        HashMapSaveFile<T, TK> map;
        if (!saveFiles.containsKey(saveName)) {
            map = new HashMapSaveFile<>(saveFile, defaultValues);
            saveFiles.put(saveName, map);
        } else {
            map = (HashMapSaveFile<T, TK>) saveFiles.get(saveName);
        }
        return map;
    }
}
