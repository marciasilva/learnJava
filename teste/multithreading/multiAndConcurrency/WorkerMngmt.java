package multiAndConcurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class WorkerMngmt {

	public static void operateWithBonus(Boolean applyBonus) {

		BankAccount myBA = new BankAccount();
		TxWorker[] workers = null;
		if (applyBonus) {
			workers = createSimpleTransactions(myBA);
		} else {
			workers = createTransactionsWithBonus(myBA);
		}
				
		execOperation(myBA, workers);
	}

	private static TxWorker[] createSimpleTransactions(BankAccount myBA) {
		TxWorker transaction1 = new TxWorker(myBA, 'd', 200);
		TxWorker transaction2 = new TxWorker(myBA, 'w', 36);
		TxWorker transaction3 = new TxWorker(myBA, 'w', 120);
		TxWorker[] workers = { transaction1, transaction2, transaction3 };
		return workers;
	}

	private static TxWorker[] createTransactionsWithBonus(BankAccount myBA) {
		TxWorker transaction1 = new TxPromoWorker(myBA, 'd', 200);
		TxWorker transaction2 = new TxPromoWorker(myBA, 'w', 36);
		TxWorker transaction3 = new TxPromoWorker(myBA, 'd', 420);
		TxWorker[] workers = { transaction1, transaction2, transaction3 };
		return workers;
	}

	private static void execOperation(BankAccount myBA, TxWorker[] workers) {
		ExecutorService es = Executors.newFixedThreadPool(3);

		for (TxWorker worker : workers) {
			es.submit(worker);
		}

		try {
			es.shutdown();
			es.awaitTermination(60, TimeUnit.SECONDS);

		} catch (Exception e) {
			System.out.println(e.getClass().getSimpleName() + " - " + e.getMessage());
		}

		System.out.println("Total: " + myBA.getBalance());
	}
}
