import java.util.*;

public class DeliveryNCollection {

  public long solution(int cap, int n, int[] deliveries, int[] pickups) {
    long answer = 0;
    // 배달 갈 때 cap, 다녀 올 때 cap만큼 가져옴
    //case 1-1 : 5까지 다녀온 후 result = 5 * 2
    // deliveries       pickups
    // [1, 0, 2, 0, 0]  [0, 3, 0, 0, 0]
    //case 1-2 : 3까지 다녀온 후 result = 3 * 2 + 10
    // [0, 0, 0, 0, 0]  [0, 0, 0, 0, 0]

    //case 2-1 : 7까지 다녀온 후 result = 7 * 2
    //deliveries              pickups
    //[1, 0, 2, 0, 1, 0, 0]   [0, 2, 0, 1, 0, 0, 0]
    //case 2-2 : 5까지 다녀온 후 result = 5 * 2 + 14
    //[1, 0, 1, 0, 0, 0, 0]   [0, 1, 0, 0, 0, 0, 0]
    //case 2-3 : 3까지 다녀온 후 result = 3 * 2 + 24
    //[0, 0, 0, 0, 0, 0, 0]   [0, 0, 0, 0, 0, 0, 0]

    int attemp = 0;
    int deliveryPoint = n-1;
    int pickupPoint = n-1;

    while(deliveryPoint >= 0 || pickupPoint >= 0) {
      int deliveryRemain = cap;
      while(deliveryPoint >= 0 && deliveryRemain > 0) {
        if (deliveries[deliveryPoint] - deliveryRemain > 0) { //해당 지점에 한번 더 와야 하는 경우
          //System.out.println("해당 지점에 한번 더 와야 하는 경우 : deliveries :" + Arrays.toString(deliveries) +", deliveryRemain : " + deliveryRemain);
          deliveries[deliveryPoint] -= deliveryRemain;
          deliveryRemain = cap; 
        }
        else { //해당 지점에 배달을 완료한 경우 (한번 더 안와도 됨)
          deliveryRemain -= deliveries[deliveryPoint];
          deliveries[deliveryPoint] = 0;
          deliveryPoint--;
          //System.out.println("해당 지점에 배달을 완료한 경우 (한번 더 안와도 됨) : deliveries :" + Arrays.toString(deliveries) +", deliveryRemain : " + deliveryRemain);
          break;
        }
      }
      System.out.println(attemp+" 번 수행 한 후 deliveries :" + Arrays.toString(deliveries) + " deliveryPoint : " + deliveryPoint);

      int pickupRemain = cap;
      while(pickupPoint >= 0 && pickupRemain > 0) {
        if (pickups[pickupPoint] - pickupRemain > 0) { //해당 지점에 한번 더 와야 하는 경우
          //System.out.println("해당 지점에 한번 더 와야 하는 경우 : pickups :" + Arrays.toString(pickups) +", pickupRemain : " + pickupRemain);
          pickups[pickupPoint] -= pickupRemain;
          pickupRemain = cap; 
        }
        else { //해당 지점에 수거를 완료한 경우 (한번 더 안와도 됨)
          pickupRemain -= pickups[pickupPoint];
          pickups[pickupPoint] = 0;
          pickupPoint--;
          //System.out.println("해당 지점에 수거를 완료한 경우 (한번 더 안와도 됨) : pickups :" + Arrays.toString(pickups) +", pickupRemain : " + pickupRemain);
          break;
        }
      }
      System.out.println(attemp+" 번 수행 한 후 pickups :" + Arrays.toString(pickups) + " pickupPoint : " + pickupPoint);
      attemp++;
      // 가장 먼 지점까지의 거리 추가 (왕복 거리)
      int maxIndex = Math.max(deliveryPoint, pickupPoint);
      answer += (maxIndex + 1) * 2;
    }
    
    return answer;
  }

  public static void main(String[] args) {
    // cap	n	deliveries	pickups	result
    // 4	5	[1, 0, 3, 1, 2]	[0, 3, 0, 4, 0]	16
    // 2	7	[1, 0, 2, 0, 1, 0, 2]	[0, 2, 0, 1, 0, 2, 0]	30

    int cap = 4; //택배에 실을 수 있는 최대 택배 수
    int n = 5; //배달할 집의 개수
    int[] deliveries = {1, 0, 3, 1, 2}; //배달할 택배
    int[] pickups = {0, 3, 0, 4, 0}; //수거할 택배

    // int cap = 2; //택배에 실을 수 있는 최대 택배 수
    // int n = 7; //배달할 집의 개수
    // int[] deliveries = {1, 0, 2, 0, 1, 0, 2}; //배달할 택배
    // int[] pickups = {0, 2, 0, 1, 0, 2, 0}; //수거할 택배
    
    DeliveryNCollection deliveryNCollection = new DeliveryNCollection();
    System.out.println(deliveryNCollection.solution(cap, n, deliveries, pickups));
  }
}
