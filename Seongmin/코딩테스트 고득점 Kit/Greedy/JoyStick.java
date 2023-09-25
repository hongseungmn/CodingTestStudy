/*
 * 조이스틱
 * https://school.programmers.co.kr/learn/courses/30/lessons/42860
 * 조이스틱으로 알파벳 이름을 완성하세요. 맨 처음엔 A로만 이루어져 있습니다.
 */
//홍성민


/*
 * 'A'가 연속해서 나타나는 구간의 길이를 계산하고, 그 구간에서 좌우로 이동하여 어떤 방향으로 이동하는 것이 더 적은 횟수로 'A'를 빠져나갈 수 있는지 계산합니다. 
 * 이때, 이동 거리는 'A'를 빠져나가는데 필요한 횟수와 'A'를 지나가고 다시 돌아오는 횟수를 모두 고려하여 최소값을 선택합니다.
 */

public class JoyStick {
  public int solution(String name) {
    int answer = 0;
    int move = name.length() - 1; // 오른쪽으로 쭉 간 횟수
    
    for(int i = 0; i < name.length(); i++) {
        answer += Math.min(name.charAt(i) - 'A', 26 - (name.charAt(i) - 'A')); //상,하 알파벳 맞추기
        if (i < name.length() - 1 && name.charAt(i + 1) == 'A') {
            int endA = i + 1;
            while(endA < name.length() && name.charAt(endA) == 'A')
                endA++;
            move = Math.min(move, i * 2 + (name.length() - endA));// 오른쪽으로 갔다 다시 왼쪽으로 꺾기
            move = Math.min(move, i + (name.length() - endA) * 2);// 왼쪽으로 갔다 다시 오른쪽으로 꺾기
        }
    }
    return answer + move;
  }
}
