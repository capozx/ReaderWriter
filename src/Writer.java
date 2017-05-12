import java.util.LinkedList;
import java.util.concurrent.Semaphore;

public class Writer implements Runnable {

	public LinkedList<Integer> library;
	public Semaphore semaphore;
	public int readerCount;
	public Object mutex;
	
	Writer(LinkedList<Integer> l , Semaphore s,int rC, Object m ){
		this.library = l;
		this.semaphore = s;
		this.readerCount = rC;
		this.mutex = m;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("Writer started.");
		while(true){
			synchronized(mutex){
				if(readerCount != 0){
					try {
						System.out.println("readerCount != 0. Writer is waiting...");
						mutex.wait();
						library.add((int) (Math.random() * 10));
						System.out.println("Writer has written. " + this.library);
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}
}
