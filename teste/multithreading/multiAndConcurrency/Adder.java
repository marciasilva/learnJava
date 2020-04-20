package multiAndConcurrency;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class Adder implements Callable {

	private String inFile, outFile;

	public Adder(String inFile, String outFile) {
		super();
		this.inFile = inFile;
		this.outFile = outFile;
	}

	@SuppressWarnings("unchecked")
	public static void readWriteFilesWithThread() throws InterruptedException {
		String[] inFiles = { "./resources/file1.txt", "./resources/file2.txt", "./resources/file3.txt",
				"./resources/file4.txt", "./resources/file5.txt", "./resources/file6.txt" };
		String[] outFiles = { "./resources/file1.out.txt", "./resources/file2.out.txt", "./resources/file3.out.txt",
				"./resources/file4.out.txt", "./resources/file5.out.txt", "./resources/file6.out.txt" };

//		Thread[] threads = new Thread[inFiles.length];

		// it will limit to 3 thread at the same time
		ExecutorService es = Executors.newFixedThreadPool(3);

		// Use with Callable

		Future<Integer>[] results = new Future[inFiles.length];

		for (int i = 0; i < inFiles.length; i++) {
			Adder adder = new Adder(inFiles[i], outFiles[i]);
//			threads[i] = new Thread(adder);
//			threads[i].start();

			// es.submit(adder); -> use with Runnable

			results[i] = es.submit(adder);

			for (Future<Integer> result : results) {
				try {
					int value = result.get(); // blocks until return value available
					System.out.println("Total: " + value);
				} catch (ExecutionException e) { // Exeception raised in Adder
					Throwable adderEx = e.getCause(); // Get the Adder exception
					System.out.println(adderEx);
				} catch (Exception e) { // Non-Adder exceptions
					System.out.println(e.getClass().getSimpleName() + " - " + e.getMessage());
				}

			}

		}

//		for (Thread thread:threads) {
//			thread.join(); //Blocks waiting for thread completion
//		}

		try {
			es.shutdown();
			es.awaitTermination(60, TimeUnit.SECONDS);

		} catch (Exception e) {
			System.out.println(e.getClass().getSimpleName() + " - " + e.getMessage());
		}

	}

	private void doAddAndSaveResult() throws IOException {
		int total = 0;
		String line = null;

		try (BufferedReader reader = Files.newBufferedReader(Paths.get(inFile))) {
			while ((line = reader.readLine()) != null) {
				total += Integer.parseInt(line);
			}
		}

		try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(outFile))) {
			writer.write("Total: " + total);
		}
	}

	private int doAddAndReturnTotal() throws IOException {
		int total = 0;
		String line = null;

		try (BufferedReader reader = Files.newBufferedReader(Paths.get(inFile))) {
			while ((line = reader.readLine()) != null) {
				total += Integer.parseInt(line);
			}
		}
		return total;
	}
	/*
	 * USE THIS WITH INTERFACE
	 * 
	 * @Override public void run() { try { doAddAndSaveResult(); } catch
	 * (IOException e) { System.out.println(e.getClass().getSimpleName() + " - " +
	 * e.getMessage()); } }
	 */

	@Override
	public Integer call() throws IOException {
		return doAddAndReturnTotal();
	}

}
