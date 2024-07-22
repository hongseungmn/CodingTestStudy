/*
 * 외톨이 알파벳
 * https://school.programmers.co.kr/learn/courses/15008/lessons/121683
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Stream;

public class LonerAlphabet {

    public String solution(String input_string) {
        String answer = "";
       
        char[] charr = input_string.toCharArray();
        Set<Character> set = new HashSet<>();
       
        for(int i=0; i<charr.length; i++) {
        	char c = charr[i];
        	int last_idx = input_string.lastIndexOf(charr[i]);
        	if(i < last_idx) {
        		for(int j=i+1; j<last_idx; j++) {
        			if(c != charr[j]) {
        				//외톨이 가능
        				set.add(c);
        			}
        		}
        	}
        }
       
        for(char c : set) {
        	answer += c;
        }
       
        if(set.isEmpty()) {
        	answer += "N";
        }
       
        return answer;
    }

    public static void main(String[] args) {
    	/*
    	 * "edeaaabbeed"
    	 * "eeddee"
    	 * "string"
    	 * "zbzbz"
    	 * "abbbbbbbbaaa"
    	 */
    	
        String input_string = "aabbccaabbcc";
        LonerAlphabet solution = new LonerAlphabet();
        String answer = solution.solution(input_string);
        System.out.println(answer);
    }
}
