package serialization;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import multiAndConcurrency.BankAccount;

public class WorkingWithSerialization {

	private static BankAccount acct;

	public BankAccount getAcct() {
		return acct;
	}

	public static void serializeAnObject() {
		// TODO Auto-generated method stub
		acct = new BankAccount(500, "1234");
		acct.deposit(250);
		saveAccount(acct, "account.dat");
	}
	
	//Deserializing an Object
	public BankAccount loadAccount(String filename) { 
		BankAccount ba = null;
		try (ObjectInputStream objectStream = new ObjectInputStream(Files.newInputStream(Paths.get(filename)))) {
			ba = (BankAccount) objectStream.readObject();
		} catch (IOException e) {
			
		} catch (ClassNotFoundException e) {
			
		}
		return ba;
	}
	
	public void serializingWithTransientField() throws IOException {
		BankAccount bAc = new BankAccount(500, "1234");
		BankAccount bAc2 = new BankAccount(760, "8764");
		
		AccountGroup ag = new AccountGroup();
		ag.addAccount(bAc);
		ag.addAccount(bAc2);
		
		ag.saveGroup("group.dat");
		
	}

	private static void saveAccount(BankAccount acct2, String filename) {
		try(ObjectOutputStream objectStream = new ObjectOutputStream(Files.newOutputStream(Paths.get(filename)))) {
			objectStream.writeObject(acct2);
		} catch (IOException e) {
			
		}
		
	}	
}
