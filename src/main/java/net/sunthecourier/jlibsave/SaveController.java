package net.sunthecourier.jlibsave;

import lombok.Getter;
import net.sunthecourier.jlibsave.types.saves.ISaveFile;
import net.sunthecourier.jlibsave.types.saves.map.HashMapSave;
import net.sunthecourier.jlibsave.types.saves.map.LinkedHashMapSave;
import net.sunthecourier.jlibsave.types.saves.set.HashSetSave;
import net.sunthecourier.jlibsave.types.saves.set.LinkedHashSetSave;

import java.io.File;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

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

    public <T, TK> void registerHashMapSave(String saveName, Supplier<HashMap<T, TK>> defaultData, Type HashMapType) {
        saveFiles.put(saveName, new HashMapSave<>(this.getSavePath(saveName), defaultData, HashMapType));
    }

    public <T, TK> void registerHashMapSave(String saveName, Type HashMapType) {
        registerHashMapSave(saveName, HashMap::new, HashMapType);
    }

    public <T, TK> void registerLinkedHashMapSave(String saveName, Supplier<LinkedHashMap<T, TK>> defaultData, Type LinkedHashMapType) {
        saveFiles.put(saveName, new LinkedHashMapSave<>(this.getSavePath(saveName), defaultData, LinkedHashMapType));
    }

    public <T, TK> void registerLinkedHashMapSave(String saveName, Type LinkedHashMapType) {
        registerLinkedHashMapSave(saveName, LinkedHashMap::new, LinkedHashMapType);
    }

    public <T> void registerHashSetSave(String saveName, Supplier<HashSet<T>> defaultData, Type HashSetType) {
        saveFiles.put(saveName, new HashSetSave<>(this.getSavePath(saveName), defaultData, HashSetType));
    }

    public <T> void registerHashSetSave(String saveName, Type HashSetType) {
        registerHashSetSave(saveName, HashSet::new, HashSetType);
    }

    public <T> void registerLinkedHashSetSave(String saveName, Supplier<LinkedHashSet<T>> defaultData, Type LinkedHashSetType) {
        saveFiles.put(saveName, new LinkedHashSetSave<>(this.getSavePath(saveName), defaultData, LinkedHashSetType));
    }

    public <T> void registerLinkedHashSetSave(String saveName, Type LinkedHashSetType) {
        registerHashSetSave(saveName, LinkedHashSet::new, LinkedHashSetType);
    }

    public <T extends ISaveFile> T getSave(String saveName) {
        if (saveFiles.containsKey(saveName)) {
            return (T) saveFiles.get(saveName);
        }
        return null;
    }

    public void registerCustomSave(ISaveFile saveFile, String saveName) {
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

    public File getSavePath(String saveName) {
        return Utils.getFile(saveDirectory, saveName);
    }
}
