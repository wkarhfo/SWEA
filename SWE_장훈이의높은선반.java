import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWE_장훈이의높은선반 {
	static int result;
	static int MAX;
	static int MIN;
	static int N;
	static int[] arr;
	static boolean[] visit;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int TC = Integer.parseInt(st.nextToken());
		for (int tc = 1; tc <= TC; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			MAX = Integer.parseInt(st.nextToken());

			arr = new int[N];
			visit = new boolean[N];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			MIN=Integer.MAX_VALUE;
			powerSet(0);
			System.out.println("#" + tc + " " + MIN);
		}
	}

	private static void powerSet(int depth) {
		if (depth == N) {
			int sum=0;
			for (int i = 0; i < visit.length; i++) {
				if (visit[i]) {
					sum+=arr[i];
				}
			}
			if(sum<MAX) {
				return;
			}
			else {
				int temp=Math.abs(sum-MAX);
				MIN=Math.min(temp,MIN);
			}
			return;
		}
		visit[depth] = true;
		powerSet(depth + 1);

		visit[depth] = false;
		powerSet(depth + 1);
	}

}
