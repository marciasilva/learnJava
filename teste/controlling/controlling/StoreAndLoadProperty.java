package controlling;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;


/*
 * Properties can be persisted
 * Can be written to and read from a stream
 * Can optionally include comments
 * Supported formats: simple text and XML
 * */

public class StoreAndLoadProperty {
	
	private static Properties defaultProps;	

	public StoreAndLoadProperty() {
		super();
	}

	public Properties getDefaultProps() {
		return defaultProps;
	}

	public static void createProperty() {
		Properties props = new Properties();
		props.setProperty("displayName", "Jane Doe");
		String name = props.getProperty("displayName");
		System.out.println(name);

		String acctNum = props.getProperty("accountNumber");
		System.out.println(acctNum);

		String nextPosition = props.getProperty("position", "1");
		System.out.println(nextPosition);
	}

	public static void createPropertyDefaults() {
		Properties defaults = new Properties();
		defaults.setProperty("position", "1");

		Properties props = new Properties(defaults);
		String nextPos = props.getProperty("position");

		int iPos = Integer.parseInt(nextPos);
		props.setProperty("position", Integer.toString(++iPos));
		System.out.println("Defaults: ");
		printProperties(defaults);

		System.out.println("Props: ");
		printProperties(props);
	}

	public static void writePropertyOnSimpleText() throws IOException {

		Properties props = createPropertyNameAccount();

		try (Writer writer = Files.newBufferedWriter(Paths.get("xyz.properties"))) {
			props.store(writer, "My comment");
		}
	}

	public static void readPropertiesFromSimpleText() {
		Properties props = new Properties();
		try (Reader reader = Files.newBufferedReader(Paths.get("myapp.properties"))) {
			props.load(reader);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		printProperties(props);
	}

	/*
	 * One key/value pair per XML element key stored as key attribute value stored
	 * as element attribute
	 */

	public static void writePropertiesInXML() throws IOException {
		Properties props = createPropertyNameAccount();

		try (OutputStream out = Files.newOutputStream(Paths.get("props.xml"))) {
			props.storeToXML(out, "my comment");
		}
	}

	public static void readPropertiesFromXML() throws IOException {
		Properties props = new Properties();
		try (InputStream in = Files.newInputStream(Paths.get("myAppProps.xml"))) {
			props.loadFromXML(in);
		}

		printProperties(props);

	}

	public static void loadDefaultPropertiesFromFile() {
		try {
			
			try (InputStream inputStream = StoreAndLoadProperty.class.getResourceAsStream("MyDefaultValues.xml")) {
				defaultProps = new Properties();
				defaultProps.loadFromXML(inputStream);
			}
			Properties userProps = new Properties(defaultProps);
			loadUserProperties(userProps);
			
			String welcomeMessage = userProps.getProperty("welcomeMessage");
			System.out.println(welcomeMessage);
			
			if(userProps.getProperty("isFirstRun").equalsIgnoreCase("Y")) {
				userProps.setProperty("welcomeMessage", "Welcome back");
				userProps.setProperty("farewellMessage", "Things will be fine");
				userProps.setProperty("isFirstRun", "N");
				saveUserProps(userProps);
			}
			
			System.out.println("Default props: ");
			printProperties(defaultProps);
			System.out.println("User props: ");
			printProperties(userProps);
			
		} catch (IOException e) {
			System.out.println("Exception <" + e.getClass().getSimpleName() + "> " + e.getMessage());
		}

	}

	private static void printProperties(Properties prop) {
		prop.keySet().stream().map(key -> key + ": " + prop.getProperty(key.toString())).forEach(System.out::println);
	}

	private static Properties createPropertyNameAccount() {
		Properties props = new Properties();
		props.setProperty("displayName", "Jane Doe");
		props.setProperty("accountNumber", "123-456-789");

		return props;
	}

	private static Properties loadUserProperties(Properties userProps) throws IOException {
		Path userFile = Paths.get("userValues.xml");
		if (Files.exists(userFile)) {
			try (InputStream inputStream = Files.newInputStream(userFile)) {
				userProps.loadFromXML(inputStream);
			}
		}
		return userProps;
	}
	
	private static void saveUserProps(Properties userProps) throws IOException {
		try(OutputStream outputStream = Files.newOutputStream(Paths.get("userValues.xml"))) {
			userProps.storeToXML(outputStream, null);
		}
	}

}
