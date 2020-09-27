package net.sunthecourier.jlibsave.types.saves.set;

import net.sunthecourier.jlibsave.types.saves.SaveFile;

import java.io.File;
import java.lang.reflect.Type;
import java.util.LinkedHashSet;
import java.util.function.Supplier;

public class LinkedHashSetSave<T> extends SaveFile<LinkedHashSet<T>> {
	/**
	 * @param path
	 * @param fallbackData If the class fails to read data from the JSON this will be used instead
	 * @param typeToken
	 */
	public LinkedHashSetSave(File path, Supplier<LinkedHashSet<T>> fallbackData, Type typeToken) {
		super(path, fallbackData, typeToken);
	}
}
