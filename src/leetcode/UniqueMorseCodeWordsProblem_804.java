package leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class UniqueMorseCodeWordsProblem_804
{
	public static Map<String,String> charMorseCode = new HashMap<>();
    static{
        charMorseCode.put("a",".-");
        charMorseCode.put("b","-...");
        charMorseCode.put("c","-.-.");
        charMorseCode.put("d","-..");
        charMorseCode.put("e",".");
        charMorseCode.put("f","..-.");
        charMorseCode.put("g","--.");
        charMorseCode.put("h","....");
        charMorseCode.put("i","..");
        charMorseCode.put("j",".---");
        charMorseCode.put("k","-.-");
        charMorseCode.put("l",".-..");
        charMorseCode.put("m","--");
        charMorseCode.put("n","-.");
        charMorseCode.put("o","---");
        charMorseCode.put("p",".--.");
        charMorseCode.put("q","--.-");
        charMorseCode.put("r",".-.");
        charMorseCode.put("s","...");
        charMorseCode.put("t","-");
        charMorseCode.put("u","..-");
        charMorseCode.put("v","...-");
        charMorseCode.put("w",".--");
        charMorseCode.put("x","-..-");
        charMorseCode.put("y","-.--");
        charMorseCode.put("z","--..");
    }
    public int uniqueMorseRepresentations(String[] words) {
        Set<String> morseCodes = new HashSet<>();
        if(words!=null && words.length>0){
            for(String w:words){
                String mcode = "";
                for(int i=0;i<w.length();i++) {
                	mcode+=charMorseCode.get(String.valueOf(w.charAt(i)));
                }
                morseCodes.add(mcode);
            }
            return morseCodes.size();
        }
        return 0;
    }
}
