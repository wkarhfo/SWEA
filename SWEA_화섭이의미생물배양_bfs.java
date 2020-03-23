import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_화섭이의미생물배양_bfs {
	static int s, t, a, b;
	static boolean[] visit;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			s = Integer.parseInt(st.nextToken()); // 시작
			t = Integer.parseInt(st.nextToken()); // 도착
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			if (s > t) {
				visit = new boolean[s + 1];
			} else
				visit = new boolean[t + 1];
			int result = -1;

			Queue<Data> q = new LinkedList<>();
			q.add(new Data(s, 0));

			while (!q.isEmpty()) {
				Data temp = q.poll();
				visit[temp.x] = true;
				if (temp.x == t) {
					result = temp.cnt;
					break;
				}
				for (int k = 0; k < 2; k++) {
					int ax = 0;
					if (k == 0) {
						ax = temp.x + a;
					} else {
						ax = temp.x * b;
					}
					if (ax <= 0 || ax >= visit.length || visit[ax] == true)
						continue;

					q.add(new Data(ax, temp.cnt + 1));

				}

			}

			System.out.println("#" + tc + " " + result);
		}
	}

	static class Data {
		int x;
		int cnt;

		public Data(int x, int cnt) {
			super();
			this.x = x;
			this.cnt = cnt;
		}

	}
}
