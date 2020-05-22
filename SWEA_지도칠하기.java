import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_지도칠하기 {
	static int[][] map;
	static int N;
	static int[] color;
	static int[] fill;
	static int min;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// 데이터 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine().trim());
		for (int tc = 1; tc <= TC; tc++) {
			N = Integer.parseInt(br.readLine().trim());
			map = new int[N][N];
			color = new int[N];
			fill = new int[N];
			min = Integer.MAX_VALUE;
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				color[i] = Integer.parseInt(st.nextToken());
			}
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			// 순열 생성후,
			dfs(0);

			// 결과를 출력
			System.out.println("#" + tc + " " + min);
		}
	}

	static void dfs(int depth) {
		if (depth == N) {
			// 인접된 국가가 다른 color로 칠할 수 있는 순열인 경우
			if (check()) {
				// 인접된 국가의 색이 다르게 작성딘 순열이 생성된 경우 기존의 color와 생성된 순열이 다른지를 카운트
				int count = 0;
				for (int i = 0; i < N; i++) {
					if (color[i] != fill[i]) {
						count++;
					}
				}
				min = Math.min(count, min);
			}
			return;
		}
		// 중복 순열
		for (int i = 1; i <= 4; i++) {
			fill[depth] = i;
			dfs(depth + 1);
		}
	}

	static boolean check() {

		for (int i = 0; i < N; i++) { // cur
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 1 && fill[i] == fill[j])
					return false;
			}
		}
		return true;
	}

}
