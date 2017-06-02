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
        + "1. Insert\n"
        + "2. Insert a patient meals \n"
        + "3. Insert a patient doctor\n"
        + "4. Insert a patient visitors\n"
        + "5. Show all patients\n"
        + "6. Delete a patient \n"
        + "7. Search for a patient \n"
        + "8. Change a patient room \n"
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
        	System.out.println("Write the illness for what the patient is going to be treated: ");
			String ill=consola.readLine();
			j.assignPatientIllness(p.getId(),ill);
			System.out.println("Any chronic illness?[Y/N]");
			if(consola.readLine().equals("y")){
				System.out.println("Write patient chronic illness: ");
				String chro=consola.readLine();
				j.assignPatientChronic(p.getId(),chro);
			}
			break;
        case 2:
        	System.out.println("Coose a food and a patient: ");
			System.out.println(j.selectF());
			System.out.println(j.selectPid());
			int id2,id11;
			String st;
			boolean booleano=true;
			do{
				System.out.println("Enter a patient id: ");
				id2=Integer.parseInt(consola.readLine());
				System.out.println("Enter a food id: ");
				id11=Integer.parseInt(consola.readLine());
				System.out.println("Enter the date when the meal is going to be delivered:");
				st=consola.readLine();
				try{
					booleano=j.assignPatientFood(id2, id11,st);
				}catch(SQLException sq){
					System.out.println("You first need to indicate if the patient has any chronic illness");
				}
				if(booleano==false){
					System.out.println("This patient cannot eat this food due to his chronic illness");
				}
			}while(booleano==false);
        	break;
        case 3:
        	System.out.println("Enter the patient id: ");
        	System.out.println(j.selectPid());
        	int id=Integer.parseInt(consola.readLine());
        	System.out.println("Enter its doctor name: ");
        	String nam=consola.readLine();
        	System.out.println("Enter its doctor surname: ");
        	j.assignPatientDoctor(id, nam, consola.readLine());
        	break;
        case 4:
        	System.out.println("Choose a patient: ");
        	System.out.println(j.selectPid());
        	System.out.println("Enter its id: ");
        	int idp=Integer.parseInt(consola.readLine());
        	System.out.println("1- Insert a new visitor\n2- Choose a visitor");
        	switch(Integer.parseInt(consola.readLine())){
        		case 1:
        			System.out.println("Visitor name:");
        			String name=consola.readLine();
        			System.out.println("Visitor surname: ");
        			String sur=consola.readLine();
        			Visitor v=new Visitor(name,sur);
        			j.insertVis(v);
        			j.assignPatientVisitor(idp,j.obtainVisitorid(v));
        			break;
        		case 2:
                	System.out.println(j.selectV());
                	System.out.println("Enter the visitor id: ");
                	int id2111=Integer.parseInt(consola.readLine());
                	j.assignPatientVisitor(idp, id2111);
                	break;
        	}
        	break;
        case 5:
			System.out.println(j.selectP());
        	break;
        case 6:
        	System.out.println("Enter the id of the patient to be deleted:");
        	System.out.println(j.selectPid());
        	j.deletePatient(Integer.parseInt(consola.readLine()));
        	break;
        case 7:
        	System.out.println("Enter a room number: ");
        	int r=Integer.parseInt(consola.readLine());
        	Patient p1=j.searchPatient(r);
        	System.out.println("The patient in room "+r+" is:\n"+p1);
        	break;
        case 8:
        	System.out.println("Enter the id of the patient to be changed of room: ");
        	System.out.println(j.selectP());
        	int id1=Integer.parseInt(consola.readLine());
        	System.out.println("Enter its new room");
        	int r1=Integer.parseInt(consola.readLine());
        	j.updatePatient(id1, r1);
        	break;
    }
}
}

