import java.util.LinkedList;
import java.util.concurrent.Semaphore;

public class LibrarySimulator {
	public volatile Integer readerCount;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LibrarySimulator ls = new LibrarySimulator();
		int readerNumber = 5;
		ls.readerCount = new Integer(0);
		LinkedList<Integer> library = new LinkedList<Integer>();
		Object mutex = new Object();
		Semaphore s = new Semaphore(readerNumber,true);
		Writer w = new Writer(library,s,ls.readerCount,mutex);
		for(int i = 0; i < readerNumber; i++){
			Reader r = new Reader(library,i,s,ls.readerCount,mutex);
			Thread tR = new Thread(r);
			tR.start();
		}
		Thread tW = new Thread(w);
		tW.start();
	}

}
