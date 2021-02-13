package net.sunthecourier.jlibsave.types.saves;

import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.lang.reflect.Type;
import java.util.concurrent.CompletableFuture;

public abstract class ISaveFile {
	@Getter
	protected final File saveInfo;
	@Getter
	protected Type type;
	@Getter
	@Setter
	private boolean isConfig;

	public ISaveFile(File path, Type typeToken, boolean isConfig) {
		saveInfo = path;
		type = typeToken;
		this.isConfig = isConfig;
	}

	public abstract void write();

	public abstract CompletableFuture<Void> writeAsync();

	public abstract void reload();
}
