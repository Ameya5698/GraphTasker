package com.chatter;

import java.sql.SQLException;
import java.util.ArrayList;

public class GraphTasker {

	
	
	
	public graph GT;
	
	public GraphTasker(int flag) 
	{	// flag=1 load previous data structure ...else during first time, we create a new one....(flag=0)
		
		this.GT=new graph();
		if(flag==1)
		{
		try {
			this.GT=DatabaseUser.getSession();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	
		
	}
	
	public void  adduser(String name)
	{
		
		
		try {
			
			this.GT.addUser(DatabaseUser.adduser(name));
			DatabaseUser.SaveSessionGraph(GT);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
	}
	
	public int ShowIDForName(String name)
	{int p=-1;
		try {
			p=DatabaseUser.PrintIdForName(name);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return p;
	}
	
	public void PrintGraph()
	{
		this.GT.PrintGraph();
	}
	
	public void deleteuser(int id)
	{
		
		try {
			DatabaseUser.deleteUser(id);
			//this.GT.DeleteUser(id);
			
			this.GT=DatabaseUser.getSession();
			DatabaseUser.SaveSessionGraph(GT);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
		
		
		
		
		
	}
	
	
	public void AddUserWithId(int id,String name)
	{
		
		
		try {
			DatabaseUser.addCustomUser(id, name);
			this.GT.addUser(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}
	
	public void IsTaskDone(int from,int to,int taskid)
	{
		try {
			int a=DatabaseUser.istaskdone(from, to, taskid);
			if(a==1)
			{
				System.out.println("The task from "+from+" to "+to+" with task-id "+taskid+" is Done!!! ");
			}
			else {
				System.out.println("The task from "+from+" to "+to+" with task-id "+taskid+" is Not Done!!! ");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void Addconnection(int fromId,int toid)
	{
		
		try {this.GT.addEdge(fromId, toid);
			DatabaseUser.SaveSessionGraph(GT);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		
	}
	
	public void RemoveConnection(int fromId,int Toid)
	{
		
		try {
			this.GT.RemoveEdge(fromId, Toid);
			DatabaseUser.SaveSessionGraph(GT);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
	}
	
	
	
	
	public void releaveFromTask(int fromId,int toId,int taskid)
	{
		
		
		try {
			DatabaseUser.DeleteTask(fromId, toId, taskid);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	public void PrintAvailablePassers(int id)
	{
		ArrayList<Integer> temp=this.GT.network.get(id);
		System.out.println("The users that are free for task assignment");
		for(int i=0;i<temp.size();i++)
		{
			int isfree;
			try {
				isfree = DatabaseUser.isUserFree(temp.get(i));
				
				if(isfree==1)
				{
					System.out.print(temp.get(i)+" ");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		System.out.println();
		
		
		
	}
	
	public void Mytasks(int id)
	{
		try {
			DatabaseUser.PrintTasks(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void TasksGivenToOthers(int id)
	{
		try {
			DatabaseUser.PrintTaskGivenToOthers(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	public void MarksAsComplete(int fromid,int toid,int taskid)
	{
		
		try {
			DatabaseUser.MarkTaskDone(fromid, toid, taskid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	public void showDoneTasksByUsers(int id)
	{
		try {
			DatabaseUser.PrintDoneByUser(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	public void ShowAssignedCompletedTasks(int id)
	{
		
		try {
			DatabaseUser.printDonetasksForManager(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void OutSourceTask(int from,int to,int nextTo,int task_id)
	{
		try {
			if(GT.network.get(to).contains(nextTo))
			{DatabaseUser.passTaskInGraph(from, to, nextTo,task_id);}
			else {
				System.out.println("The "+nextTo+" user for outsourcing is not under you as per Grapher.");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void levelOfTask(int from,int to)
	{
		
		this.GT.shortestPathBetween(from,to);
		
		
		
		
		
	}
	
	
	public void SaveSession()
	{
		try {
			DatabaseUser.SaveSessionGraph(this.GT);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void GiveTask(int from,int to,String task)
	{
		try {
			DatabaseUser.addMessageForUser(from, to, task);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void MarkDoneTaskWithNotification(int from,int to,int task_id)
	{
		try {
			DatabaseUser.deleteTaskOncompleteWithNotification(from, to, task_id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}
	
	public void GetTaskThatAreOutsourced(int id)
	{
		try {
			DatabaseUser.GetOutsourcesTaskForManger(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
	}
	
	public void ClearLogForUser(int id)
	{
		try {
			DatabaseUser.ClearLog(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void UncheckATask(int from,int to,int taskid)
	{
		try {
			DatabaseUser.MarkDoneAsNotDone(from, to, taskid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
