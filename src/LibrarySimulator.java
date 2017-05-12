import java.util.LinkedList;
import java.util.concurrent.Semaphore;

public class LibrarySimulator {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int readerNumber = 5;
		int readerCount = 0;
		LinkedList<Integer> library = new LinkedList<Integer>();
		Object mutex = new Object();
		Semaphore s = new Semaphore(readerNumber);
		Writer w = new Writer(library,s,readerCount,mutex);
		for(int i = 0; i < readerNumber; i++){
			Reader r = new Reader(library,i,s,readerCount,mutex);
			Thread tR = new Thread(r);
			tR.start();
		}
		Thread tW = new Thread(w);
		tW.start();
	}

}
