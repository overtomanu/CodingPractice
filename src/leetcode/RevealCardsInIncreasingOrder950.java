package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RevealCardsInIncreasingOrder950
{
	public int[] deckRevealedIncreasing(int[] deck) {
        int l = deck.length;
        if(l==0){
            return new int[]{};
        }
        Arrays.sort(deck);
        List<Integer> result = new ArrayList<>();
        result.add(deck[l-1]);
        for(int i=l-2;i>-1;i--){
            int temp = (int)result.remove(result.size()-1);
            result.add(0,temp);
        }
        return result.stream().mapToInt(Integer::intValue).toArray();
    }
}
