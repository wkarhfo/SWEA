/**
 * 	Comparator 인터페이스는 compareTo메소드를 갖는다
 * 	o1과 o2가 같으면 0을 리턴
 * 	o1이 o2보다 앞에 오게 하려면 음수리턴
 * 	o1이 o2보다 뒤에 오게 하려면 양수리턴
 */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

public class SWE_염라대왕의이름정렬 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine().trim());
		for (int tc = 1; tc <= TC; tc++) {
			int N = Integer.parseInt(br.readLine().trim());
			Set<String> set=new TreeSet<>(new Comparator<String>() {

				@Override
				public int compare(String o1, String o2) {
					int lang=o1.length()-o2.length();
					if(lang==0) {
						return o1.compareTo(o2);
					}
					else
						return lang;
				}
			});
			
			
			for (int i = 0; i < N; i++) {
				set.add(br.readLine().trim());
			}
			
			System.out.println("#" + tc);
			for(String result:set) {
				System.out.println(result);
			}
			
		
		}
	}
}
