package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class ConcatenatedWords
{
	int a = "a".codePointAt(0);
	
	class TrieNode {
        TrieNode[] childs;
        boolean leafNode = false;
        
        public TrieNode(){
            childs = new TrieNode[26];
        }
    }
    
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        TrieNode root = new TrieNode();
        List<String> result = new ArrayList<>();
        Map<Integer,Set<String>> strMap = new HashMap<>();
        SortedSet<Integer> ss = new TreeSet<>();
         for(int i=0;i<words.length;i++){
             Integer wlength = words[i].length();
             Set<String> ilWordSet = strMap.get(wlength);
             if(ilWordSet==null){
                 ilWordSet = new HashSet<>();
                 strMap.put(wlength,ilWordSet);
             }
             ilWordSet.add(words[i]);
             ss.add(wlength);
         }
         
         Iterator<Integer>it = ss.iterator();
         
         while(it.hasNext()) {
        	 Integer wlength = it.next();
        	 Set<String> ilWordSet = strMap.get(wlength);
        	 for(String word:ilWordSet) {
        		 if(checkOrInsertWord(root,word,0)){
                     result.add(word);
                 }
        	 }
         }
        return result;
    }
    
    public boolean checkOrInsertWord(TrieNode root,String str,int si){
        boolean result = true;
        TrieNode cur = root;
        for(int i=si;i<str.length();i++){
        	int index = str.codePointAt(i)-a;
            if(cur.childs[index]==null) {
            	result=false;
            	if(si==0) {
            		for(int j=i;j<str.length();j++) {
                        index = str.codePointAt(j)-a;
            			cur.childs[index] = new TrieNode();
            			cur = cur.childs[index];
            		}
            		cur.leafNode=true;
            	}
                return result;
            }else{
                if(cur.childs[index].leafNode){
                    if(checkOrInsertWord(root,str,i+1)){
                        return true;
                    }
                }
                cur=cur.childs[index];
            }
        }
        result = cur.leafNode;
        return result;
    }
    
    public static void main(String[] args)
	{
		String words[] = new String[] {"cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"};
		System.out.println(new ConcatenatedWords().findAllConcatenatedWordsInADict(words));
	}
}
