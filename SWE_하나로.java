import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class SWE_하나로 {
	static int N;
	static int[] x;
	static int[] y;
	static double E;
	static ArrayList<Edge> edge;
	static int[] reset;

	public static void main(String[] args) throws NumberFormatException, IOException {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			N = sc.nextInt();
			x = new int[N + 1];
			y = new int[N + 1];
			reset = new int[N + 1];
			edge = new ArrayList<>();

			for (int i = 1; i < x.length; i++) {
				x[i] = sc.nextInt();
			}
			for (int i = 1; i < y.length; i++) {
				y[i] = sc.nextInt();
			}
			E = sc.nextDouble();

			for (int i = 1; i < N; i++) {
				for (int j = i + 1; j <= N; j++) {
					double a = Math.abs(x[i] - x[j]);
					double b = Math.abs(y[i] - y[j]);
					edge.add(new Edge(i, j, (E * (a * a + b * b))));
				}
			}
			Collections.sort(edge, new Comparator<Edge>() {

				@Override
				public int compare(Edge o1, Edge o2) {
					// TODO Auto-generated method stub
					return Double.compare(o1.len, o2.len);
				}
			});

			
			// 유니온 파인드로 사이클 구하기
			for (int i = 1; i < reset.length; i++) {
				reset[i] = i;
			}

			double sum = 0;
			for (int i = 0; i < edge.size(); i++) {
				if (find(edge.get(i).a, edge.get(i).b) == 0) {
					sum += edge.get(i).len;
					unionParent(edge.get(i).a, edge.get(i).b);
				}
			}
			System.out.println("#"+tc+" "+Math.round(sum));

		}
	}

	static int getParent(int x) {
		if (reset[x] == x)
			return x;
		return reset[x] = getParent(reset[x]);
	}

	static void unionParent(int a, int b) {
		a = getParent(a);
		b = getParent(b);
		if (a < b)
			reset[b] = a;
		else
			reset[a] = b;
	}

	static int find(int a, int b) {
		a = getParent(a);
		b = getParent(b);
		if (a == b)
			return 1;
		else
			return 0;
	}

	static class Edge {
		int a;
		int b;
		double len;

		public Edge(int a, int b, double d) {
			this.a = a;
			this.b = b;
			this.len = d;
		}

	}

}
