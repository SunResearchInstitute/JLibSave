package net.sunthecourier.jlibsave.types.saves.map;


import net.sunthecourier.jlibsave.types.saves.SaveFile;

import java.io.File;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.function.Supplier;

public class HashMapSave<T, TK> extends SaveFile<HashMap<T, TK>> {
	/**
	 * @param path
	 * @param fallbackData If the class fails to read data from the JSON this will be used instead
	 * @param typeToken
	 */
	public HashMapSave(File path, Supplier<HashMap<T, TK>> fallbackData, Type typeToken) {
		super(path, fallbackData, typeToken);
	}
}
