## DFS와 BFS - 자바 풀이 : [1260번 바로가기](https://www.acmicpc.net/problem/1260)

**문제는 백준 사이트와 똑같으니, 해설부터 보셔도 됩니다.**

#### 문제
그래프를 DFS로 탐색한 결과와 BFS로 탐색한 결과를 출력하는 프로그램을 작성하시오. 단, 방문할 수 있는 정점이 여러 개인 경우에는 정점 번호가 작은 것을 먼저 방문하고, 더 이상 방문할 수 있는 점이 없는 경우 종료한다. 정점 번호는 1번부터 N번까지이다.

#### 입력
첫째 줄에 정점의 개수 N(1 ≤ N ≤ 1,000), 간선의 개수 M(1 ≤ M ≤ 10,000), 탐색을 시작할 정점의 번호 V가 주어진다. 다음 M개의 줄에는 간선이 연결하는 두 정점의 번호가 주어진다. 어떤 두 정점 사이에 여러 개의 간선이 있을 수 있다. 입력으로 주어지는 간선은 양방향이다.

#### 출력
첫째 줄에 DFS를 수행한 결과를, 그 다음 줄에는 BFS를 수행한 결과를 출력한다. V부터 방문된 점을 순서대로 출력하면 된다.

#### 예제1
> **입력**
4 5 1
1 2
1 3
1 4
2 4
3 4

> **출력**
1 2 4 3
1 2 3 4

## 해설

**접근방식**
DFS와 BFS의 응용이 따로 필요하지 않은 문제입니다.

1. DFS 시 Stack을 사용하였습니다.

2. BFS 시 Queue를 사용하였습니다.

주의 사항으로는 무방향 그래프인 것을 알아야합니다. (저는 그래프를 2차원 배열로 표현하였습니다.)

## 답안
```java
import java.io.*;
import java.util.StringTokenizer;
import java.util.Stack;
import java.util.Queue;
import java.util.LinkedList;


public class Main {
	static int n, m, v;
	static boolean[][] graph;
	static boolean[] visited;
	
	private static void DFS() {
		Stack<Integer> stack = new Stack<>();
		StringBuilder sb = new StringBuilder();
		stack.push(v);
		while(!stack.isEmpty()) {
			int i = stack.pop();
			if(!visited[i]) {
				sb.append(i).append(" ");
				visited[i] = true;
				for (int j = n; j > 0; j--) {
					if(!visited[j] && (graph[i][j] || graph[j][i])) stack.push(j);
				}
			}
		}
		System.out.println(sb);
	}
	
	private static void BFS() {
		Queue<Integer> queue = new LinkedList<>();
		StringBuilder sb = new StringBuilder();
		queue.add(v);
		while(!queue.isEmpty()) {
			int i = queue.remove();
			if(!visited[i]) {
				sb.append(i).append(" ");
				visited[i] = true;
				for (int j = 1; j <= n; j++) {
					if(!visited[j] && (graph[i][j] || graph[j][i])) queue.add(j);
				}
			}
		}
		System.out.println(sb);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		v = Integer.parseInt(st.nextToken());
		graph = new boolean[n + 1][n + 1];
		visited = new boolean[n + 1];
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			graph[a][b] = true;
			graph[b][a] = true;
		}
		
		DFS();
		
		visited = new boolean[n + 1];
		BFS();
	}

}
```