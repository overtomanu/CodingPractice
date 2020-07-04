package leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

class NumberOfAtoms {
    String formula;
    private int pos=0;
    
    public String countOfAtoms(String formula) {
        this.formula=formula;
        Map<String,Integer> countMap = evaluateFormula();
        Map<String,Integer> sMap = new TreeMap<>(countMap);
        String result="";
        for(Map.Entry<String,Integer> entry:sMap.entrySet()){
        	Integer count = (Integer)entry.getValue();
            result+=entry.getKey().toString();
            result+=count>1?entry.getValue().toString():"";
        }
        return result;
    }
    
    public Map<String,Integer> evaluateFormula(){
    	System.out.println("evaluateFormula:"+formula.substring(pos));
        Map<String,Integer> totalCountMap = new HashMap<>();
        Map<String,Integer> countMap;
        while(pos<formula.length()){
        	System.out.println("Current char:"+formula.charAt(pos));
            if(formula.charAt(pos)==')'){
                return totalCountMap;
            }else if(formula.charAt(pos)=='('){
                countMap=evaluateParanthesizedFormula();
            }else{
                countMap=evaluateSequentialFormula();
            }
            for(Map.Entry<String,Integer> entry:countMap.entrySet()){
                totalCountMap.put(entry.getKey(),totalCountMap.getOrDefault(entry.getKey(),0)+entry.getValue());
            }
        }
        return totalCountMap;
    }
    
    public Map<String,Integer> evaluateParanthesizedFormula(){
    	System.out.println("evaluateParanthesizedFormula:"+formula.substring(pos));
        pos++;
        Map<String,Integer> countMap = evaluateFormula();
        pos++;
        Integer count = 1;
        if(pos<formula.length() && Character.isDigit(formula.charAt(pos))){
            count=evaluateNumber();
        }
        if(count==0){
            return new HashMap<>();
        }
        if(count>1){
            for(Map.Entry<String,Integer> entry:countMap.entrySet()){
            	countMap.put(entry.getKey(),entry.getValue()*count);
            }
        }
        return countMap;
    }
    
    public Map<String,Integer> evaluateSequentialFormula(){
    	System.out.println("evaluateSequentialFormula:"+formula.substring(pos));
        Map<String,Integer> totalCountMap = new HashMap<>();
        while(pos<formula.length() && formula.charAt(pos)!='(' && formula.charAt(pos)!=')'){
            String element = evaluateElement();
            Integer count = 1;
            if(pos<formula.length() && Character.isDigit(formula.charAt(pos))){
                count=evaluateNumber();
            }
            totalCountMap.put(element,totalCountMap.getOrDefault(element, 0)+count);
        }
		return totalCountMap;
    }
    
    public Integer evaluateNumber(){
    	System.out.println("evaluateNumber:"+formula.substring(pos));
        Integer value = formula.charAt(pos) - '0';
        pos++;
        while (pos<formula.length() && Character.isDigit(formula.charAt(pos))) {
            value = value * 10 + (formula.charAt(pos) - '0');
            pos++;
        }
        return value;
    }
    
    public String evaluateElement(){
    	System.out.println("evaluateElement:"+formula.substring(pos));
        StringBuilder build = new StringBuilder();
        if(pos<formula.length() && Character.isUpperCase(formula.charAt(pos))){
            build.append(formula.charAt(pos));
            pos++;
        }
        while (pos<formula.length() && Character.isLowerCase(formula.charAt(pos))) {
            build.append(formula.charAt(pos));
            pos++;
        }
        return build.toString();
    }
    
    public static void main(String[] args)
	{
		String formula="";
		//formula="K4(ON(SO3)2)2";
		//formula="H2O";
		formula="H11He49NO35B7N46Li20";
		System.out.println(new NumberOfAtoms().countOfAtoms(formula));
	}
}