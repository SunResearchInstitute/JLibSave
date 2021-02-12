package net.sunthecourier.jlibsave;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.File;

public class Utils {
	/**
	 * GSON instance used to create Save files.
	 */
	@Getter
	@Setter
	private static Gson prettyGson = new GsonBuilder()
			.setPrettyPrinting()
			.disableHtmlEscaping()
			.create();

	@NotNull
	@Contract("_, _ -> new")
	public static File getFile(File folder, String file) {
		return new File(folder, file);
	}
}
