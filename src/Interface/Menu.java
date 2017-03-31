package Interface;

import java.io.*;
import java.sql.SQLException;

import sample.db.patient.JDBC;
import sample.db.pojos.Patient;

public class Menu {
	public void menu() throws IOException {
        System.out.println(""
            + "1. Connect \n"
            + "2. Create table\n"
            + "3. Insert a patient\n"
            + "4. Show all patients\n"
            + "5. Delete a patient\n"
            + "6. Search for a patient\n"
            + "7. Update a patient\n"
            + "8. Drop\n"
            + "9. Disconnect\n"
            + "0. Salir\n\n"
            + "Introduzca una opcion:");
    }
	public static void main(String[] args) throws IOException, SQLException {
		Menu menu=new Menu();
		Patient p=null;
		JDBC j=null;
        BufferedReader consola = new BufferedReader(new InputStreamReader(System.in));
        int opcion =0;
              do{
        menu.menu();
        opcion=Integer.parseInt(consola.readLine());
        switch (opcion) {
            case 1:
                System.out.println("Name of DDB to connect");
                j=new JDBC(consola.readLine());
                j.connect();
                break;
            case 2:
            	try{
                j.createTable();
            	}catch(NullPointerException np){
            		System.out.println("You first need to connect to a DDB");
            	}
                break;
            case 3:
            	try{
            		try{
            	String n,s;
            	int rn;
            	System.out.println("Name:");
            	n=consola.readLine();
            	System.out.println("Surname:");
            	s=consola.readLine();
            	System.out.println("Number:");
            	rn=Integer.parseInt(consola.readLine());
            	p=new Patient(n,s,rn);
            	j.insertPatient(p);
            		}catch(SQLException sq){
            			System.out.println("You first need to create a table");
            		}
            	}catch(NullPointerException np){
            		System.out.println("You first need to connect to a DDB");
            	}
            	break;
            case 4:
            	try{
            		try{
            			System.out.println(j.select());
            		}catch(SQLException sq){
            			System.out.println("You first need to create a table");
            		}
            	}catch(NullPointerException np){
            		System.out.println("You first need to connect to a DDB");
            	}
            	break;
            case 5:
            	try{
            		try{
            			System.out.println(j.select());
            			System.out.println("Enters the id from the patient you want to delete: ");
            	int id=Integer.parseInt(consola.readLine());
            	j.deletePatient(id);
            		}catch(SQLException sq){
            			System.out.println("You first need to create a table");
            		}
            	}catch(NullPointerException np){
            		System.out.println("You first need to connect to a DDB");
            	}
            	break;
            case 6:
            	try{
            		try{
            			System.out.println("Enter the room number from the patient to be shown");
            			int n=Integer.parseInt(consola.readLine());
            			Patient p1=j.searchPatient(n);
            			System.out.println(p1);
            		}catch(SQLException sq){
            			System.out.println("You first need to create a table");
            		}
            	}catch(NullPointerException np){
            		System.out.println("You first need to connect to a DDB");
            	}
            	break;
            case 7:
            	try{
            		try{
            			System.out.println("Enter the id of the patient to be changed of room");
            			int id=Integer.parseInt(consola.readLine());
            			System.out.println("Enter its new room number");
            			int room=Integer.parseInt(consola.readLine());
            			j.updatePatient(id, room);
            		}catch(SQLException sq){
            			System.out.println("You first need to create a table");
            		}
            	}catch(NullPointerException np){
            		System.out.println("You first need to connect to a DDB");
            	}
            	break;
            case 8:
            	try{
            		try{
            			j.dropTable();
            		}catch(SQLException sq){
            			System.out.println("You first need to create a table");
            		}
            	}catch(NullPointerException np){
            		System.out.println("You first need to connect to a DDB");
            	}
            	break;
            case 9:
            	try{
            	j.disconnect();
            	}catch(NullPointerException np){
            		System.out.println("You first need to connect to a DDB");
            	}
            	break;
            case 0:
            	System.exit(0);
            	break;
        }
}while(true);
	}
}

