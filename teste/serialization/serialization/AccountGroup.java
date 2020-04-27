package serialization;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import multiAndConcurrency.BankAccount;

public class AccountGroup {

	private Map<String, BankAccount> accountMap;
	private transient int totalBalance;

	public AccountGroup() {
		this.accountMap = new HashMap<>();
	}

	public Map<String, BankAccount> getAccountMap() {
		return accountMap;
	}

	public void setAccountMap(Map<String, BankAccount> accountMap) {
		this.accountMap = accountMap;
	}

	public int getTotalBalance() {
		return totalBalance;
	}

	public void setTotalBalance(int totalBalance) {
		this.totalBalance = totalBalance;
	}

	public void addAccount(BankAccount ba) {
		this.totalBalance += ba.getBalance();
		this.accountMap.put(ba.getId(), ba);
	}

	public void saveGroup(String fileName) throws IOException {
		try (ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(Paths.get(fileName)))) {
			oos.writeObject(this);
		}
	}

	// Used for serialization
	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
		in.defaultReadObject();
		for (BankAccount account : accountMap.values()) {
			totalBalance += account.getBalance();
		}
	}
}
