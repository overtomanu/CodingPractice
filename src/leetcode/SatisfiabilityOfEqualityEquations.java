package leetcode;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class SatisfiabilityOfEqualityEquations {
    
    public class DisjointSet<E>
    {
	private Map<E, DisjointSetNode>	nodeMap		= new HashMap<>();
	private Set<DisjointSetNode>    rootNodes	= new HashSet<>();
	boolean isCompressPath = false;

	public DisjointSet()
	{
		nodeMap = new HashMap<>();
		rootNodes = new HashSet<>();
	}

	public DisjointSetNode makeSet(E o) throws Exception
	{
		if (nodeMap.containsKey(o))
		{
			throw new Exception("Set already exists for specified object:"+o);
		}
		DisjointSetNode node = new DisjointSetNode();
		nodeMap.put(o, node);
		rootNodes.add(node);
		return node;
	}

	public DisjointSetNode find(DisjointSetNode node)
	{
		if (node != node.getParent())
		{
			DisjointSetNode parentNode = find(node.getParent());
			if(isCompressPath) {
				node.setParent(parentNode);
			}
			return parentNode;
		}
		return node;
	}

	public DisjointSetNode union(DisjointSetNode x, DisjointSetNode y)
	{
		x = find(x);
		y = find(y);

		if (x == y)
		{
			return x;
		}

		if (x.getSize() >= y.getSize())
		{
			y.setParent(x);
			x.setSize(x.getSize() + y.getSize());
			rootNodes.remove(y);
			return x;
		}
		else
		{
			x.setParent(y);
			y.setSize(y.getSize()+x.getSize());
			rootNodes.remove(x);
			return y;
		}
	}

	public DisjointSetNode getDisjointSetNode(E o)
	{
		return nodeMap.get(o);
	}
	
	public Map<E,DisjointSetNode> getNodeMap()
	{
		return Collections.unmodifiableMap(nodeMap);
	}
	
	public Set<DisjointSetNode> getRootNodes(){
		return Collections.unmodifiableSet(rootNodes);
	}
	
	public void doPathCompression(boolean b) {
		isCompressPath=b;
	}

	public class DisjointSetNode
	{
		private DisjointSetNode	parent;
		private int				size;

		public DisjointSetNode()
		{
			this.parent = this;
			this.size = 1;
		}

		public DisjointSetNode getParent()
		{
			return parent;
		}

		public void setParent(DisjointSetNode parent)
		{
			this.parent = parent;
		}

		public int getSize()
		{
			return size;
		}

		public void setSize(int rank)
		{
			this.size = rank;
		}
		
		public String toString() {
			return super.toString()+" size:"+size;
		}
	}
}

    
    public boolean equationsPossible(String[] equations) {
        //int a = "a".codePointAt(0);
        DisjointSet<Integer> ds = new DisjointSet<>();
        for(String s:equations){
            int a = s.codePointAt(0);
            int b = s.codePointAt(3);
            
            DisjointSet.DisjointSetNode na = ds.getDisjointSetNode(a);
            //fix:for input c==c, move it below ->DisjointSet.DisjointSetNode nb = ds.getDisjointSetNode(b);
            if(na==null){
                try
				{
					na = ds.makeSet(a);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
            }
            DisjointSet.DisjointSetNode nb = ds.getDisjointSetNode(b);
            if(nb==null){
                try
				{
					nb = ds.makeSet(b);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
            }
            
            boolean isEqual = s.codePointAt(1)=="=".codePointAt(0);
            if(isEqual){
                //union
                ds.union(na,nb);
            }
        }
        
        for(String s:equations){
            int a = s.codePointAt(0);
            int b = s.codePointAt(3);
            DisjointSet.DisjointSetNode na = ds.getDisjointSetNode(a);
            DisjointSet.DisjointSetNode nb = ds.getDisjointSetNode(b);
            boolean isEqual = s.codePointAt(1)=="=".codePointAt(0);
            if(!isEqual){
                if(ds.find(na)==ds.find(nb)){
                    return false;
                }
            }
        }
        return true;
    }
    
    public static void main(String[] args)
	{
		SatisfiabilityOfEqualityEquations s = new SatisfiabilityOfEqualityEquations();
		String input[] = new String[] {"c==c","b==d","x!=z"};
		System.out.println("final result:"+s.equationsPossible(input));
	}
}