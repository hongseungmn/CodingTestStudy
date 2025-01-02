public class VideoPlayer {
  public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
    String answer = "";
    int video_len_int = Integer.parseInt(video_len.split(":")[0]) * 60 + Integer.parseInt(video_len.split(":")[1]);
    int pos_int = Integer.parseInt(pos.split(":")[0]) * 60 + Integer.parseInt(pos.split(":")[1]);
    int op_start_int = Integer.parseInt(op_start.split(":")[0]) * 60 + Integer.parseInt(op_start.split(":")[1]);
    int op_end_int = Integer.parseInt(op_end.split(":")[0]) * 60 + Integer.parseInt(op_end.split(":")[1]);

    for(String command : commands) {

      //현재 시점이 오프닝 구간이라면 오프닝 종료 시각으로 이동
      if(pos_int >= op_start_int && pos_int < op_end_int) {
        pos_int = op_end_int;
      }
      System.out.println("현재 시각 : " + convertToMMSS(pos_int));
      if(command.equals("prev")) {
        pos_int -= 10;
        if(pos_int < 0) pos_int = 0; //0초보다 이전일 경우 0으로 이동
      }
      else if(command.equals("next")) {
        pos_int += 10;
        if(pos_int > video_len_int) pos_int = video_len_int; //비디오 실행시간을 넘길 경우 비디오 마지막 부분으로 이동
      }
    }

    //현재 시점이 오프닝 구간이라면 오프닝 종료 시각으로 이동
    if(pos_int >= op_start_int && pos_int < op_end_int) {
      pos_int = op_end_int;
    }

    return convertToMMSS(pos_int);
  }

  public String convertToMMSS(int seconds) {
    int minutes = seconds / 60;
    int remainSeconds = seconds % 60; // 초 계산
    return String.format("%02d:%02d",minutes, remainSeconds);
  }


  public static void main(String[] args) {
    
    /*
     * video_len	pos	op_start	op_end	commands	result
      "34:33"	"13:00"	"00:55"	"02:55"	["next", "prev"]	"13:00"
      "10:55"	"00:05"	"00:15"	"06:55"	["prev", "next", "next"]	"06:55"
      "07:22"	"04:05"	"00:15"	"04:07"	["next"]	"04:17"
      "10:00", "00:03", "00:00", "00:05", ["prev", "next"] "00:15"
     */
    String video_len = "10:00";
    String pos = "00:03";
    String op_start = "00:00";
    String op_end = "00:05";
    String[] commands = {"prev", "next"};

    VideoPlayer videoPlayer = new VideoPlayer();
    String answer = videoPlayer.solution(video_len, pos, op_start, op_end, commands);
    System.out.println("정답은 : " + answer);
  }
}
