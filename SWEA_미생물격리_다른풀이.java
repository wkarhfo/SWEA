import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
 
public class SWEA_미생물격리_다른풀이 {
 
//  public static Queue2382 Queue=null;
    public static Node2382[][] arr=null;
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));     
         
        String[] str=br.readLine().split(" ");
        int testCase=Integer.parseInt(str[0]);
          
        int[] answer=new int[testCase];
 
        int N=0;
        int M=0;
        int K=0;
        for(int test=0;test<testCase;test++) {
            str=br.readLine().split(" ");
            N=Integer.parseInt(str[0]);
            M=Integer.parseInt(str[1]);
            K=Integer.parseInt(str[2]);
             
            arr=new Node2382[N][N];
//            Queue=new Queue2382();
             
            
             
            for(int i=0;i<K;i++) {
                str=br.readLine().trim().split(" ");
                int x=Integer.parseInt(str[0]);
                int y=Integer.parseInt(str[1]);
                int w=Integer.parseInt(str[2]);
                int d=Integer.parseInt(str[3]);
                if(d==2) {
                    d=-1;
                }else if(d==3) {
                    d=2;
                }else if(d==4) {
                    d=-2;
                }
                arr[x][y]=new Node2382(w,d) ;           
            }
            answer[test]=calc(M);            
        }
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<testCase;i++) {
            sb.append("#"+(i+1)+" "+answer[i]+"\n");
        }
        System.out.println(sb);
 
    }
    private static int calc(int M) {
        int sum=0;
        int time=1;
         
        while(M-->0) {
             
            for(int i=0;i<arr.length;i++) {
                for(int j=0;j<arr.length;j++) {
                    if(arr[i][j]!=null&&arr[i][j].time!=time) {
                        move(i,j,time);                 
                    }
                }
            }
            time++;         
        }
         
        for(int i=0;i<arr.length;i++) {
            for(int j=0;j<arr.length;j++) {
                if(arr[i][j]!=null) {
                    sum+=arr[i][j].w;
                }
            }
        }
         
         
         
         
         
             
        return sum;
    }
    private static void move(int i, int j,int time) {
        Node2382 temp=arr[i][j];
        temp.time=time;
        temp.max=temp.w;
        arr[i][j]=null;
        int x=i;
        int y=j;
        if(temp.dist==1) {
            x--;
        }else if(temp.dist==-1) {
            x++;
        }else if(temp.dist==2) {
            y--;
        }else if(temp.dist==-2) {
            y++;
        }
 
        if(x==0 || x==arr.length-1 ||y==0||y==arr.length-1) {
            temp.w/=2;
            temp.max=temp.w;
            temp.dist*=-1;
        }
         
        if(arr[x][y]!=null) {
            if(arr[x][y].time==time) {
                if(arr[x][y].max<temp.max) {
                    arr[x][y].dist=temp.dist;
                    arr[x][y].max=temp.max;
                }
                arr[x][y].w+=temp.w;
            }else {
                move(x,y,time);
                if(arr[x][y]!=null) {                   
                    if(arr[x][y].max<temp.max) {
                        arr[x][y].dist=temp.dist;
                        arr[x][y].max=temp.max;
                    }
                    arr[x][y].w+=temp.w;                        
                }else {
                    arr[x][y]=temp;
                }           
            }       
             
        }else if(temp.w!=0) {
            arr[x][y]=temp;
        }   
         
    }
 
}
class Queue2382{
    Node2382[] arr;
    int len=1000000;
    int near,front=near=0;
    Queue2382(){
        len=1000000;
        arr=new Node2382[len];
        front=0;
        near=0;
    }
    public void add(Node2382 in) {
        if(near==len) {
            return;
        }
        arr[near++]=in;
    }
    public Node2382 get() {
        if(near==front) {
            return null;
        }
        return arr[front++];
    }
     
    public int gap() {
        return near-front;
    }
 
    public boolean isEmpty() {
        return front==near;
    }
}
 
class Node2382{
 
    int dist;
    int w;
    int time=0;
    int max;
    Node2382(int W,int d){
        w=W;
        dist=d;
        time=0;
        max=w;
    }
}