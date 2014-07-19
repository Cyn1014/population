package com.bigdata.servlet;

import java.util.Scanner;

public class Testing {

	private Scanner scaner;

	public Testing() {
		// TODO Auto-generated method stub
		System.out.println("enter something:");
		scaner = new Scanner(System.in);
		String a=scaner.next();
		System.out.println(a);

	}

}
