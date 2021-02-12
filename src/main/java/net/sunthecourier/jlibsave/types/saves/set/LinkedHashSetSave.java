package net.sunthecourier.jlibsave.types.saves.set;

import com.google.gson.reflect.TypeToken;
import net.sunthecourier.jlibsave.SaveController;
import net.sunthecourier.jlibsave.types.saves.SaveFile;

import java.io.File;
import java.lang.reflect.Type;
import java.util.LinkedHashSet;
import java.util.function.Supplier;

public class LinkedHashSetSave<T> extends SaveFile<LinkedHashSet<T>> {
	/**
	 * @param path         Path of save. Can be found using {@link SaveController#getSavePath(String)}.
	 * @param fallbackData If the class fails to read data from the JSON this will be used instead
	 * @param typeToken    The type token including the Save. Best gotten using {@link TypeToken}.
	 */
	public LinkedHashSetSave(File path, Supplier<LinkedHashSet<T>> fallbackData, Type typeToken) {
		super(path, fallbackData, typeToken);
	}
}
