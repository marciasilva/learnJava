package reflection;

import java.lang.reflect.Modifier;

import multiAndConcurrency.BankAccount;

public class HighVolumeAccount extends BankAccount implements Runnable {

	public HighVolumeAccount(int startBalance, String id) {
		super(startBalance, id);
		// TODO Auto-generated constructor stub
	}

	private int[] readDailyDeposits() {
		int[] dummyDepositArray = {10,10,2400};
		return dummyDepositArray;
	}

	private int[] readDailyWithdrawals () {
		int[] dummyWithdrawalsArray = {10,10,56,876};
		return dummyWithdrawalsArray;		
	}

	@Override
	public void run() {

		for (int depositAmt : readDailyDeposits()) {
			deposit(depositAmt);
		}

		for (int withdrawalAmt : readDailyWithdrawals()) {
			withdrawal(withdrawalAmt);
		}

	}

	public void classInfo(Object obj) {
		Class<?> theClass = obj.getClass();
		System.out.println(theClass.getSimpleName());

		Class<?> superClass = theClass.getSuperclass();
		System.out.println(superClass.getSimpleName());

		Class<?>[] interfaces = theClass.getInterfaces();

		for (Class<?> interfaceC : interfaces) {
			System.out.println(interfaceC.getSimpleName());
		}
	}

	public void typeModifiers(Object obj) {
		Class<?> theClass = obj.getClass();
		int modifiers = theClass.getModifiers();

		if ((modifiers & Modifier.FINAL) > 0) {
			System.out.println("bitwise check - final");
		}

		if (Modifier.isFinal(modifiers)) {
			System.out.println("method check - final");
		}

		if (Modifier.isPrivate(modifiers)) {
			System.out.println("method check - private");
		} else if (Modifier.isProtected(modifiers)) {
			System.out.println("method check - protected");
		} else if (Modifier.isPublic(modifiers)) {
			System.out.println("method check - public");
		}
	}

}
