package com.entities;

public class Student{
	private int id;
	private String name;
	private int age;
	private int cource;

	public Student(){}

	public void setId(int id){
		this.id=id;
	}
	public void setName(String name){
		this.name=name;
	}
	public void setAge(int age){
		this.age=age;
	}
	public void setCource(int cource){
		this.cource=cource;
	}

	public int getId(){
		return this.id;
	}

	public String getName(){
		return this.name;
	}
	public int getAge(){
		return this.age;
	}
	public int getCource(){
		return this.cource;
	}

	@Override 
	public String toString(){
		return "Student  = "+id+ " "+ name+ " "+ age+ " "+" "+ cource; 
	}
}
