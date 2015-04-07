package tianwp.mathprob;

/**
 * there is a game, the host choose a number N between 0 and 100 randomly, then
 * three person choose there own number x, y and z one by one. the one who's
 * number is most near N will win the game. so the problem is which position
 * will you want to be, and your choosing strategy?
 * 
 * @author tianwp
 *
 */
public class FindOptPoint {

	public static int optA(int num) {
		int tmpx = 0;
		double tmpvx = 0;

		for (int x = 0; x < num; x++) {
			int y = optB(x, num);
			int z = optC(x, y, num);
			double vx = valueY(y, x, z, num);
			if (vx > tmpvx) {
				tmpx = x;
				tmpvx = vx;
			}
			printValue(x, y, z, num);
		}
		int fy = optB(tmpx, num);
		int fz = optC(tmpx, fy, num);

		printValue(tmpx, fy, fz, num);
		return tmpx;
	}

	private static void printValue(int x, int y, int z, int num) {
		System.out.println(x + " " + y + " " + z + " " + valueY(y, x, z, num)
				+ " " + valueY(x, y, z, num) + " " + valueY(y, z, x, num));
	}

	private static int optB(int x, int num) {
		int tmpy = 0;
		double tmpvy = 0;
		for (int y = 0; y < num; y++) {
			if (x != y) {
				int z = optC(x, y, num);
				double vy = valueY(x, y, z, num);
				if (vy > tmpvy) {
					tmpy = y;
					tmpvy = vy;
				}
			}
		}
		return tmpy;
	}

	private static int optC(int x, int y, int num) {
		if (x > y) {
			int t = x;
			x = y;
			y = t;
		}
		double a = x;
		double b = y - x <= 1 ? -1 : (y - x) / 2.0;
		double c = num - y - 1;
		if (a > b) {
			if (a > c) {
				return (int) a;
			} else {
				return y + 1;
			}
		} else {
			if (b > c) {
				return (x + y) / 2;
			} else {
				return y + 1;
			}
		}
	}

	private static double valueY(int x, int y, int z, int num) {
		if (y < x) {
			if (y < z) {
				if (x < z) {
					return (y + x + 1) / 2.0;
				} else {
					return (y + z + 1) / 2.0;
				}
			} else {
				return (x - z) / 2.0;
			}
		} else {
			if (y > z) {
				if (x < z) {
					return num - y + (y - z - 1) / 2.0;
				} else {
					return num - y + (y - x - 1) / 2.0;
				}
			} else {
				return (z - x) / 2.0;
			}
		}
	}

	public static void main(String[] args) {
		optA(1000);
	}
}
