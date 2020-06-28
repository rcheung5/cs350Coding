
public class Exp {
	public static double getExp(double lambda) {
		double rand = Math.random();
		return (-Math.log(1 - rand)) / lambda;
	}

	public static void main(String[] args) {
		double lambda = Double.parseDouble(args[0]);
		int N = Integer.parseInt(args[1]);
		for (int x = 0;x < N; x ++) {
			System.out.println(getExp(lambda));
		}
	}

}