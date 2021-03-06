package files;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import controlling.ExerciseCmdLine;
import controlling.StoreAndLoadProperty;
import filesMngmt.Helper;
import filesMngmt.MyAutoCloseable;
import filesMngmt.ZipFileMngmt;
import formattingAndExpressions.WorkingWithString;
import logSystemMngmt.DoLogCalls;
import multiAndConcurrency.Adder;
import multiAndConcurrency.BankAccount;
import multiAndConcurrency.WorkerMngmt;
import reflection.ProcessedBy;
import reflection.TaskWorker;
import reflection.WorkHandler;
import serialization.WorkingWithSerialization;
import workingWithCollections.SimpleCollection;

public class Main {
	public static void main(String[] args) throws InterruptedException, IOException {
//		// TODO Auto-generated method stub
		try {
//			doTryCatchFinally();
//			doTryWithResources();
//		    doTryWithResourcesMulti();
//			doCloseThing();
//			playWithZipFiles();			

		} catch (Exception e) {
//			System.out.println(e.getClass().getSimpleName() + " - " + e.getMessage());
//			e.printStackTrace();
		}
//
//		WorkingWithString.creatingAStringComposing();
//		WorkingWithString.handleEmptyString();
//		WorkingWithString.handleStringWithFormat();
//		WorkingWithString.handleFormatFlag();
//		WorkingWithString.usingRegExp();
//
//		SimpleCollection.usingArrayList();
//		SimpleCollection.removingAMember();
//		SimpleCollection.collectionsWithLambdas();
//		SimpleCollection.converting();
//		SimpleCollection.sorting();
//		SimpleCollection.usingMap();
//
//		/* Edit run configurations -> arguments */
//		ExerciseCmdLine.readFileFromArgs(args);
//		StoreAndLoadProperty.createProperty();
//		StoreAndLoadProperty.writePropertyOnSimpleText();
//		StoreAndLoadProperty.readPropertiesFromSimpleText();
//		StoreAndLoadProperty.writePropertiesInXML();
//		StoreAndLoadProperty.readPropertiesFromXML();
//		StoreAndLoadProperty.createPropertyDefaults();
//
//		StoreAndLoadProperty.loadDefaultPropertiesFromFile();
//		
//	
//		DoLogCalls.printlogMessages();

//		Adder.readWriteFilesWithThread();

		WorkerMngmt.operateWithBonus(false);
		WorkerMngmt.operateWithBonus(true);
		
		
		
		WorkingWithSerialization.serializeAnObject();
		

	}

	public static void playWithZipFiles() {
		// Handle zip File

		String[] data = { "Line 1", "Line 2 2", "Line 3 3 3", "Line 4 4 4 4", "Line 5 5 5 5 5" };

		ZipFileMngmt zipMngt = new ZipFileMngmt();

		try {
			FileSystem zipFs = zipMngt.openZip(Paths.get("myDataZip.zip"));
			zipMngt.copyToZip(zipFs);
			zipMngt.writeToFileInZip1(zipFs, data);
			zipMngt.writeToFileInZip2(zipFs, data);

		} catch (Exception e) {
			System.out.println(e.getClass().getSimpleName() + " - " + e.getMessage());
		}

	}

	public static void doTryCatchFinally() throws IOException {
		char[] buff = new char[8];
		int length;
		Reader reader = null;

		try {
			reader = Helper.openReader("file1.txt");
			while ((length = reader.read(buff)) >= 0) {
				System.out.println("\n length: " + length);
				for (int i = 0; i < length; i++) {
					char charVal = buff[i];
					System.out.println(charVal);
				}
			}
		} catch (IOException e) {
			System.out.println(e.getClass().getSimpleName() + " - " + e.getMessage());

		} finally {
			try {
				if (reader != null) {
					reader.close();
				}
			} catch (IOException e2) {
				System.out.println(e2.getClass().getSimpleName() + " - " + e2.getMessage());
			}

		}
	}

	public static void doTryWithResources() {
		char[] buff = new char[8];
		int length;

		// como o resource � declardo no try n�o precisa do finally para encerra-lo
		try (Reader reader = Helper.openReader("file1.txt")) {
			while ((length = reader.read(buff)) >= 0) {
				System.out.println("\n length: " + length);
				for (int i = 0; i < length; i++) {
					char charVal = buff[i];
					System.out.println(charVal);
				}
			}
		} catch (IOException e) {
			System.out.println(e.getClass().getSimpleName() + " - " + e.getMessage());

		}
	}

