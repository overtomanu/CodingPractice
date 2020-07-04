package leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class VowelSpellchecker966
{
	Set<Character> vowels = new HashSet<Character>()
	{
		{
			add('a');
			add('e');
			add('i');
			add('o');
			add('u');
			add('A');
			add('E');
			add('I');
			add('O');
			add('U');
		}
	};

	class TrieNode
	{
		Map<Character, TrieNode>	childs	= new HashMap<>(5);	// log 5000/log
																// 7
		TrieNode					iVowelNode;
		String						word, iCaseWord;
	}

	public String[] spellchecker(String[] wordlist, String[] queries)
	{
		int N;
		if (wordlist == null || queries == null || (N = wordlist.length) == 0
				|| queries.length == 0)
		{
			return new String[] {};
		}

		String[] result = new String[queries.length];
		TrieNode root = new TrieNode();
		for (int i = N - 1; i > -1; i--)
		{
			char[] insStr = wordlist[i].toCharArray();
			TrieNode curNode = root;
			processTrie(wordlist[i], wordlist[i], root, true);
			/*for(int j=0;j<insStr.length;j++){
			char c = insStr[j];
			TrieNode nxtNode = curNode.childs.get(c);
			if(nxtNode==null){
			nxtNode = new TrieNode();
			curNode.childs.put(c, nxtNode);
			}
			
			}*/
		}

		for (int i = 0; i < queries.length; i++)
		{
			// char[] insStr = queries[i].toCharArray();
			TrieNode curNode = root;
			boolean exactMatch = true;
			/*for(int j=0;j<insStr.length;j++){
			if(curNode==null){
			break;
			}
			char c = insStr[j];
			TrieNode nxtNode = curNode.childs.get(c);
			if(nxtNode==null){
			Character otherC = Character.isUpperCase(c)? Character.toLowerCase(c):Character.toUpperCase(c);
			nxtNode = curNode.childs.get(otherC);
			exactMatch=false;
			}
			if(nxtNode==null){
			Character ic = Character.toUpperCase(c);
			if(ic.equals('A')||ic.equals('E')||ic.equals('I')||ic.equals('O')||ic.equals('U')){
			nxtNode=curNode.iVowelNode;
			}
			}
			curNode=nxtNode;
			}*/
			/*if(curNode!=null){
			if(exactMatch){
			result[i]=curNode.word;
			}else{
			result[i]=curNode.iCaseWord;
			}
			}else{
			result[i]="";
			}*/
			String rStr = matchStr(queries[i], root, true);
			if (rStr != null)
			{
				result[i] = rStr;
			}
			else
			{
				result[i] = "";
			}
		}
		return result;
	}

	private void processTrie(String origWord, String str, TrieNode curNode,
			boolean isInsert)
	{
		if (str == null || str.length() == 0)
		{
			return;
		}
		Character c = str.charAt(0);
		Character ic = Character.toUpperCase(c);
		TrieNode nxtNode = curNode.childs.get(c);
		if (isInsert)
		{
			if (nxtNode == null)
			{
				nxtNode = new TrieNode();
				curNode.childs.put(c, nxtNode);
			}
			if (str.length() == 1)
			{
				nxtNode.word = origWord;
				nxtNode.iCaseWord = origWord;
			}
			if (ic.equals('A') || ic.equals('E') || ic.equals('I') || ic.equals('O')
					|| ic.equals('U'))
			{
				curNode.iVowelNode = nxtNode;
			}
			processTrie(origWord, str.substring(1), nxtNode, true);
		}
		else
		{
			if (nxtNode != null)
			{
				processTrie(origWord, str.substring(1), nxtNode, false);
				if (str.length() == 1)
				{
					nxtNode.iCaseWord = origWord;
				}
			}
		}

		Character otherC = Character.isUpperCase(c) ? Character.toLowerCase(c) : ic;
		TrieNode otherNode = curNode.childs.get(otherC);
		if (otherNode != null)
		{
			processTrie(origWord, str.substring(1), otherNode, false);
			if (str.length() == 1)
			{
				otherNode.iCaseWord = origWord;
			}
		}

		if (ic.equals('A') || ic.equals('E') || ic.equals('I') || ic.equals('O')
				|| ic.equals('U'))
		{
			if (nxtNode != null)
			{
				curNode.iVowelNode = nxtNode;
			}

			Set<Character> otherVowels = new HashSet<>(vowels);
			otherVowels.remove(c);
			otherVowels.remove(otherC);
			for (Character vc : otherVowels)
			{
				nxtNode = curNode.childs.get(vc);
				if (nxtNode != null)
				{
					processTrie(origWord, str.substring(1), nxtNode, false);
					if (str.length() == 1)
					{
						nxtNode.iCaseWord = origWord;
					}
				}
			}
		}
	}

	private String matchStr(String input, TrieNode curNode, boolean exactMatch)
	{

		if (input.length() == 0)
		{
			if (exactMatch)
			{
				return curNode.word;
			}
			else
			{
				return curNode.iCaseWord;
			}
		}
		Character c = input.charAt(0);
		TrieNode nxtNode = curNode.childs.get(c);
		if (nxtNode != null)
		{
			String result = matchStr(input.substring(1), nxtNode, exactMatch);
			if (result != null)
			{
				return result;
			}
		}
		Character ic = Character.toUpperCase(c);
		Character otherC = Character.isUpperCase(c) ? Character.toLowerCase(c) : ic;
		TrieNode otherNode = curNode.childs.get(otherC);
		if (otherNode != null)
		{
			String result = matchStr(input.substring(1), otherNode, false);
			if (result != null)
			{
				return result;
			}
		}

		if (ic.equals('A') || ic.equals('E') || ic.equals('I') || ic.equals('O')
				|| ic.equals('U'))
		{
			String result = matchStr(input.substring(1), curNode.iVowelNode, false);
			if (result != null)
			{
				return result;
			}
		}
		return null;
	}

	public static void main(String[] args)
	{
		/*String[] wordlist = new String[]
		{
			"KiTe", "kite", "hare", "Hare"
		};
		String[] queries = new String[]
		{
			"kite", "Kite", "KiTe", "Hare", "HARE", "Hear", "hear", "keti", "keet", "keto"
		};*/
		/*String[] wordlist = new String[]
		{
			"AA", "aa"
		};
		String[] queries = new String[]
		{
			"aA"
		};*/
		String[] wordlist = new String[]
		{
			"wg", "uo", "as", "kv", "ra", "mw", "gi", "we", "og", "zu"
		};
		String[] queries = new String[]
		{
			"AS", "in", "yc", "kv", "mw", "ov", "lc", "os", "wm", "Mw"
		};

		System.out.println(Arrays
				.asList(new VowelSpellchecker966().spellchecker(wordlist, queries)));

	}
}