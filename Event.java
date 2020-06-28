
import java.util.PriorityQueue;

public class Event implements Comparable<Event>{
	public double time;
	public int BMD; //0 means birth, 1 means monitor, 2 means death
	
	public Event(double TIME, int eventType) {
		time = TIME;
		BMD = eventType;
	}
	
	double getTime() {
		return time;
	}
	
	public static double getNextBirthOrMon() {
		return Exp.getExp(Simulator.lambda);
	}
	
	public static double getNextDeath() {
		return Exp.getExp(1/Simulator.TS);
	}
	
	public void function(double time) {
		if (BMD == 0) {
			Request request = new Request(Simulator.reqNum + 1);
			Simulator.reqNum++;
			request.setArrTime(time);
			Simulator.queue.add(request);
			System.out.println("R" + Integer.toString(request.getReqNum()) + " ARR: " + Double.toString(request.getArrTime()));
			if (Simulator.queue.size() == 1) {
				request.setStartTime(time);
				Event event = new Event(time + getNextDeath(), 2);
				Simulator.Requests.add(event);
				System.out.println("R" + Integer.toString(request.getReqNum()) + " START: " + Double.toString(time));
			}
			//Simulator.queue.add(request);
			Event event2 = new Event(time + getNextBirthOrMon(), 0);
			Simulator.Requests.add(event2);
		}
		else if (BMD == 1) {
			Simulator.numMonEvents += 1;
			Simulator.totQLen += Simulator.queue.size();
			Event event = new Event(time + getNextBirthOrMon(), 1);
			Simulator.Requests.add(event);
		}
		else if (BMD == 2) {
			Request request = Simulator.queue.remove();
			request.setFinTime(time);
			Simulator.busyTime += request.getBusyTime();
			Simulator.totReqTime += request.getReqTime();
			Simulator.numCompReq ++;
			System.out.println("R" + Integer.toString(request.getReqNum()) + " DONE: " + Double.toString(request.getFinTime()));
			if (Simulator.queue.size() > 0) {
				Request next = Simulator.queue.peek();
				next.setStartTime(time);
				System.out.println("R" + Integer.toString(next.getReqNum()) + " START: " + Double.toString(next.getStartTime()));
				Event event = new Event(time + getNextDeath(), 2);
				Simulator.Requests.add(event);
			}
		}
		
	}
	
	public int compareTo(Event event) {
		double sub = time - event.getTime();
		if (sub < 0) {
			return -1;
		}
		else if (sub > 0) {
			return 1;
		}
		else {
			return 0;
		}
	}
	
}
