package multiAndConcurrency;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Random;

import reflection.AccountWorker;
import reflection.ProcessedBy;

@ProcessedBy(AccountWorker.class)
public class BankAccount implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int balance = 0;
	private final String id;
	private char lastTxType;
	private int lastTxAmount;

	public BankAccount(int balance, String id) {
		this.balance = balance;
		this.id = id;
	}

	public char getLastTxType() {
		return lastTxType;
	}

	public void setLastTxType(char lastTxType) {
		this.lastTxType = lastTxType;
	}

	public int getLastTxAmount() {
		return lastTxAmount;
	}

	public void setLastTxAmount(int lastTxAmount) {
		this.lastTxAmount = lastTxAmount;
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
		this.lastTxType = 'd';
		this.lastTxAmount = amount;
	}

	public synchronized void withdrawal(int amount) {
		this.balance -= amount;
		this.lastTxType = 'w';
		this.lastTxAmount = amount;
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

	// Method access with reflection - reflection is slower than traditional coding
	void callGetId(Object obj) {
		try {
			Class<?> theClass = obj.getClass();
			Method m = theClass.getMethod("getId");
			Object result = m.invoke(obj);
			System.out.println("Result: " + result);
		} catch (Exception e) {
			System.out.println(e.getClass().getSimpleName() + " - " + e.getMessage());
		}
	}

	void callDeposit(Object obj, int amt) {
		try {
			Class<?> theClass = obj.getClass();
			Method m = theClass.getMethod("deposit", int.class);
			Object result = m.invoke(obj, amt);
			System.out.println("Result: " + result);
		} catch (Exception e) {
			System.out.println(e.getClass().getSimpleName() + " - " + e.getMessage());
		}
	}	
}