	// copia o conteudo de um arquivo para outro
	public static void doTryWithResourcesMulti() {
		char[] buff = new char[8];
		int length;

		try (Reader reader = Helper.openReader("file1.txt"); Writer writer = Helper.openWriter("file2.txt")) {
			while ((length = reader.read(buff)) >= 0) {
				System.out.println("\n length: " + length);
				writer.write(buff, 0, length);
			}
		} catch (IOException e) {
			System.out.println(e.getClass().getSimpleName() + " - " + e.getMessage());

		}
	}

	public static void doCloseThing() throws Exception {
		try (MyAutoCloseable ac = new MyAutoCloseable()) {
			ac.saySomething();
		} catch (IOException e) {
			System.out.println(e.getClass().getSimpleName() + " - " + e.getMessage());

			for (Throwable t : e.getSuppressed()) {
				System.out.println("Supressed: " + t.getMessage());
			}
		}

	}

	public static void readLinesWithBufferedREader() throws IOException {
		try (BufferedReader br = Files.newBufferedReader(Paths.get("data.txt"))) {
			String inValue;
			while ((inValue = br.readLine()) != null) {
				System.out.println(inValue);
			}
		}
	}

	public static void readAllLinesInFile() throws IOException {
		List<String> lines = Files.readAllLines(Paths.get("data.txt"));

		for (String line : lines) {
			System.out.println(line);
		}
	}

	// Work Dispatch System Invocation
	void startWork(String workerTypeName, Object workerTarget) {
		try {
			BankAccount acct1 = new BankAccount();

			startWork("teste.reflection.AccountWorker", acct1);

			// Get information of worker class
			Class<?> workerType = Class.forName(workerTypeName);

			// Get information of target class
			Class<?> targetType = workerTarget.getClass();

			// From worker class, get the type of constructor that accepts the targeType
			@SuppressWarnings("rawtypes")
			Constructor c = workerType.getConstructor(targetType);
			Object worker = c.newInstance(workerTarget);

			Method doWork = workerType.getMethod("doWork");
			doWork.invoke(worker);

		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | InstantiationException
				| ClassNotFoundException | NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getClass().getSimpleName() + " - " + e.getMessage());
			e.printStackTrace();
		}

	}

	// Work Dispatch System Interface Invocation
	void startWorkWithInterface(String workerTypeName, Object workerTarget) {

		try {
			Class<?> workerType = Class.forName(workerTypeName);
			TaskWorker worker = (TaskWorker) workerType.newInstance();
			worker.setTarget(workerTarget);
			worker.doWork();

		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// Acessing Annotations
	void startWorkingWithAnnotations(String workerTypeName, Object workerTarget) throws Exception {
		ExecutorService pool = Executors.newFixedThreadPool(3);;

		Class<?> workerType = Class.forName(workerTypeName);
		TaskWorker worker = (TaskWorker) workerType.newInstance();
		worker.setTarget(workerTarget);

		WorkHandler wh = workerType.getAnnotation(WorkHandler.class);
		if(wh == null ) {
			System.out.println("WorkHandler annotation not found");
		}
		if (wh.useThreadPool()) {
			pool.submit(new Runnable() {
				public void run() {
					worker.doWork();
				}
			});
		} else {
			worker.doWork();
		}
	}
	
	void startWorkSelfContained(String workerTypeName, Object workerTarget) throws Exception {
		Class <?> targetType = workerTarget.getClass();
		ProcessedBy pb = targetType.getAnnotation(ProcessedBy.class);
		Class <?> workerType = pb.value();
		TaskWorker worker = (TaskWorker) workerType.newInstance();
		Method doWork = workerType.getMethod("doWork");
		doWork.invoke(worker);			
	}
	
	/*
	 * 
	 * 
	 * 
	 * //Chaining Streams void doChain(InputStream in) throws IOException {
	 * 
	 * int length; char [] charBuff = new char [128]; //passa a referencia pro
	 * inputstream reader try(InputStreamReader rdr = new InputStreamReader(in) {
	 * while((length = rdr.read(charBuff)) >= 0)
	 * 
	 * 
	 * 
	 * 
	 * try ( BufferedReader br = new BufferedReader (new FileReader ("file1.txt") {
	 * int invVal; while intVal = br.read() >= 0
	 * 
	 * 
	 * buffer streams and line breaks line breaks are different across platforms
	 * line breaks knows what do do on the current platform
	 * 
	 * BufferedReader can read a new line
	 * 
	 * 
	 * Write with line breaks
	 * 
	 * 
	 * void writeData (String[] data) throws IOException
	 * 
	 * try(BufferedWriter bw = new BufferedWriter (new FilwWriter("data.txt")))
	 * 
	 * for (String d:data) { bw.write(d); bw.newLine();
	 * 
	 * 
	 * 
	 * reading lines
	 * 
	 * BufferedReader br
	 * 
	 * String inValue;
	 * 
	 * while(inVal = br.readLine() != null) sysout inValue
	 * 
	 */

}
