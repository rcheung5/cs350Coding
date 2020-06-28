
public class Request {
	public int reqNum;
	public double arrTime;
	public double startTime;
	public double finTime;
	
	public Request(int reqNUM ) {
		reqNum = reqNUM;
	}
	
	public void setArrTime(double AT) {
		arrTime = AT;
	}
	public double getArrTime() {
		return arrTime;
	}
	
	public void setStartTime(double ST) {
		startTime = ST;
	}
	public double getStartTime() {
		return startTime;
	}
	
	public void setFinTime(double FT) {
		finTime = FT;
	}
	public double getFinTime() {
		return finTime;
	}
	
	public int getReqNum() {
		return reqNum;
	}
	
	public double getReqTime() {
		return finTime - arrTime;
	}
	
	public double getBusyTime() {
		return finTime - startTime;
	}
}
