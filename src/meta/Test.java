package meta;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.TreeSet;

public class Test {
	private TreeSet<String> fileInput() {
		BufferedReader br = null;
		TreeSet<String> treeSet = new TreeSet<>();
		try {

			br = new BufferedReader(new FileReader("src/meta/test.log"));
			boolean timeWatchFlag = false;
			String timeWatchLine = null;
			while (true) {
				String line = br.readLine();

				if (line == null)
					break;
				if (line.contains("],[MessageExchange_ID=ID:")) {
					// 라인에 불필요한 게 들어가 있어서 배제처리

					continue;
				}
				if (line.contains("## Wating File logging. ##")) {
					// 라인에 불필요한 게 들어가 있어서 배제처리

					continue;
				}
				

				if (line.contains("StopWatch")) {
					// 타임 워치 시작
					timeWatchLine += line;
					timeWatchFlag = true;
					continue;// 타임워치가 시작되었습니다
				}

				if (timeWatchFlag == false) {
					// 타임워치가 아닐 경우에만

					treeSet.add(line);
					continue;
				}
				if (line.contains("CmmMod Server")) { // 시간끝
					timeWatchFlag = false;

					timeWatchLine += line;
					treeSet.add(timeWatchLine);
					timeWatchLine = "";
					continue;
				}
				else if (timeWatchFlag == true) {
					if(line !=null) {
					timeWatchLine += line;
				}

				}}
		} catch (IOException e) {
			System.out.println("예외 발생");
			e.getMessage();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				// 트리셋 반환
			}
		}
		return treeSet;
	}

	private void fileOutput(TreeSet<String> treeSet) throws IOException {
		PrintWriter pw = null;
		Iterator<String> iter = treeSet.iterator();
		try {
			pw = new PrintWriter("src/meta/test2.log");
			while (iter.hasNext()) {// 값이 있으면 true 없으면 false
				pw.println(iter.next());
			}
		} catch (IOException e) {
			System.out.println("예외 발생");
			e.getMessage();
		} finally {
			if (pw != null) {
				try {
					pw.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				// 트리셋 반환
			}
		}

	}

	public void logSplit(TreeSet<String> treeSet) {
		String line = null;

		TreeSet<String> treeSetSplit = new TreeSet<>();
		TreeSet<String> thread = new TreeSet<>();

		// 스레드 이름만 모은 트리셋 생성
		Iterator<String> iter = treeSet.iterator();
		while (iter.hasNext()) {
			line = iter.next();
			String[] strArr = null;
			strArr = line.split("  ");
			thread.add(strArr[5]);
		}
		;
		
		System.out.println("thread" + thread);
		Iterator<String> threadIter = thread.iterator(); // 스레드 반복하는 반복자
		int threadSize = thread.size();// 스레드 사이즈 측정
		String[][] threadList = new String[threadSize][7];

		iter = treeSet.iterator(); // 초기화
		while (iter.hasNext()) {
			// 먼저 기존의 스레드와 일치하는 중간값이 있을 경우
			line = iter.next();
			String[] strArr = null;
			strArr = line.split(" ");
			thread.add(strArr[1]); // 스레드 이름인데
			// 만약에 strArr[1]과 일치하는 스레드가 트리맵에 있어
			// 그러면 그 트리맵 번호를 불러와
			int bc = thread.headSet(strArr[1]).size();
			System.out.println("해당 스레드와 일치하는 스레드" + bc);
			// 그럼 그 사이즈에 맞춰서 스레드 리스트에 저걸 넣는다
			System.out.println("strArr2" + strArr[2]);
			if (strArr[2].contains("##galileo_bean end")) {
				threadList[bc][5] = strArr[0];
				threadList[bc][6] = strArr[2];
			} else if (strArr[2].contains(".*##galileo_bean start.*")) {
				// 시작 시간 체크
				threadList[bc][0] = strArr[1]; // 스레드 이름 삽입
				threadList[bc][1] = strArr[0]; // 시작 시간 삽입
				threadList[bc][2] = strArr[2]; // 시작 삽입
			} else {

				threadList[bc][3] = strArr[0]; // 중간 시간 삽입
				threadList[bc][4] = strArr[2]; // 끝 시간 삽입
			}
			
			System.out.println("threadSizdfsfdse" + threadSize);
			
		}


		System.out.println(treeSetSplit);

	}

	public static void main(String[] args) throws IOException {

		Test code = new Test();
		// 파일을 입력받을 때 더미로그를 지우고 유효시간을 한 줄 처리한 후 트리셋으로 전환
		TreeSet<String> treeSet = code.fileInput();
		// 트리셋을 분석해 스레드별로 분리
		//code.logSplit(treeSet);
		// 트리셋을 파일로 전환
		code.fileOutput(treeSet);

	}///////// main
}
