import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SWEA_키순서 {
	static int N, M;
	static ArrayList<Integer>[] list;
	static ArrayList<Integer>[] count;

	static boolean[] visit;
	static int lastCnt;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine().trim());
		for (int tc = 1; tc <= TC; tc++) {
			
			N = Integer.parseInt(br.readLine().trim());
			M = Integer.parseInt(br.readLine().trim());
			list = new ArrayList[N + 1];
			count = new ArrayList[N + 1];
			for (int i = 1; i <= N; i++) {
				list[i] = new ArrayList<>();
				count[i] = new ArrayList<>();
			}

			for (int i = 0; i < M; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				list[a].add(b);
			}

			lastCnt = 0;

			for (int i = 1; i <= N; i++) {
				visit = new boolean[N + 1];
				dfs(i, i);
			}

			for (int i = 1; i <= N; i++) {
				int cnt = 0;
				boolean[] num = new boolean[N + 1];
				for (int j : count[i]) {
					num[j] = true;
				}
				for (int k = 0; k < list[i].size(); k++) {
					num[list[i].get(k)] = true;
				}
				for (int k = 1; k <= N; k++) {
					if (num[k])
						cnt++;
				}
				if (cnt == N) {
					lastCnt++;
				}

			}

			System.out.println("#" +tc + " " + lastCnt);
		}
	}

	static void dfs(int start, int i) {
		count[i].add(start);
		count[start].add(i);

		for (int k = 0; k < list[i].size(); k++) {
			if (visit[list[i].get(k)])
				continue;

			visit[list[i].get(k)] = true;
			dfs(start, list[i].get(k));
		}
	}
}
