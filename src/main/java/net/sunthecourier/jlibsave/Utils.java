package net.sunthecourier.jlibsave;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.Contract;

import java.io.File;

@UtilityClass
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

	@NonNull
	@Contract("_, _ -> new")
	public static File getFile(File folder, String file) {
		return new File(folder, file);
	}
}
