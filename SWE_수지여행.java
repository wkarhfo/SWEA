import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

//v&1<<arr[ah][ay]!=0 --->방문한 적이 있다!.
//대문자 갯수:26개 

public class SWE_수지여행 {
	static char[][] arr;
	static int[] dh = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static ArrayList<Character> list;
	static int count;
	static boolean[] visit;
	static int MAX;
	static int MAX2;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			int hang = Integer.parseInt(st.nextToken());
			int yeul = Integer.parseInt(st.nextToken());
			arr = new char[hang][yeul];
			for (int i = 0; i < hang; i++) {
				st = new StringTokenizer(br.readLine());
				arr[i] = st.nextToken().toCharArray();
			}

			list = new ArrayList<>();
			list.add(arr[0][0]);
			count = 0;
			MAX = Integer.MIN_VALUE;
			dfs(0, 0);

			visit = new boolean[26];
			MAX2 = Integer.MIN_VALUE;
			visit[arr[0][0] - 'A'] = true;
			dfs2(0, 0, 1);
			System.out.println("#" + tc + " " + (MAX + 1));
			System.out.println("#" + tc + " " + MAX2);
		}
	}

	private static void dfs2(int h, int y, int cnt) {
		MAX2 = Math.max(cnt, MAX2);
		if(cnt==26)
			return;
		for (int k = 0; k < 4; k++) {
			int ah = h + dh[k];
			int ay = y + dy[k];
			if (ah < 0 || ah >= arr.length || ay < 0 || ay >= arr[0].length || visit[arr[ah][ay] - 'A'] == true)
				continue;

			visit[arr[ah][ay] - 'A'] = true;
			dfs2(ah, ay, cnt + 1);
			visit[arr[ah][ay] - 'A'] = false;

		}
	}

	private static void dfs(int h, int y) {
		if (MAX < count) {
			MAX = count;
		}
		for (int k = 0; k < 4; k++) {
			int ah = h + dh[k];
			int ay = y + dy[k];
			if (ah < 0 || ah >= arr.length || ay < 0 || ay >= arr[0].length)
				continue;
			if (list.contains(arr[ah][ay]))
				continue;

			list.add(arr[ah][ay]);
			count++;
			dfs(ah, ay);
			count--;
			list.remove(list.size() - 1);
		}

	}

}
