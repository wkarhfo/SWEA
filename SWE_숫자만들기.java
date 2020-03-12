import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWE_숫자만들기 {
	static int[] arr;
	static int MAX;
	static int MIN;
	static int len;
	static int SUM;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		for (int tc = 1; tc <= T; tc++) {

			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());

			st = new StringTokenizer(br.readLine());
			int plus = Integer.parseInt(st.nextToken());
			int minus = Integer.parseInt(st.nextToken());
			int mul = Integer.parseInt(st.nextToken());
			int div = Integer.parseInt(st.nextToken());
			len = plus + minus + mul + div;
			arr = new int[N];

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());

			}
			MAX = Integer.MIN_VALUE;
			MIN=Integer.MAX_VALUE;
			SUM = arr[0];
			dfs(plus, minus, mul, div, 0, SUM);

			System.out.println("#" + tc + " " + Math.abs(MAX-MIN));
		}
	}

	private static void dfs(int plus, int minus, int mul, int div, int cnt, int sum) {
		if(cnt==len) {
			MAX=Math.max(sum, MAX);
			MIN=Math.min(sum, MIN);
			return;
		}
		
		if(plus<0||minus<0||mul<0||div<0)
			return;
		if(plus>0)
			dfs(plus-1,minus,mul,div,cnt+1,sum+arr[cnt+1]);
		if(minus>0)
			dfs(plus,minus-1,mul,div,cnt+1,sum-arr[cnt+1]);
		if(mul>0)
			dfs(plus,minus,mul-1,div,cnt+1,sum*arr[cnt+1]);
		if(div>0)
			dfs(plus,minus,mul,div-1,cnt+1,sum/arr[cnt+1]);
				
		
	}
}
