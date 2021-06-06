package com.chatter;



// Created by AMEYA M(6-6-21)
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;

import com.mysql.cj.jdbc.Driver;

public class DatabaseUser {

	static String loc=PassDBdata.loc;
	static String root=PassDBdata.id;
	static String pass=PassDBdata.pass;

static int adduser(String name) throws SQLException
{
	Connection con=DriverManager.getConnection(loc,root,pass);
	
	
	String fet="select * from basicinfo ";
	int count=1;
	Statement st=con.createStatement();
	ResultSet rs=st.executeQuery(fet);
	int flag=1;
	while(rs.next() && flag==1)
	{
		int temp=rs.getInt(1);
		if(temp==count)
		{
			count++;
		}
		else {
			
			String inserter="insert into basicinfo value(?,?)";
			PreparedStatement st1=con.prepareStatement(inserter);
			st1.setInt(1,count);
			st1.setString(2,name);
			int i=st1.executeUpdate();
			if(i==1)
			{
				System.out.println("User "+name+" added to GT "+" with Id "+count);
				flag=0;
				
			}
			else {
				System.out.println("some error occured in Adding user to GT");
			}
			
		}
	}
	
	if(flag==1)
	{
		
		String inserter="insert into basicinfo value(?,?)";
		PreparedStatement st1=con.prepareStatement(inserter);
		st1.setInt(1,count);
		st1.setString(2,name);
		int i=st1.executeUpdate();
		if(i==1)
		{
			System.out.println("User "+name+" added to GT "+" with Id "+count);
			flag=0;
			
		}
		else {
			System.out.println("some error occured in Adding user to GT");
		}
		
		
		
		
		
	}
	
	
	
	
	
	
	
	con.close();
	
	return count;
	
	
}
	
static void deleteUser(int id) throws SQLException
{
	
	
	
	Connection con=DriverManager.getConnection(loc,root,pass);
	
	
	
	String delQ="delete from basicinfo where Userid = ?";
	
	PreparedStatement st=con.prepareStatement(delQ);
	st.setInt(1,id);
	 int i=st.executeUpdate();
	if(i==1)
	{
		System.out.println(id +" is removed from the database");
	}
	
	
	con.close();
	
	
	
}



static void addMessageForUser(int from,int to,String msg) throws SQLException
{
	
	Connection con=DriverManager.getConnection(loc,root,pass);
	
	String addTask="insert into messages(Toid,Fromid,message,istaskdone,TaskId) value(?,?,?,?,?)";
	
	PreparedStatement st=con.prepareStatement(addTask);
	
	st.setInt(1,to);
	st.setInt(2,from);
	st.setString(3,msg);
	st.setInt(4,0);
	
	
	String selq="select * from messages where (Fromid=? and Toid=?)";
	
	PreparedStatement st2=con.prepareStatement(selq);
	
	st2.setInt(1,from);
	st2.setInt(2,to);
	
	ResultSet rs=st2.executeQuery();
	int curr_maxxer=0;
	
	while(rs.next())
	{
		curr_maxxer=rs.getInt(3);
	}
	
	st.setInt(5, curr_maxxer+1);
	
	
	
	
	int i=st.executeUpdate();
	if(i==1)
	{
		System.out.println("Task assigned by "+from + " to "+ to);
	}
	
	
	con.close();
	
	
}
	
static int istaskdone(int from,int to,int taskid) throws SQLException
{
	Connection con=DriverManager.getConnection(loc,root,pass);
	
	String query="select * from messages where Fromid=? and Toid=? and TaskId=?";
	
	PreparedStatement st=con.prepareStatement(query);
	st.setInt(1,from);
	st.setInt(2,to);
	st.setInt(3, taskid);
	
	ResultSet rs=st.executeQuery();
	
	rs.next();
	
	int ans=rs.getInt(5);
	
	con.close();
	
	return ans;
	
	
	
	
	
	
	
	
	
}
	

static void  deleteTaskOncompleteWithNotification(int from,int to,int taskid) throws SQLException
{
	int i=istaskdone(from, to, taskid);
	if(i==1) {
	
	
		Connection con=DriverManager.getConnection(loc,root,pass);
	
	String delQ="delete from messages where TaskId=? and Fromid=? and Toid=?";
	
	PreparedStatement st=con.prepareStatement(delQ);
	st.setInt(1,taskid);
	st.setInt(2,from);
	st.setInt(3,to);
	int p=st.executeUpdate();
	
	System.out.println(p+" rows affected");
	
	String Notification="insert into messages(Toid,Fromid,TaskId,Message,istaskdone) value(?,?,?,?,?)";
	
	st=con.prepareStatement(Notification);
	st.setInt(1,from);
	st.setInt(2,to);
	st.setInt(3,taskid);
	st.setString(4,"The task of id "+taskid+"given by you "+from +" to user "+to+" is completed");
	st.setInt(5,-1);
	p=st.executeUpdate();
	
	System.out.println("parent notified");
	
	con.close();
	
	
	
	}
	
	
	
	
	
	
	
	
	
	
	
}


static void MarkTaskDone(int from ,int to,int taskid) throws SQLException
{
	
	Connection con=DriverManager.getConnection(loc,root,pass);
	
	String setter="update messages set istaskdone=1 where Fromid=? and Toid=? and TaskId=?";
	
	PreparedStatement st=con.prepareStatement(setter);
	st.setInt(1,from);
	st.setInt(2,to);
	st.setInt(3,taskid);
	
	int i=st.executeUpdate();
	if(i==1)
	{
		System.out.println("Task marked as done!!!!");
	}
	else {
		System.out.println("Failed to mark as done Or may have already marked as done and Informed parent");
	}
	
	
	
	con.close();
	
	
	
	
	
	
}

static void PrintTasks(int id) throws SQLException
{
	Connection con=DriverManager.getConnection(loc,root,pass);
	
	
	String getTask="select * from messages where Toid ="+id;
	Statement st=con.createStatement();
	
	ResultSet rs=st.executeQuery(getTask);
	
	int flag=0;
	while(rs.next())
	{	flag=1;
		System.out.println(rs.getInt(2)+" gave task (Task-id="+rs.getInt(3)+") of --> /"+ rs.getString(4)+"/ with task status of "+rs.getInt(5));
		
		
	}
	if(flag==0)
	{
		System.out.println("No task for user with Id "+id);
	}
	
	con.close();
	
	
}

static void PrintTaskGivenToOthers(int byid) throws SQLException
{
	Connection con=DriverManager.getConnection(loc,root,pass);
	
	
	String getTask="select * from messages where Fromid ="+byid;
	Statement st=con.createStatement();
	
	ResultSet rs=st.executeQuery(getTask);
	
	
	while(rs.next())
	{
		System.out.println(rs.getInt(2)+" gave task (Task-id="+rs.getInt(3)+") to->"+rs.getInt(1) +" of --> "+ rs.getString(4));
		
		
	}
	
	con.close();
	
	
	
	
	
	
	
	
}

static void DeleteTask(int from,int to ,int taskid) throws SQLException
{
	
	Connection con=DriverManager.getConnection(loc,root,pass);
	
	String delQ="delete from messages where TaskId=? and Fromid=? and Toid=?";
	
	PreparedStatement st=con.prepareStatement(delQ);
	st.setInt(1,taskid);
	st.setInt(2,from);
	st.setInt(3,to);
	int p=st.executeUpdate();
	
	if(p==1)
	{
		System.out.println(to+" is now releaved from task with id "+taskid+" given by "+from);
	}
	else {
		System.out.println("Error in task Deletion/Already deleted..");
	}

	
	con.close();
	
	
	
	
	
	
}

static int isUserFree(int id) throws SQLException
{
	Connection con=DriverManager.getConnection(loc,root,pass);
	
	String q="select * from messages where Toid = "+id;
	
	Statement st=con.createStatement();
	ResultSet rs=st.executeQuery(q);
	
	int ans=1;
	if(rs.next())
	{
		ans= 0;
	}
	con.close();
	return ans;
	
	
	
}

static void printDonetasksForManager(int id) throws SQLException
{
	Connection con=DriverManager.getConnection(loc,root,pass);
	
	String que="select * from messages where Toid=? and istaskdone=?";
	
	PreparedStatement st=con.prepareStatement(que);
	st.setInt(1,id);
	st.setInt(2,-1);
	
	ResultSet rs=st.executeQuery();
	
	while(rs.next())
	{
		System.out.println("From--> "+rs.getInt(2)+" To--> "+rs.getInt(1)+" with Task-id --> "+rs.getInt(3)+" As --> "+rs.getString(4));
	}
	
	
	
	
	
	con.close();
	
	
	
	
}

// can only acces before notification to manager...
static void PrintDoneByUser(int id) throws SQLException
{
	
	Connection con=DriverManager.getConnection(loc,root,pass);
	
	String que="select * from messages where Toid=? and istaskdone=?";
	
	PreparedStatement st=con.prepareStatement(que);
	st.setInt(1,id);
	st.setInt(2,1);
	
	ResultSet rs=st.executeQuery();
	
	while(rs.next())
	{
		System.out.println("From--> "+rs.getInt(2)+" To--> "+rs.getInt(1)+" with Task-id --> "+rs.getInt(3)+" As --> "+rs.getString(4));
	}
	
	
	
	
	
	con.close();
	
}

static void passTaskInGraph(int from,int to,int secto,int task_id) throws SQLException
{
	Connection con=DriverManager.getConnection(loc,root,pass);
	
	String que="select * from messages where Fromid=? and Toid=? and TaskId=?";
	
	PreparedStatement st=con.prepareStatement(que);
	st.setInt(1,from);
	st.setInt(2,to);
	st.setInt(3,task_id);
	
	ResultSet rs=st.executeQuery();
	
	if(rs.next())
	{
		String mess=rs.getString(4);
		DeleteTask(from,to,task_id);
		
		String newadder="insert into messages value(?,?,?,?,?)";
		st=con.prepareStatement(newadder);
		st.setInt(1,secto);
		st.setInt(2,from);
		st.setInt(3,task_id);
		st.setString(4,mess);
		st.setInt(5,2);
		int k=st.executeUpdate();
		
		if(k==1)
		{System.out.println("Outsource Successfull!!  to user "+secto+" the task given by "+ from+ " with task id as "+task_id);
		}
	}
	else {
		System.out.println("No task to forward");
	}
	con.close();
	
}

static void SaveSessionGraph(graph g) throws SQLException
{
	
	Connection con=DriverManager.getConnection(loc,root,pass);
	
	String deleter="delete from edges";
	String deleter2="delete from graphparam";
	
	Statement st=con.createStatement();
	int l=st.executeUpdate(deleter);
	l=st.executeUpdate(deleter2);
	
	
	
	String inserter_edge="insert into edges value(?,?)";
	
	PreparedStatement st2=con.prepareStatement(inserter_edge);
	
	for(Map.Entry<Integer,ArrayList<Integer>> ent:g.network.entrySet())
	{
		int a=ent.getKey();
		ArrayList<Integer> temp=ent.getValue();
		for(int i=0;i<temp.size();i++)
		{
			st2.setInt(1,a);
			st2.setInt(2,temp.get(i));
			int p=st2.executeUpdate();
		}
		
		
		
	}
	
	String setParam="insert into graphparam value("+g.CurrUsers +","+g.maxIdTillNow +")";
	
	int a=st.executeUpdate(setParam);
	
	
	
	
	
	con.close();
	
	System.out.println("Graph saved ");
	
	
}


static void addCustomUser(int id,String name) throws SQLException
{
	Connection con=DriverManager.getConnection(loc,root,pass);
	
	String que="insert into basicinfo value(?,?)";
	PreparedStatement st=con.prepareStatement(que);
	
	st.setInt(1,id);
	st.setString(2,name);
	int i=st.executeUpdate();
	if(i==1)
	{
		System.out.println("custom entry successfull");
	}
	
	con.close();
}


static graph getSession() throws SQLException
{
	graph g=new graph();
	
	int maxtill=-1;
	int currUser=-1;
	
	
	Connection con=DriverManager.getConnection(loc,root,pass);
	
	Statement st=con.createStatement();
	
	/*String que="select * from graphparam";
	
	ResultSet rs=st.executeQuery(que);
	
	if(rs.next())
	{
		maxtill=rs.getInt(2);
		currUser=rs.getInt(1);
	}
	
	if(maxtill==-1 && currUser==-1)
	{
		return null;
	}
	g.CurrUsers=currUser;
	g.maxIdTillNow=maxtill;*/
	
	String que="select * from basicinfo";
	
	ResultSet rs=st.executeQuery(que);
	
	while(rs.next())
	{
		g.addUser(rs.getInt(1));
	}
	
	//now add edges...
	
	que="select * from edges";
	
	rs=st.executeQuery(que);
	
	while(rs.next())
	{
		int src=rs.getInt(1);
		int dest=rs.getInt(2);
		g.addEdge(src, dest);
	}
	
	con.close();
	
	
	System.out.println("GRAPH RESTORED");
	
	
	
	return g;
	
	
	
	
	
	
	
	
}
	
static int PrintIdForName(String name) throws SQLException
{
	
	
	Connection con=DriverManager.getConnection(loc,root,pass);
	
	String que="select * from basicinfo where Name="+name;
	
	Statement st=con.createStatement();
	ResultSet rs=st.executeQuery(que);
	int ans=-1;
	if(rs.next())
	{	ans=rs.getInt(1);
		System.out.println(rs.getInt(1)+" is id for name "+name);
	}
	else {
		System.out.println("Name not found");
	}
	
	con.close();
	return ans;
	
	
}
static void PrintTask(int from,int to,int task_id) throws SQLException
{
	Connection con=DriverManager.getConnection(loc,root,pass);
	
	String que="select * from messages where Fromid=? and Toid=? and TaskId=?";
	
	PreparedStatement st=con.prepareStatement(que);
	st.setInt(3, task_id);
	st.setInt(2,to);
	st.setInt(1,from);
	ResultSet rs=st.executeQuery();
	if(rs.next())
	{
		System.out.format("%d gave task to user(%d) with Task-id(%d) with message as /%s/ with taskStatus(current) as %d ",from,to,task_id,rs.getString(4),rs.getInt(5));
	}
	
	
	
	con.close();
	
	
	
	
	
}



static void GetOutsourcesTaskForManger(int id) throws SQLException
{
	Connection con=DriverManager.getConnection(loc,root,pass);
	
	String que="select * from messages where Fromid=? and istaskdone=?";
	PreparedStatement st=con.prepareStatement(que);
	st.setInt(1, id);
	st.setInt(2,2);// we have chose 2 as taskstatus for outsourcing...
	
	ResultSet rs=st.executeQuery();
	while(rs.next())
	{
		PrintTask(rs.getInt(2),rs.getInt(1),rs.getInt(3));
	
	}
	
	System.out.println();
	
	
	
	
	con.close();
	
	
	
	
}


static void ClearLog(int id) throws SQLException
{

	Connection con=DriverManager.getConnection(loc,root,pass);
	
	String que="delete from messages where Toid=? and istaskdone=-1";
	
	PreparedStatement st=con.prepareStatement(que);
	
	st.setInt(1,id);
	
	int r=st.executeUpdate();
	if(r>=0)
	{
		System.out.println("Logs were cleaned successfully");
	}
	else {
		System.out.println("Some error Occured ");
	}
	
	
	
	
	con.close();
	
	
	
	
	
}

static void MarkDoneAsNotDone(int from,int to,int taskID) throws SQLException
{
	Connection con=DriverManager.getConnection(loc,root,pass);
	
	int checker=istaskdone(from, to, taskID);
	if(checker==1)
	{
		String updater="update messages set istaskdone=0 where Fromid=? and Toid=? and TaskId=?";
		
		PreparedStatement st=con.prepareStatement(updater);
		st.setInt(1,from);
		st.setInt(2,to);
		st.setInt(3,taskID);
		int r=st.executeUpdate();
		if(r==1)
		{
			System.out.println("Marked as Not Done !");
		}
		else {
			System.out.println("Some error occured/failed to mark as Not Done");
		}
	}
	
	con.close();
	
	
}

	
}
