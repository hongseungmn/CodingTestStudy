import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
	class Music {
		String genre = "";
		ArrayList<int[]> charts = new ArrayList<>();
		int totalPlayCount = 0;

		public Music(int play, int index) {
			totalPlayCount += play;
			int[] item = new int[2];
			item[0] = index;
			item[1] = play;
			charts.add(item);
		}

		public void addItem(int play, int index) {
			totalPlayCount += play;
			int[] item = new int[2];
			item[0] = index;
			item[1] = play;
			charts.add(item);
		}

		public void sortByIndex() {
			charts.sort((a1, a2) -> {
				return a2[0] - a1[0];
			});
		}

		public void sortByPlay() {
			charts.sort((a1, a2) -> {
				return a2[1] - a1[1];
			});
		}
	}

	public int[] solution(String[] genres, int[] plays) {
		ArrayList<Integer> answer = new ArrayList<>();
		Map<String, Music> map = new HashMap<>();
		for (int i = 0; i < genres.length; i++) {
			if (map.keySet().contains(genres[i])) {
				map.get(genres[i]).addItem(plays[i], i);
			} else {
				map.put(genres[i], new Music(plays[i], i));
			}
		}

		List<Music> musicList = new ArrayList<>(map.values());
		musicList.sort((a1, a2) -> {
			return a2.totalPlayCount - a1.totalPlayCount;
		});

		for (int i = 0; i < musicList.size(); i++) {
			musicList.get(i).sortByPlay();
		}
		for (int i = 0; i < musicList.size(); i++) {
			answer.add(musicList.get(i).charts.get(0)[0]);
            if (musicList.get(i).charts.size() != 1) {
				answer.add(musicList.get(i).charts.get(1)[0]);
			}
		}
		int[] arr = answer.stream()
				.mapToInt(Integer::intValue)
				.toArray();
		return arr;
	}
}