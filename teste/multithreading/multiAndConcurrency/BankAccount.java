package multiAndConcurrency;

public class BankAccount {

	private int balance;

	public BankAccount(int startBalance) {
		this.balance = startBalance;
	}

	public synchronized int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	/*
	 * Non atomic operation read current value from memory perform addition write
	 * result back to memory -- it is not thread safe, must be treated
	 */
	public synchronized void deposit(int amount) {
		this.balance += amount;
	}
	
	public synchronized void withdrawal(int amount) {
		this.balance -= amount;
	}

}
