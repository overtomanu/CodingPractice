package leetcode;

class MinimumCostForTickets {
    public int mincostTickets(int[] days, int[] costs) {
        int[] costTillDayAtIndex = new int[days.length];
        for(int i=0;i<costTillDayAtIndex.length;i++)
        {
            costTillDayAtIndex[i]=-1;
        }

        return dpGetCostAtDay(days.length-1,days[days.length-1],days,costs,costTillDayAtIndex);
    }
    
    // top down DP
    private int dpGetCostAtDay(int upperIndex,int day,int[] days,int[] costs,int[] costTillDayAtIndex){
        if(day<1){
            return Integer.MAX_VALUE;
        }
        int dayIndex=-1;
        for(int i=upperIndex;i>-1;i++){
            if(days[i]<=day && costTillDayAtIndex[i]!=-1){
                if(days[i]==day){
                    dayIndex=i;
                }
                return costTillDayAtIndex[i];
            }
        }
        
        int dayMinus1Cost = dpGetCostAtDay(upperIndex-1,day-1,days,costs,costTillDayAtIndex);
        int dayMinus7Cost = dpGetCostAtDay(upperIndex-1,day-7,days,costs,costTillDayAtIndex);
        int dayMinus30Cost = dpGetCostAtDay(upperIndex-1,day-30,days,costs,costTillDayAtIndex);
        
        if(dayMinus1Cost<=Integer.MAX_VALUE){
            dayMinus1Cost+=costs[0];
        }
        if(dayMinus7Cost<=Integer.MAX_VALUE){
            dayMinus7Cost+=costs[1];
        }
        if(dayMinus30Cost<=Integer.MAX_VALUE){
            dayMinus30Cost+=costs[2];
        }
        
        int result = -1;
        if(dayMinus1Cost<dayMinus7Cost){
            if(dayMinus1Cost<dayMinus30Cost){
                result=dayMinus1Cost;
            }else{
                result=dayMinus30Cost;
            }
        }else{
            if(dayMinus7Cost<dayMinus30Cost){
                result=dayMinus7Cost;
            }else{
                result=dayMinus30Cost;
            }
        }
        //memoize
        if(dayIndex!=-1){
            costTillDayAtIndex[dayIndex]=result;
        }
        
        return result;
    }
}