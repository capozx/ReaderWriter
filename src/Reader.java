import java.util.LinkedList;
import java.util.concurrent.Semaphore;

public class Reader implements Runnable {

	public LinkedList<Integer> library;
	public Semaphore semaphore;
	public Integer readerCount;
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
				synchronized(this.mutex){
					this.readerCount++;
					System.out.println("Thread #" + this.id+ " readerCount: " + this.readerCount);
				}
				semaphore.acquire();
				System.out.println("#" + this.id + " is reading from library: " + this.library);
				Thread.sleep((long) (10000));
				semaphore.release();
				synchronized(this.mutex){
					this.readerCount--;
					//System.out.println("readerCount: " + this.readerCount);
					if(this.readerCount == 0){
						mutex.notifyAll();
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
