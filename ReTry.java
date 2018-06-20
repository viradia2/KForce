import java.rmi.RemoteException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class ReTry {

	public static void main(String[] args) throws InterruptedException{
		ReTry rt = new ReTry();
		rt.call(2,3);
	}
	
	/**
	 * Method attempts web service call for n times
	 * 
	 * @param secondsToWait
	 * @param numberOfTry
	 * @throws InterruptedException
	 */
	private void call(int secondsToWait,int numberOfTries) throws InterruptedException {
		
		// wait for this many milliseconds (seep happens in number of milliseconds)
		final AtomicInteger milliSecondsToWait = new AtomicInteger(secondsToWait*1000);
		// incremental counter
		final AtomicInteger counter = new AtomicInteger(0);
		// maximum tries
		final int maxTries = numberOfTries;
		// flag to control loop
		final AtomicBoolean tryFlag = new AtomicBoolean(true);
		
		// run unitl flag is false
		while(tryFlag.get()) {
			
			// create a thread with Task
			Thread t = new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						// web service call
						callBackMehod();
						
						// log that web service call is success
						System.out.println("Call Success.");
						// turn off the loop
						tryFlag.set(false);
						
					// handle exception during webservice call	
					} catch (Exception e) {
						try {
							// wait for seconds
							Thread.sleep(milliSecondsToWait.get());
						} catch (InterruptedException e1) {
							// log exception if sleep call fails
							e1.printStackTrace();
						}
						
						// log number of attempt failed
						System.out.println(counter.get());
						
						// max try limit reached then terminate the loop
						if (counter.addAndGet(1) == maxTries) {
							// turn off the loop
							tryFlag.set(false);
						}
					}
				}

			});
			
			// start the thread
			t.start();
			// wait for it to complete
			// this call can throw InterruptedException log the details
			t.join();
		}
	}

	private void callBackMehod() throws RemoteException {
		throw new RemoteException("remote");
	}

}

