## 경로 찾기 - 자바 풀이 : [11403번 바로가기](https://www.acmicpc.net/problem/11403)

**문제는 백준 사이트와 똑같으니, 해설부터 보셔도 됩니다.**

#### 문제
가중치 없는 방향 그래프 G가 주어졌을 때, 모든 정점 (i, j)에 대해서, i에서 j로 가는 길이가 양수인 경로가 있는지 없는지 구하는 프로그램을 작성하시오.

#### 입력
첫째 줄에 정점의 개수 N (1 ≤ N ≤ 100)이 주어진다. 둘째 줄부터 N개 줄에는 그래프의 인접 행렬이 주어진다. i번째 줄의 j번째 숫자가 1인 경우에는 i에서 j로 가는 간선이 존재한다는 뜻이고, 0인 경우는 없다는 뜻이다. i번째 줄의 i번째 숫자는 항상 0이다.

#### 출력
총 N개의 줄에 걸쳐서 문제의 정답을 인접행렬 형식으로 출력한다. 정점 i에서 j로 가는 길이가 양수인 경로가 있으면 i번째 줄의 j번째 숫자를 1로, 없으면 0으로 출력해야 한다.

#### 예제1
> **입력**
3
0 1 0
0 0 1
1 0 0

> **출력**
1 1 1
1 1 1
1 1 1

## 해설

**접근방식**
플로이드-워셜(Floyd-Warshall) 알고리즘을 사용하였습니다.

알고리즘 특징인 모든 정점에서 모든 정점으로의 최단 거리를 구하는 알고리즘입니다.
또한, 거쳐가는 정점을 기준으로 한다는 점을 알아야합니다.
> (i, j) = (i, k) + (k, j)

> graph\[i]\[k] == 1 && graph\[k]\[j] == 1
  => graph\[i]\[j] == 1

## 답안
```java
import java.io.*;
import java.util.StringTokenizer;

public class test {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        int[][] graph = new int[n][n];
        
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if ((graph[i][k] == 1) && (graph[k][j] == 1)) {
                    	graph[i][j] = 1;
                    }
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sb.append(graph[i][j]).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);

    }
}
```
**밑에는 DFS를 이용하여 i부터 j까지 모든 경로를 확인하여 해결한 제가 처음 풀었던 소스코드입니다.**
```java
import java.io.*;

public class Main{
	static int n;
	static boolean[] visit;
	static int[][] graph;
	
	private static void printMatrix() {
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if(graph[i][j] == 1) {
					sb.append(1).append(" ");
					continue;
				}
				visit = new boolean[n];
				if(checkPath(i, j)) sb.append(1).append(" ");
				else sb.append(0).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
	
	private static boolean checkPath(int start, int end) {
		if(start == end && visit[end]) return true;
		
		for (int i = 0; i < n; i++) {
			if(graph[start][i] == 1 && !visit[i]) {
				visit[i] = true;
				if(checkPath(i, end)) return true;
			}
		}
		
		return false;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		graph = new int[n][n];
		
		for (int i = 0; i < n; i++) {
			String[] s = br.readLine().split(" ");
			for (int j = 0; j < s.length; j++) {
				graph[i][j] = Integer.parseInt(s[j]);
			}
		}
		
		printMatrix();
	}
	
}
```