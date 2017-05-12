import java.util.LinkedList;
import java.util.concurrent.Semaphore;

public class Reader implements Runnable {

	public LinkedList<Integer> library;
	public Semaphore semaphore;
	public int readerCount;
	public Object mutex;
	private int id;
	
	Reader(LinkedList<Integer> l , int i, Semaphore s, Object m){
		this.library = l;
		this.id = i;
		this.readerCount = 0;
		this.mutex = m;
		this.semaphore = s;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			synchronized(mutex){
				readerCount++;
			}
			semaphore.acquire();
			System.out.println("#" + this.id + " read from library: " + this.library);
			semaphore.release();
			synchronized(mutex){
				readerCount--;
				if(readerCount == 0){
					mutex.notify();
				}
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
