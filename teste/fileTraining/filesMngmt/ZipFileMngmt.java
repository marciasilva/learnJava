package filesMngmt;

import java.io.BufferedWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ZipFileMngmt {

	public FileSystem openZip(Path zipPath) throws IOException, URISyntaxException {
		Map<String, String> providerProps = new HashMap<>();
		providerProps.put("create", "true");

		// Get the path and convert to valid uri
		URI zipUri = new URI("jar:file", zipPath.toUri().getPath(), null);

		FileSystem zipFs = FileSystems.newFileSystem(zipUri, providerProps);

		return zipFs;
	}

	public void copyToZip(FileSystem zipFs) throws IOException {
		Path sourceFile = Paths.get("file1.txt");
		Path destinationFile = zipFs.getPath("/file1Copied.txt");

		Files.copy(sourceFile, destinationFile, StandardCopyOption.REPLACE_EXISTING);
	}

	public void writeToFileInZip1(FileSystem zipFs, String[] data) throws IOException {
		try (BufferedWriter writer = Files.newBufferedWriter(zipFs.getPath("/newFile1.txt"))) {
			for (String d : data) {
				writer.write(d);
				writer.newLine();
			}
		}
	}

	public void writeToFileInZip2(FileSystem zipFs, String[] data) throws IOException {
		Files.write(zipFs.getPath("/newFile2.txt"), Arrays.asList(data), Charset.defaultCharset(),
				StandardOpenOption.CREATE);
	}

}
