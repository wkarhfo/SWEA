import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;


/**
 * 
 * @author 정호
 *	<그래프>
 *	정점이 많으면 리스트
 *	정점이 적으면 행렬( 진입 진출 같이 봐야 할때)
 *
 *	간적쿠(간선이 적으면 쿠르스칼) 간많프(간선이 많으면 프림)
 */
public class SWEA_하나로_Kruscal {
	static int N;
	static int[] x;
	static int[] y;
	static double env;
	static int[] arr;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine().trim());
		for (int tc = 1; tc <= TC; tc++) {
			N = Integer.parseInt(br.readLine().trim());
			x = new int[N + 1];
			y = new int[N + 1];
			arr = new int[N + 1];
			StringTokenizer st = new StringTokenizer(br.readLine());
			StringTokenizer st2 = new StringTokenizer(br.readLine());

			for (int i = 1; i <= N; i++) {
				x[i] = Integer.parseInt(st.nextToken());
				y[i] = Integer.parseInt(st2.nextToken());
			}

			env = Double.parseDouble(br.readLine());
			ArrayList<Data> list = new ArrayList<>();
			for (int i = 1; i < N; i++) {
				for (int j = i + 1; j <= N; j++) {
					double tmpx = Math.abs(x[i] - x[j]);
					double tmpy = Math.abs(y[i] - y[j]);
					double tmppay = env * (Math.pow(tmpx, 2) + Math.pow(tmpy, 2));
					list.add(new Data(i, j, tmppay));
				}
			}

			
			Collections.sort(list, new Comparator<Data>() {

				@Override
				public int compare(Data o1, Data o2) {
					return Double.compare(o1.pay, o2.pay);
				}
			});
			
//			for(int i=0;i<list.size();i++) {
//				System.out.println(list.get(i).start+","+list.get(i).end+":"+list.get(i).pay);
//			}
			for (int i = 1; i <= N; i++) {
				arr[i] = i;
			}

			double result =0.0;
			int cnt = N - 1;
			for (int i = 0; i < list.size(); i++) {
				int a = findSet(list.get(i).start);
				int b = findSet(list.get(i).end);
//				System.out.println(a+" "+b);
				if (a == b)
					continue;

				if (cnt == 0) {
					break;
				}
				union(a, b);
				result += list.get(i).pay;
				cnt--;

			}
			
			System.out.println("#"+tc+" "+Math.round(result));

		}
		
	}

	private static void union(int a, int b) {
		a = findSet(a);
		b = findSet(b);

		if(a!=b)
			arr[b]=a;
	}

	private static int findSet(int num) {
		if (arr[num] == num)
			return num;
		int temp = findSet(arr[num]);
		arr[num]=temp;
		return temp;
	}

	static class Data {
		int start;
		int end;
		double pay;

		public Data(int start, int end, double pay) {
			super();
			this.start = start;
			this.end = end;
			this.pay = pay;
		}
	}

}
