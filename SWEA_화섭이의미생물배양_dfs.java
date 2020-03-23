import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_화섭이의미생물배양_dfs {
	static int s, t, a, b;
	static int min;

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
				System.out.println(-1);
			} else if (s == t) {
				System.out.println(0);
			} else {
				min = Integer.MAX_VALUE;
				dfs(s, 0);
				if (min == Integer.MAX_VALUE)
					System.out.println(-1);
				else
					System.out.println(min);
			}

		}
	}

	private static void dfs(int idx, int cnt) {
		
		if (idx == t) {
			min = Math.min(cnt, min);
			return;
		}
		if (idx > t) {
			return;
		}
		
		dfs(idx + a, cnt + 1);
		dfs(idx * b, cnt + 1);
	}
}
