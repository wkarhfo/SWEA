import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_준환이의양팔저울 {
	static int count;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine().trim());
		for (int tc = 1; tc <= TC; tc++) {
			int N = Integer.parseInt(br.readLine().trim());
			int[] arr = new int[N];
			boolean[] visit = new boolean[N];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}

			count = 0;
			dfs(0, 0, 0, arr, visit);
			System.out.println("#" + tc + " " + count);
		}
	}

	static void dfs(int depth, int left, int right, int[] arr, boolean[] visit) {
		if (right > left) {
			return;
		}
		if (depth == arr.length) {
			count++;
			return;
		}
		for (int i = 0; i < arr.length; i++) {
			if (visit[i])
				continue;
			visit[i] = true;
			dfs(depth + 1, left + arr[i], right, arr, visit);

			dfs(depth + 1, left, right + arr[i], arr, visit);
			visit[i] = false;

		}

	}
}
