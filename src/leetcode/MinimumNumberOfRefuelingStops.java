package leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class MinimumNumberOfRefuelingStops
{
	public int minRefuelStops(int target, int startFuel, int[][] stations) {
        // include starting point and end target in station array
        // index 0 is distance, 1 is fuel to be filled, 2 is fuel left after reaching station i
        long[][] allPoints = new long[stations.length+2][3];
        
        allPoints[0][0]=0;
        allPoints[0][1]=startFuel;
        allPoints[allPoints.length-1][0]=target;
        allPoints[allPoints.length-1][1]=0;
        
        long curFuel = startFuel;
        
        for(int i=1;i<allPoints.length-1;i++){
        	allPoints[i][0]=stations[i-1][0];
        	allPoints[i][1]=stations[i-1][1];
            curFuel-=(allPoints[i][0]-allPoints[i-1][0]);
            if(curFuel<0){
                return -1;
            }
            allPoints[i][2]=curFuel;
            curFuel+=allPoints[i][1];
        }
        curFuel-=(allPoints[allPoints.length-1][0]-allPoints[allPoints.length-2][0]);
        if(curFuel<0) {
        	return -1;
        }
        allPoints[allPoints.length-1][2]=curFuel;
        
        //Target reachable, now try removing stations contributing least fuel
        int stops = stations.length;
        TreeMap<Long,Set<Integer>> tm = new TreeMap<>();
        for(int i=1;i<allPoints.length-1;i++){
            Set<Integer> s = tm.get(allPoints[i][1]);
            if(s==null){
                s=new HashSet<>();
                tm.put(allPoints[i][1],s);
            }
            s.add(i);
        }
        Set<Integer> removed = new HashSet<>();
        for(Map.Entry<Long,Set<Integer>> e: tm.entrySet()){
            Set<Integer> s = e.getValue();
            for(Integer i:s) {
            	if(removedAndUpdate(i,allPoints,removed)){
                    removed.add(i);
                }
            }
        }
        
        return stops-removed.size();
    }
    
    private boolean removedAndUpdate(Integer i,long[][] allPoints,Set<Integer> removed){
        //remove fuel added at this station at subsequent stops
        
        Map<Integer,Long> updatedFuels = new HashMap<>();
        for(int j=i+1;j<allPoints.length;j++){
            if(!removed.contains(j)){
            	Long newFuel = allPoints[j][2]-allPoints[i][1];
                if(newFuel>-1){
                    updatedFuels.put(j,newFuel);
                }else{
                    return false;
                }
            }
        }
        
        for(Map.Entry<Integer,Long> e:updatedFuels.entrySet()){
            Integer j = e.getKey();
            Long newFuel = e.getValue();
            allPoints[j][2]=newFuel;
        }
        
        return true;
    }
    
    public static void main(String[] args)
	{
		System.out.println("Solution: "+
	new MinimumNumberOfRefuelingStops()
	.minRefuelStops(1000000000,1000000000,
	new int[][]{{5,1000000000},{1000,1000000000},{100000,1000000000}}));
	}
}
