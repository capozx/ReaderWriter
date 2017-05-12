import java.util.LinkedList;
import java.util.concurrent.Semaphore;

public class Reader implements Runnable {

	public LinkedList<Integer> library;
	public Semaphore semaphore;
	public int readerCount;
	public Object mutex;
	private int id;
	
	Reader(LinkedList<Integer> l , int i, Semaphore s, int rC ,Object m){
		this.library = l;
		this.id = i;
		this.readerCount = rC;
		this.mutex = m;
		this.semaphore = s;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true){
			try {
				synchronized(mutex){
					readerCount++;
				}
				semaphore.acquire();
				System.out.println("#" + this.id + " read from library: " + this.library);
				Thread.sleep((long) (Math.random() * 10000));
				semaphore.release();
				synchronized(mutex){
					readerCount--;
					//System.out.println("readerCount: " + this.readerCount);
					if(readerCount == 0){
						mutex.notify();
						//System.out.println("NotifyAll");
					}
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
