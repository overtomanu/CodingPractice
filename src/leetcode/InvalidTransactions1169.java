package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class InvalidTransactions1169
{
	class Transaction implements Comparable<Transaction>{
        String txn,name,city;
        short time,amount;
        public Transaction(String txn,String name,String city,short time,short amount){
            this.txn=txn;
            this.name=name;
            this.city=city;
            this.time=time;
            this.amount=amount;
        }
        
        public int compareTo(Transaction txn){
            return this.time-txn.time;
        }
    }
    
    public List<String> invalidTransactions(String[] txns) {
        Set<String> result = new HashSet<>();
        Map<String,SortedSet<Transaction>> nameMap = new HashMap<>();
        for(int i=0;i<txns.length;i++){
            String txn = txns[i];
            String[] splits = txn.split("\\Q,\\E");
            Transaction tObj = new Transaction(txn,splits[0],splits[3],Short.parseShort(splits[1]),Short.parseShort(splits[2]));
           if(tObj.amount>1000){
               result.add(txn);
           }
           SortedSet<Transaction> set = nameMap.get(tObj.name);
           if(set==null){
               set=new TreeSet<>();
               nameMap.put(tObj.name,set);
           }
            SortedSet<Transaction> lSet = set.subSet(new Transaction("","","",(short)(tObj.time-60),(short)0),new Transaction("","","",(short)(tObj.time+61),(short)0));
           for(Transaction t2:lSet){
               if(!t2.city.equals(tObj.city)){
                   result.add(txn);
                   result.add(t2.txn);
               }
           }
            
           set.add(tObj);
        }
        return new ArrayList<String>(result);
    }
}
