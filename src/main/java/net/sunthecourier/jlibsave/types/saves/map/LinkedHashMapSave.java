package net.sunthecourier.jlibsave.types.saves.map;

import com.google.gson.reflect.TypeToken;
import net.sunthecourier.jlibsave.SaveController;
import net.sunthecourier.jlibsave.types.saves.SaveFile;

import java.io.File;
import java.lang.reflect.Type;
import java.util.LinkedHashMap;
import java.util.function.Supplier;

public class LinkedHashMapSave<T, TK> extends SaveFile<LinkedHashMap<T, TK>> {
	/**
	 * @param path         Path of save. Can be found using {@link SaveController#getSavePath(String)}.
	 * @param fallbackData If the class fails to read data from the JSON this will be used instead
	 * @param typeToken    The type token including the Save. Best gotten using {@link TypeToken}.
	 */
	public LinkedHashMapSave(File path, Supplier<LinkedHashMap<T, TK>> fallbackData, Type typeToken) {
		super(path, fallbackData, typeToken);
	}
}
