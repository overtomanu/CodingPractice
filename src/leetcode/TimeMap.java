package leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

class TimeMap {

    Map<String,Map<Integer,String>> m = new HashMap<>();
    /** Initialize your data structure here. */
    public TimeMap() {
        
    }
    
    public void set(String key, String value, int timestamp) {
        System.out.println("set:\t"+key+"\t"+value+"\t"+timestamp);
        Map<Integer,String> vMap = m.get(key);
        if(vMap==null){
            vMap=new TreeMap<Integer,String>();
            m.put(key,vMap);
        }
        vMap.put(timestamp,value);
        System.out.println("Map:\t"+m);
    }
    
    public String get(String key, int timestamp) {
        System.out.println("get:\t"+key+"\t"+timestamp);
        System.out.println("Map:\t"+m);
        Map<Integer,String> vMap = m.get(key);
        if(vMap==null){
            System.out.println("return:empty str\t");
            return "";
        }
        String value = vMap.get(timestamp);
        if(value!=null){
            System.out.println("return:\t"+value);
            return value;
        }
        //binary search
        Integer[] ts = vMap.keySet().toArray(new Integer[vMap.keySet().size()]);
        int low = 0,high = vMap.keySet().size()-1;
        while(low+1<high){
            System.out.println("low,high:\t"+low+","+high);
            int mid = low+(high-low)/2;
            if(ts[mid]>timestamp){
                high=mid;
            }else{
                low=mid;
            }
        }
        if(ts[high]<=timestamp){
            System.out.println("return:\t"+vMap.get(ts[high]));
            return vMap.get(ts[high]);
        }else{
            System.out.println("return:\t"+vMap.get(ts[low]));
            return vMap.get(ts[low]);
        }
    }
}

/**
 * Your TimeMap object will be instantiated and called as such:
 * TimeMap obj = new TimeMap();
 * obj.set(key,value,timestamp);
 * String param_2 = obj.get(key,timestamp);
 */



/**
 * Your TimeMap object will be instantiated and called as such:
 * TimeMap obj = new TimeMap();
 * obj.set(key,value,timestamp);
 * String param_2 = obj.get(key,timestamp);
 */