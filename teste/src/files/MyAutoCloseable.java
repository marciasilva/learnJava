package files;

import java.io.IOException;

public class MyAutoCloseable implements AutoCloseable {

	public void saySomething() throws IOException{
		System.out.println("Something");
		throw new IOException ("Exception from saySomething");		
	}
	
	@Override
	public void close() throws Exception {
		System.out.println("close");
		throw new IOException ("Exception from close");		
	}
	

}
