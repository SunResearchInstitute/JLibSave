package net.sunthecourier.jlibsave.types.saves.map;

import net.sunthecourier.jlibsave.types.saves.SaveFile;

import java.io.File;
import java.lang.reflect.Type;
import java.util.LinkedHashMap;
import java.util.function.Supplier;

public class LinkedHashMapSave<T, TK> extends SaveFile<LinkedHashMap<T, TK>> {
	/**
	 * @param path
	 * @param fallbackData If the class fails to read data from the JSON this will be used instead
	 * @param typeToken
	 */
	public LinkedHashMapSave(File path, Supplier<LinkedHashMap<T, TK>> fallbackData, Type typeToken) {
		super(path, fallbackData, typeToken);
	}
}
