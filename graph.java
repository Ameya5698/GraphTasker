package com.chatter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;


public class graph {
public Map <Integer,ArrayList<Integer>> network ;
	
public int CurrUsers;

public int maxIdTillNow;

public graph()
{
	this.network=new HashMap<Integer,ArrayList<Integer>>();
	this.CurrUsers=0;
	this.maxIdTillNow=-1;
}

	
void addUser(int id)
{	if(!this.network.containsKey(id))
	{
		ArrayList<Integer> temp=new ArrayList<Integer>();
		this.network.put(id,temp);
		this.CurrUsers++;
		if(id>this.maxIdTillNow)
		{
			this.maxIdTillNow=id;
		}
		
		
	
	}
}


void addEdge(int id1,int id2)
{
	ArrayList<Integer> temp=this.network.get(id1);
	if(temp.contains(id2)==false)
	{
		temp.add(id2);
		this.network.replace(id1, temp);
	}
	
	
	/*temp=this.network.get(id2);
	temp.add(id1);
	this.network.replace(id2,temp); making Directed graph...*/ 
	
}

void PrintGraph()
{
	for(Map.Entry<Integer,ArrayList<Integer>> ent:this.network.entrySet())
	{
		System.out.print(ent.getKey()+"-->");
		ArrayList<Integer> temp=ent.getValue();
		for(int i=0;i<temp.size();i++)
		{
			System.out.print(temp.get(i)+" ");
		}
		System.out.println();
	}
	
	
	
}

private void dfsHelper(int root ,int [] visited) {
	
	visited[root]=1;
	System.out.print(root+" ");
	
	ArrayList<Integer> temp=this.network.get(root);
	
	for(int i=0;i<temp.size();i++)
	{
		if(visited[temp.get(i)]==0)
		{
			dfsHelper(temp.get(i),visited);
		}
	}
	
	
	
	
	
}


void dfs(int root)
{
	
	int visited[]=new int[this.maxIdTillNow+1];
	
	dfsHelper(root,visited);
	
	
	
	
	
	
	
	
	
}

void bsf(int root)
{System.out.println();
	
	int visited[]=new int[this.maxIdTillNow+1];
	
	Queue<Integer> q=new  LinkedList<Integer>();
	
	q.offer(root);
	visited[root]=1;
	
	while(q.isEmpty()==false)
	{
		int top=q.poll();
		System.out.print(top+" ");
		ArrayList<Integer> temp=this.network.get(top);
		
		for(int i=0;i<temp.size();i++)
		{
			if(visited[temp.get(i)]==0)
			{
				visited[temp.get(i)]=1;
				q.offer(temp.get(i));
			}
		}
		
		
		
		
	}
	
	
	
	
	
	
	
}


void RemoveEdge(int id1,int id2)
{
	ArrayList<Integer> temp = this.network.get(id1);
	
	Iterator<Integer> itr=temp.iterator();
	
	while(itr.hasNext())
	{
		int temper=itr.next();
		if(temper==id2)
		{
			itr.remove();
		}
		
	}
	
	
	this.network.replace(id1, temp);
	
	/*temp=this.network.get(id2);
	
	itr=temp.iterator();
	
	while(itr.hasNext())
	{
		int temper=itr.next();
		if(temper==id1)
		{
			itr.remove();
		}
		
	}
	
	
	
	
	
	this.network.replace(id2, temp);*/
	
	
	
	
	
	
	
}


void RemoveEdgeSingle(int src,int dest)
{
	// removes dest from src....
	ArrayList<Integer> temp=this.network.get(src);
	
	Iterator<Integer> it=temp.iterator();
	while(it.hasNext())
	{
		int n=it.next();
		if(n==dest)
		{
			it.remove();
		}
	}
	
	this.network.replace(src,temp);
	
}




void DeleteUser(int id)
{
	/*ArrayList<Integer> temp=this.network.get(id);
	
	
	for(int i=0;i<temp.size();i++)
	{
		RemoveEdgeSingle(temp.get(i),id);
	}
	
	this.network.remove(id);
	
	this.CurrUsers--;
	if(id==this.maxIdTillNow)
	{
		this.maxIdTillNow--;
	}
	*/
	
	//DatabaseUser.getSession();
	
	System.out.println("SUCCESS");
	
	

	
	
	
}
int getMinDijstra(int visited[],int distances[])
{
	int index=-1;
	int maxer=Integer.MAX_VALUE;
	//check for starting of i......
	for(int i=1;i<=this.maxIdTillNow;i++)
	{
		if(visited[i]==0 && maxer>distances[i])
		{index=i;
		maxer=distances[i];	
			
		}
		
		
	}
	
	
	
	return index;
	
}


void Dijstra(int root)
{
	
	int visited[]=new int[this.maxIdTillNow+1];
	int distances[]=new int[this.maxIdTillNow+1];
	
	for(int i=0;i<=this.maxIdTillNow;i++)
	{
		distances[i]=Integer.MAX_VALUE;
	}
	
	distances[root]=0;
	
	for(int i=0;i<this.CurrUsers;i++)
	{
		int u=getMinDijstra(visited,distances);
		if(u!=-1) {
		visited[u]=1;
		
		ArrayList<Integer> temp=this.network.get(u);
		
		for(int k=0;k<temp.size();k++)
		{
			if(visited[temp.get(k)]==0 && distances[temp.get(k)]>distances[u]+1)
			{
				distances[temp.get(k)]=distances[u]+1;
			}
			
			
		}
		}
		
		
		
		
	}
	
	// lets print distances...
	
	for(int i=1;i<this.maxIdTillNow+1;i++)
	{	if(distances[i]!=Integer.MAX_VALUE) {
		System.out.println(i+" is at distance "+ distances[i] + "from the root");
	}}
	
	
	
	
	
	
	
}


void fillerForShortPath(int id1,int id2,ArrayList<Integer> paths,int parrents[])
{
	if(id1==id2)
	{
		return ;
	}
	
	
	ArrayList<Integer> temp=this.network.get(id1);
	
	
	
	
	
	
	
	
	
	
	
}



void shortestPathBetween(int id1,int id2)
{
	
	ArrayList<Integer> paths=new ArrayList<Integer>();
	
	 int parrents[]=new int[this.maxIdTillNow+1];
	 
	 int visited[]=new int[this.maxIdTillNow+1];
	 
	 parrents[id1]=-1;
	 visited[id1]=1;
	 
	 Queue<Integer> q=new LinkedList<Integer>();
	 
	 q.offer(id1);
	 
	 while(q.isEmpty()==false)
	 {
		 
		int top=q.poll();
		
		ArrayList<Integer> temp=this.network.get(top);
		
		for(int i=0;i<temp.size();i++)
		{
			if(visited[temp.get(i)]==0)
			{
				visited[temp.get(i)]=1;
				parrents[temp.get(i)]=top;
				q.offer(temp.get(i));
			}
		}
		
		
		
		 
		 
		 
		 
		 
	 }
	 
	 // the paths....
	 
	 Stack<Integer> s=new Stack<>();
	
	 
	 int curr=id2;
	 
	 while(curr!=-1)
	 {	
		 //
		 s.push(curr);
		 curr=parrents[curr];
		 //System.out.print("the new curr-->"+curr+"<--");
	 }
	 
	 // printing..
	 while(s.empty()==false)
	 {	curr=s.pop();
		 System.out.print(curr+" ");
	 }
	 
	 
	 System.out.println();
	 
	 
	 
	 
	 
	
	
	
	
	
	
}
private void PrintAllPathsHelper(int id1,int id2,int visited[],int parents[])
{
	
	if(id1==id2)
	{
		int curr=id2;
		while(curr!=-1)
		{
			System.out.print(curr+" ");
			curr=parents[curr];
		}
		System.out.println();
		return ;
		
	}
	
	visited[id1]=1;
	
	ArrayList<Integer> temp=this.network.get(id1);
	
	for(int i=0;i<temp.size();i++)
	{
		if(visited[temp.get(i)]==0)
		{
			parents[temp.get(i)]=id1;
			PrintAllPathsHelper(temp.get(i), id2, visited, parents);
		}
	}
	
	visited[id1]=0;
	
	
	
	
	
	
	
	
	
	
}

void PrintAllPaths(int id1,int id2)
{
	int parents[]=new int[this.maxIdTillNow+1];
	
	int visited[]=new int[this.maxIdTillNow+1];
	int index=0;
	for(int i=0;i<=this.maxIdTillNow;i++)
	{
		visited[i]=0;
		parents[i]=0;
	}
		
	parents[id1]=-1;
	
	PrintAllPathsHelper(id1, id2, visited, parents);
	
	System.out.println();
	
	
	
	
	
	
	
	
	
}

private int iscycleUntill(int root,int parents[],int visited[])
{
	visited[root]=1;
	
	ArrayList<Integer> temp=this.network.get(root);
	
	for(int i=0;i<temp.size();i++)
	{
		
		if(visited[temp.get(i)]==0)
		{
			parents[temp.get(i)]=root;
			if(iscycleUntill(temp.get(i), parents, visited)==1)
			{
				return 1;
			}
		}
		
		else if(visited[temp.get(i)]==1 && parents[temp.get(i)]!=root)
		{
			return 1;
		}
		
	}
	
	
	return 0;
	
	
	
	
}


int isCycle(int id) {
	
	int parents[]=new int[this.maxIdTillNow+1];
	
	int visited[]=new int[this.maxIdTillNow+1];
	
	
	
	parents[id]=-1;
	
	return iscycleUntill(id, parents, visited);
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}





	
}
