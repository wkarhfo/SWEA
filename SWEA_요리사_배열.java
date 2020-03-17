import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SWEA_요리사_배열 {
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
			System.out.println("#" + tc + " " + MIN);

		}
	}

	private static void dfs(int start, int depth) {
		if (start >= N) {
			return;
		}

		if (depth == N / 2) {
			int a=0;
			int b=0;
			for(int i=0;i<N-1;i++) {
				for(int j=i;j<N;j++) {
					if(visit[i]!=visit[j])
						continue;
					if(visit[i]) {
						a+=arr[i][j]+arr[j][i];
					}else {
						b+=arr[i][j]+arr[j][i];
					}
				}
			}
			int min=Math.abs(a-b);
			MIN = Math.min(MIN, min);
			return;
		}
		for (int i = start; i < arr.length; i++) {
			visit[i] = true;
			dfs(i + 1, depth + 1);
			visit[i] = false;
		}
	}

}
