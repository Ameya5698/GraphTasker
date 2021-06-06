package com.chatter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;



//NOTE(RUN ONLY ONCE--> IT will create a create and needed thing for GT to RUN...
// Run only once for creattion of DATABASE....
public class DbInit {

	public static void main(String[] args) throws SQLException {
		String loc="jdbc:mysql://localhost:3306";
		String root=PassDBdata.id;
		String pass=PassDBdata.pass;
		
		
		
		
		Connection con=DriverManager.getConnection(loc,root,pass);
		
		
		String cre="create database dbgt";
		Statement st=con.createStatement();
		int r=st.executeUpdate(cre);
		System.out.println(r);
		
		// create basicinfo table in mysql..
		
		String sele="use  dbgt";
		st.executeQuery(sele);
		
		
		
		String basicinnn="create table BasicInfo(\r\n"
				+ "Userid INT PRIMARY key,\r\n"
				+ "Name Varchar(30) \r\n"
				+ ");";
		
		
		r=st.executeUpdate(basicinnn);
		System.out.println(r);
		
		String mess="create table Messages(\r\n"
				+ "\r\n"
				+ "Toid Int ,\r\n"
				+ "Fromid INT ,\r\n"
				+ "TaskId int,\r\n"
				+ "Message Varchar(6000),\r\n"
				+ "istaskdone INT,\r\n"
				+ "foreign key(Fromid) references basicinfo(Userid) on delete cascade,\r\n"
				+ "foreign key(Toid) references basicinfo(Userid) on delete cascade\r\n"
				+ ");";
		
		r=st.executeUpdate(mess);
		System.out.println(r);
		
		String edges="create table edges(\r\n"
				+ "src INT,\r\n"
				+ "dest INT,\r\n"
				+ "foreign key(src) references basicinfo(UserId) on delete cascade,\r\n"
				+ "foreign key(dest) references basicinfo(UserId) on delete cascade\r\n"
				+ ");";
		
		r=st.executeUpdate(edges);
		System.out.println(r);
		
		String graphParam="create table graphparam(curr int, maxtill int );";
		
		r=st.executeUpdate(graphParam);
		System.out.println(r);
		
		
		con.close();
		
		
	}

}
