package multiAndConcurrency;

import java.lang.reflect.Field;
import java.util.Random;

public class BankAccount {

	private int balance = 0;
	private final String id;

	public BankAccount(int balance, String id) {
		this.balance = balance;
		this.id = id;
	}

	public BankAccount() {
		Random rand = new Random();
		this.id = String.valueOf(rand.nextInt(1000));
	}

	public String getId() {
		return id;
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

	public void fieldInfo(Object obj) {
		Class<?> theClass = obj.getClass();
		Field[] fields = theClass.getFields();
		displayFields(fields);
		Field[] declaredFields = theClass.getDeclaredFields();
		displayFields(declaredFields);
	}

	private void displayFields(Field[] arr) {
		for (Field f : arr) {
			System.out.println(f.getName() + " : " + f.getType());
		}
	}
}
