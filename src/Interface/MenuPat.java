package Interface;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

import	sample.db.patient.*;
import sample.db.pojos.*;

public class MenuPat {
public MenuPat(){
	
}
public void menu() throws IOException {
    System.out.println(""
        + "1. Insert  \n"
        + "2. Insert a patient meals \n"
        + "3. Show all patients\n"
        + "4. Delete a patient \n"
        + "5. Search for a patient \n"
        + "6. Change a patient room \n"
        + "7. Drop patient table\n"
        + "Introduce an option:");
}
public void menupatient(JDBC j) throws IOException, SQLException{
	MenuPat menu=new MenuPat();
	Patient p=null;
    BufferedReader consola = new BufferedReader(new InputStreamReader(System.in));
    
    menu.menu();
    int opcion=Integer.parseInt(consola.readLine());
    switch (opcion) {
        case 1:
        	String n,s1;
        	int rn;
        	System.out.println("Name:");
        	n=consola.readLine();
        	System.out.println("Surname:");
        	s1=consola.readLine();
        	System.out.println("Room number:");
        	rn=Integer.parseInt(consola.readLine());
        	p=new Patient(n, s1, rn);
        	j.insertPatient(p);
			break;
        case 2:
        	System.out.println("Enter the patient id and food id: ");
			System.out.println(j.selectF());
			System.out.println(j.selectP());
			int id2=Integer.parseInt(consola.readLine());
			int id11=Integer.parseInt(consola.readLine());
			System.out.println("Enter the date when the meal is going to be delivered:");
			String st=consola.readLine();
			j.assignPatientFood(id2, id11,st);
        	break;
        case 3:
			System.out.println(j.selectP());
        	break;
        case 4:
        	System.out.println("Enter the id of the patient to be deleted:");
        	System.out.println(j.selectP());
        	j.deletePatient(Integer.parseInt(consola.readLine()));
        	break;
        case 5:
        	System.out.println("Enter a room number: ");
        	int r=Integer.parseInt(consola.readLine());
        	Patient p1=j.searchPatient(r);
        	System.out.println("The patient in room "+r+" is:\n"+p1);
        	break;
        case 6:
        	System.out.println("Enter the id of the patient to be changed of room: ");
        	System.out.println(j.selectP());
        	int id=Integer.parseInt(consola.readLine());
        	System.out.println("Enter its new room");
        	int r1=Integer.parseInt(consola.readLine());
        	j.updatePatient(id, r1);
        	break;
        case 7:
        	j.dropTableP();
        	break;
    }
}
}

