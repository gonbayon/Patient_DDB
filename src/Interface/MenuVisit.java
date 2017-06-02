package Interface;

import java.io.*;
import java.sql.SQLException;
import java.util.List;

import sample.db.patient.JDBC;
import sample.db.pojos.*;


public class MenuVisit {
	public void menu() throws IOException {
        System.out.println(""
            + "1. Insert a visitor \n"
            + "2. Show all the visitors of a patient\n"
            + "3. Delete a visitor \n"
            + "4. Search \n"
            + "5. Insert the date when the visitor will come\n"
            + "Introduce an option:");
    }
	public void menuVisit (JDBC j) throws IOException, SQLException {
		MenuVisit menu=new MenuVisit();
		Visitor v=null;
        BufferedReader consola = new BufferedReader(new InputStreamReader(System.in));
        int opcion =0;
        menu.menu();
        opcion=Integer.parseInt(consola.readLine());
        switch (opcion) {
            case 1:
            	System.out.println("Visitor name:");
    			String name=consola.readLine();
    			System.out.println("Visitor surname: ");
    			String sur=consola.readLine();
    			v=new Visitor(name,sur);
    			j.insertVis(v);
                break;
            case 2:
            	System.out.println(j.selectP());
            	System.out.println("Enter the patient id to see all his visitors: ");
            	int id=Integer.parseInt(consola.readLine());
            	System.out.println(j.getVisPatient(id));
                break;
            case 3:
            	System.out.println("Delete by name or id?");
            	switch(consola.readLine().toLowerCase()){
            	case "name":
            		System.out.println("Introduce the visitor name: ");
            		String nm1=consola.readLine();
            		System.out.println("Introduce the visitor surname: ");
            		String sur1=consola.readLine();
            		j.deleteVisitorbyName(nm1, sur1);
            		break;
            	case "id":
            		System.out.println(j.selectV());
            		System.out.println("Introduce the visitor id: ");
            		j.deleteVisitorbyId(Integer.parseInt(consola.readLine()));
            		break;
            	}
            	break;
            case 4:
            	System.out.println("Introduce the visitor name: ");
        		String nm11=consola.readLine();
        		System.out.println("Introduce the visitor surname: ");
        		String sur11=consola.readLine();
        		System.out.println(j.searchVis(nm11, sur11));
            	break;
            case 5:
            	System.out.println("Choose a visitor and a time available: ");
            	System.out.println(j.selectV());
            	System.out.println(j.selectSh());
            	System.out.println("Enter the visitoe id: ");
            	int id1=Integer.parseInt(consola.readLine());
            	System.out.println("Enter the schedule id: ");
            	int id2=Integer.parseInt(consola.readLine());
            	j.assignVisitorSchedule(id1, id2);
            	break;            	
	}
}
}