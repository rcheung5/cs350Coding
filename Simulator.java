
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.*;

public class Simulator {
	public static double lambda;
	public static double TS;
	public static Queue<Request> queue = new LinkedList<Request>();
	public static double busyTime = 0;
	public static double totReqTime = 0;
	public static double totQLen = 0;
	public static double numMonEvents = 0;
	public static double numCompReq = 0;
	public static int reqNum = -1;
	public static PriorityQueue<Event> Requests = new PriorityQueue<Event>();
	
	public static void simulate(double time) {
		Requests.add(new Event(0, 0));
		Requests.add(new Event(0, 1));
		
		double currTime = 0;
		double endTime = time;
		while (currTime < endTime) {
			Event event = Requests.remove();
			currTime = event.getTime();
			event.function(currTime);
		}
		
		System.out.println("UTIL: " + Double.toString(busyTime / time));
		System.out.println("QLEN: " + Double.toString(totQLen / numMonEvents));
		System.out.println("TRESP: " + Double.toString(totReqTime / numCompReq));
	}
	
	public static void main(String[] args) {
		double time = Double.parseDouble(args[0]);
	    lambda = Double.parseDouble(args[1]);
		TS = Double.parseDouble(args[2]);
		simulate(time);
	}

}
