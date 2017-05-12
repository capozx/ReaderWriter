import java.util.LinkedList;
import java.util.concurrent.Semaphore;

public class Writer implements Runnable {

	public LinkedList<Integer> library;
	public Semaphore semaphore;
	
	Writer(LinkedList<Integer> l , int i, Semaphore s){
		this.library = l;
		this.semaphore = s;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}
