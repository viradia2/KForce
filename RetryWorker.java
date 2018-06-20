import java.rmi.RemoteException;
import java.util.concurrent.atomic.AtomicBoolean;

public class RetryWorker implements Runnable {

	AtomicBoolean result;
	final int maxTries;
	int count;
	final int numOfMiliSecondsToSleep;
	
	public RetryWorker(AtomicBoolean result,int count,int maxTries,int numOfMiliSecondsToSleep) {
		this.result = result;
		this.count = count;
		this.maxTries = maxTries;
		this.numOfMiliSecondsToSleep = numOfMiliSecondsToSleep;
	}

	@Override
	public void run(){
		try {
			callWebservice();
			Thread.sleep(numOfMiliSecondsToSleep);
		}catch(RemoteException ex) {
			System.out.println("Remote call failed.");
			ex.printStackTrace();
	        if (++count == maxTries) result.set(true);
		}catch(Exception ex) {
			System.out.println("unknown Exception");
	        if (++count == maxTries) result.set(true);
		}
	}

	private void callWebservice() throws RemoteException{
		
		throw new RemoteException("Web service call failed.");
		
	}
	
}
