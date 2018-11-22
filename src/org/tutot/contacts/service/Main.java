package org.tutot.contacts.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Main {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		InputStream stream = Main.class.getClassLoader().getResourceAsStream("resources/personnes.csv");
		System.out.println(">>> "+stream);
		BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
		String line = null;
		while((line = reader.readLine()) != null){
			System.out.println(line);
		}
		

	}

}
