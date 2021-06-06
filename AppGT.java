package com.chatter;

import java.util.Scanner;

public class AppGT {
	
	public static void printInfo() {
		
		System.out.println(" To Get Below Information set again Enter 0");
		
		System.out.println("Category = User/Grapher- Manupulation/Printing:--\n");
		System.out.println("\t1> AddNewUserToGrapher");
		System.out.println("\t2> DelteExistingUserFromGrapher");
		System.out.println("\t3> MakeAConnection");
		System.out.println("\t4> DeleteAConnection");
		System.out.println("\t5> PathInGrapher");
		System.out.println("\t6> PrintCurrentGrapherState");
		System.out.println("\t7> AddCustomUser");
		System.out.println("\t-3> GetIdForName");
		
		System.out.println("\nCategory = Task(Assignment/Updation):--\n");
		System.out.println("\t8>  PostTask");
		System.out.println("\t9>  PassTaskFurther");
		System.out.println("\t10> MarkATaskAsDone");
		System.out.println("\t11> NotifyTaskerForCompletion");
		System.out.println("\t12> UnAssignTask");
		System.out.println("\t-2> MarkDoneTaskAsNotDone");
		
		
		System.out.println("\nCategory = Task(Information/status):--\n");
		System.out.println("\t13> PrintYourTodoTasks");
		System.out.println("\t14> CheckTaskStatus");
		System.out.println("\t15> PrintYourDoneTasks");
		System.out.println("\t16> PrintAllFreePasserForPassingTask");
		System.out.println("\t17> PrintTasksGivenToOther");
		System.out.println("\t18> ShowNotificationForTasker");
		System.out.println("\t19> DelteTheNotificationForTasker");
	
		
		
		System.out.println("-1>  QuitFromGraphTasker\n");
		
	}
	
	
	public static void  main(String[] args) {
		
		System.out.println("Welcome in GraphTasker!!!!\n");
		System.out.println("*************");
		System.out.println("*  _____  ______ *");
		System.out.println("* |         |    *");
		System.out.println("* |  __     |    *");
		System.out.println("* |___|     |    *");
		System.out.println("*                *");
		System.out.println("***************");
		
		
		Scanner sc=new Scanner(System.in);
		
		//int a=sc.nextInt();
		int flag=1;
		
		// callonetime printinfo..
		printInfo();
		GraphTasker controller=new GraphTasker(1);
		
		while(flag==1)
		{	
			
			System.out.println("Enter Your Choice :) ");
			int choice=sc.nextInt();
			
			switch (choice) {
			case -1:
				flag=0;
				break;
			
			case 1:
				String name;
				System.out.println("Enter name of the user -");
				name=sc.next();
				controller.adduser(name);
				break;
				
			case 2:
				int id;
				System.out.println("Enter id for deletetion -");
				id=sc.nextInt();
				controller.deleteuser(id);
				break;
			case 3:
				int src;int dest;
				System.out.println("Enter Parent<space> child-");
				src=sc.nextInt();
				dest=sc.nextInt();
				controller.Addconnection(src,dest);
				break;
			
			case 4:
				int src2;int dest2;
				System.out.println("Enter Parent<space> child-");
				src2=sc.nextInt();
				dest2=sc.nextInt();
				controller.RemoveConnection(src2,dest2);
				break;
				
			case 5:
				int src3,dest3;
				System.out.println("Enter Parent<space> child-");
				src3=sc.nextInt();
				dest3=sc.nextInt();
				controller.levelOfTask(src3, dest3);
				break;
			
			case 6:
				controller.PrintGraph();
				break;
			
			case 7:
				int id1;String namee;
				System.out.println("Enter Cutom entry's id<space>name");
				id1=sc.nextInt();
				namee=sc.next();
				controller.AddUserWithId(id1, namee);
				break;
			
			case 8:
				int from;int to;String task;
				System.out.println("Enter from<space>to<space>Task");
				from=sc.nextInt();
				to=sc.nextInt();
				task=sc.nextLine();
				controller.GiveTask(from, to,task);
				break;
				
			case 9:
				int from1,to1,to2,taskid;
				System.out.println("Enter from<space>AllotedTo<space>outsourcedId<space>taskid");
				from1=sc.nextInt();
				to1=sc.nextInt();
				to2=sc.nextInt();
				taskid=sc.nextInt();
				controller.OutSourceTask(from1, to1, to2, taskid);
				break;
			
			case 10:
				int from2,to21,taskid2;
				System.out.println("Enter from<space>to<space>taskId");
				from2=sc.nextInt();
				to21=sc.nextInt();
				taskid2=sc.nextInt();
				controller.MarksAsComplete(from2, to21, taskid2);
				break;
			
			case 11:
				int fromnoti,tonoti,taskidnoti;
				System.out.println("Enter from<space>to<space>taskId");
				fromnoti=sc.nextInt();
				tonoti=sc.nextInt();
				taskidnoti=sc.nextInt();
				controller.MarkDoneTaskWithNotification(fromnoti, tonoti, taskidnoti);
				break;
			
			case 12:
				int fromdel,todel,taskiddel;
				System.out.println("Enter from<space>to<space>taskId");
				fromdel=sc.nextInt();
				todel=sc.nextInt();
				taskiddel=sc.nextInt();
				controller.releaveFromTask(fromdel, todel, taskiddel);
				break;
				
			
			case 13:
				int myid;
				System.out.println("Enter Id");
				myid=sc.nextInt();
				controller.Mytasks(myid);
				break;
				
			case 14:
				int fromt,tot,taskt;
				System.out.println("Enter from<space>to<space>taskId");
				fromt=sc.nextInt();
				tot=sc.nextInt();
				taskt=sc.nextInt();
				controller.IsTaskDone(fromt,tot,taskt);
				break;
				
			case 15:
				System.out.println("Enter Id");
				int eid=sc.nextInt();
				controller.showDoneTasksByUsers(eid);
				break;
				
			
			case 16:
				int idp;
				System.out.println("Enter Id");
				idp=sc.nextInt();
				controller.PrintAvailablePassers(idp);// modify the passer function to do the bfs on graph..
				break;
				
				
			
			case 17:
				System.out.println("Enter Id");
				int ido=sc.nextInt();
				controller.TasksGivenToOthers(ido);
				//controller.ShowAssignedCompletedTasks(ido);
				break;
				
			case 18:
				System.out.println("Enter id");
				int ide=sc.nextInt();
				controller.ShowAssignedCompletedTasks(ide);
				break;
				
			case 19:
				System.out.println("Enter the Id");
				int y=sc.nextInt();
				controller.ClearLogForUser(y);
				break;
				
			case -2:
				System.out.println("Enter from<space>to<space>task_id>");
				int afrom=sc.nextInt();
				int ato=sc.nextInt();
				int atsk=sc.nextInt();
				controller.UncheckATask(afrom, ato, atsk);
				break;
				
			
				
			case -3:
				System.out.println("ENTER <double_apostroph>Name<double_apostroph> TO GET ID");
				String namer=sc.next();
				controller.ShowIDForName(namer);
				break;
			case 0:
				printInfo();
				break;
				
			default:
				System.out.println("No matching choice :( ");;
			}
			
			
			
			
			
			
			
			
		}
		
		sc.close();
		System.out.println("Quit successfull !! Bye Bye");
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		

	}

}
