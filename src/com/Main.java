package com;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;  
import org.hibernate.SessionFactory;  
import org.hibernate.Transaction;  
import org.hibernate.cfg.Configuration;  
import org.hibernate.criterion.Restrictions;

public class Main {  
	static Scanner s= new Scanner(System.in);

	public static void main(String[] args) {
		int choice=0,id=0;
		boolean repeatOn=true;

		while(repeatOn){

			System.out.println("choice of actions  : 1-Insert ,2-Fetch, 3-Update , 4-Delete , 5-HQL");
			choice = s.nextInt();

		switch(choice){
		case 1: addStudent();
				break;
		case 2: System.out.println("Enter id to display Student Details");
				id = s.nextInt();
				getStudent(id);
				break;
		case 3: System.out.println("Enter id to update Student Details");
				id = s.nextInt();
				updateStudent(id);
				break;
		case 4: System.out.println("Enter id to delete Student Details");
				id = s.nextInt();
				removeStudent(id);
				break;
		case 5 : invokeHQL();
				break;
		case 6: question();
				break;
		default: System.out.println("Exiting");
				repeatOn=false;
		}
		}
	}
	
	public static void question(){
		SessionFactory sessionfactory =  new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
		Session session = sessionfactory.openSession();
		try{
		Transaction tnx = session.beginTransaction();
		Questions q1=new Questions(); 
		q1.setEmail("prohidekar99@gmail.com");
		q1.setFatherName("Anoop");
		q1.setMotherName("Swathi");
		session.save(q1);
		tnx.commit();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		finally{
			session.close();
			System.out.println("Successfully saved");
		}
	}
	
	private static void invokeHQL() {
		SessionFactory sessionfactory =  new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
		Session session = sessionfactory.openSession();
		try{
		Query q= session.createQuery("from Student where name like '%S%'"); // HQL Queries
		Query q1= session.createQuery("select min(id) from Student");
		List<Student> students = (List<Student>)q.list();
		for (Student temp : students) {
			System.out.println(temp.getName()+":"+temp.getPhoneNumber());
		}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		finally{
			session.close();
			System.out.println("Successfully Retrieved.");
		}
	}
		
	public static void addStudent(){
		SessionFactory sessionfactory =  new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
		Session session = sessionfactory.openSession();
		try{
		Transaction tnx = session.beginTransaction();
		Student s1=new Student(); 
		s1.setName("Bhavya Sruthi");
		s1.setPhoneNumber("9901238391");
		session.save(s1);
		tnx.commit();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		finally{
			session.close();
			System.out.println("Successfully saved");
		}
	}

	public static void getStudent(int id){
		SessionFactory sessionfactory =  new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
		Session session = sessionfactory.openSession();

		try{
		Transaction tnx = session.beginTransaction();
		Student stud = (Student) session.get(Student.class, id);
		if(stud == null)
			System.out.println("No Student exists with such Id");
		else{
		System.out.println("Student Details are: ");
		System.out.println("Name - " + emp.getName());
		System.out.println("Phone Number - " + emp.getPhoneNumber());
		tnx.commit();
		}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		finally{
			session.close();
			System.out.println("Successfully Retrieved");
		}
	}
	public static void updateStudent(int id){
		SessionFactory sessionfactory =  new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
		Session session = sessionfactory.openSession();
		try{
		Transaction tnx = session.beginTransaction();
		System.out.println("Enter Update Detail");
		s.nextLine();
		String newphonenumber = s.nextLine();
		Student stud = (Student) session.get(Student.class, id);
		if(emp == null)
			System.out.println("No Student exists with such Id");
		else{
		emp.setPhoneNumber(newphonenumber);
		session.update(stud);
		System.out.println("Updated student Details are: ");
		System.out.println("Name - " + emp.getName());
		System.out.println("Phone Number - " + emp.getPhoneNumber());
		tnx.commit();
		}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		finally{
			session.close();
			System.out.println("Successfully Updated");
		}
	}
	public static void removeStudent(int id){
		SessionFactory sessionfactory =  new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
		Session session = sessionfactory.openSession();
		try{
		Transaction tnx = session.beginTransaction();
		Student stud = (Student) session.get(Student.class, id);
		if(emp == null)
			System.out.println("No Student exists with such Id");
		else{
		session.delete(stud);
		tnx.commit();
		}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		finally{
			session.close();
			System.out.println("Successfully Deleted");
		}
	}
	}
