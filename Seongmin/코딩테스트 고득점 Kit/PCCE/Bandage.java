/*
 * 붕대감기
 * https://school.programmers.co.kr/learn/courses/30/lessons/250137
 */

public class Bandage {
    int nowHealth; // 최대체력
    int[] bandage; // 붕대기술속성
    int[][] attacks; // 공격 정보

    public int solution(int[] bandage, int health, int[][] attacks) {

        this.nowHealth = health;
        this.bandage = bandage;
        this.attacks = attacks;

        for (int attackIndex = 0; attackIndex < attacks.length - 1; attackIndex++) { // 마지막 공격 제외 (인덱스 에러)
            // 공격 정보로부터 피해 입음

            System.out.println(String.format("[%d 번째 공격] 현재 체력 : %d", attackIndex + 1, nowHealth));

            nowHealth -= attacks[attackIndex][1];

            // 체력이 0 이하면 -1 return
            if (nowHealth <= 0)
                return -1;

            // 다음 공격까지 붕대기술을 시전
            // 붕대기술 시전 가능 시간 (다음 공격시각 - 현재 공격 시각 -1s)
            int emptyTime = attacks[attackIndex + 1][0] - attacks[attackIndex][0] - 1;

            // 붕대기술 초당 회복 (다음 공격까지 빈 시간 * 초당 회복)
            nowHealth += emptyTime * bandage[1];

            // 추가연속 붕대감기 가능 횟수 확인
            int additionBandage = (int) emptyTime / bandage[0];
            // 추가 연속 회복
            nowHealth += additionBandage * bandage[2];

            // 최대체력 30 초과 불가능
            if (this.nowHealth > health) {
                nowHealth = health;
            }
        }

        // 마지막 공격
        nowHealth -= attacks[attacks.length - 1][1];
        if (nowHealth <= 0)
            return -1;

        return nowHealth;
    }

    public static void main(String[] args) {

        // int[] bandage = {5, 1, 5};
        // int health = 30;
        // int[][] attacks = {{2, 10}, {9, 15}, {10, 5}, {11, 5}};

        // int[] bandage = {3, 2, 7};
        // int health = 20;
        // int[][] attacks = {{1, 15}, {5, 16}, {8, 6}};

        // int[] bandage = {4, 2, 7};
        // int health = 20;
        // int[][] attacks = {{1, 15}, {5, 16}, {8, 6}};

        // int[] bandage = {1, 1, 1};
        // int health = 5;
        // int[][] attacks = {{1, 2}, {3, 2}};

        int[] bandage = { 2, 1, 5 };
        int health = 30;
        int[][] attacks = { { 1, 10 }, { 5, 15 }, { 10, 10 } };

        Bandage solution = new Bandage();
        int answer = solution.solution(bandage, health, attacks);
        System.out.println("정답은 : " + answer);
    }
}