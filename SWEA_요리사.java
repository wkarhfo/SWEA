import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;


public class SWEA_요리사 {
	static int[][] arr;
	static boolean[] visit;
	private static int N;
	static int MIN;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine().trim());
		for (int tc = 1; tc <= TC; tc++) {

			N = Integer.parseInt(br.readLine().trim());
			arr = new int[N][N];

			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			MIN = Integer.MAX_VALUE;
			visit = new boolean[N];
			dfs(0, 0);
			System.out.println("#"+tc+" "+MIN);

		}
	}

	private static void dfs(int start, int depth) {
		if (depth == N / 2) {
			int min = solve();
			MIN = Math.min(MIN, min);
			return;
		}
		for (int i = start; i < arr.length; i++) {
			visit[i] = true;
			dfs(i + 1, depth + 1);
			visit[i] = false;
		}
	}

	private static int solve() {
		ArrayList<Integer> a = new ArrayList<>();
		ArrayList<Integer> b = new ArrayList<>();
		for (int i = 0; i < visit.length; i++) {
			if (visit[i]) {
				a.add(i);
			} else {
				b.add(i);
			}
		}

		int sumA = 0;
		int sumB = 0;
		for (int i = 0; i < a.size() - 1; i++) {
			for (int j = i; j < a.size(); j++) {
				sumA += arr[a.get(i)][a.get(j)] + arr[a.get(j)][a.get(i)];
				sumB += arr[b.get(i)][b.get(j)] + arr[b.get(j)][b.get(i)];
			}
		}
		int last=Math.abs(sumA-sumB);

		return last;
	}

}
