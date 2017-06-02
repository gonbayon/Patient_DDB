package Interface;

import java.io.*;
import java.sql.SQLException;

import sample.db.patient.JDBC;
import sample.db.pojos.*;

public class MenuMedic{
	public void menu() throws IOException {
        System.out.println(""
            + "1. Insert a medicine \n"
            + "2. Show all madicines\n"
            + "3. Delete a medicine \n"
            + "4. Search for a medicine \n"
            + "Introduce an option:");
    }
	public void menuMedicine(JDBC j) throws IOException, SQLException {
		MenuMedic menu=new MenuMedic();
		Medication m=null;
        BufferedReader consola = new BufferedReader(new InputStreamReader(System.in));
        int opcion =0;
        menu.menu();
        opcion=Integer.parseInt(consola.readLine());
        switch (opcion) {
            case 1:
            	String nam,ag;
            	System.out.println("Insert the medicine name: ");
            	nam=consola.readLine();
            	System.out.println("Insert tha medicine active agent:");
            	ag=consola.readLine();
            	m=new Medication(nam, ag);
            	j.insertMed(m);
                break;
            case 2:
            	System.out.println(j.selectM());
                break;
            case 3:
            	System.out.println("Choose for a medicine id:");
            	System.out.println(j.selectM());
            	System.out.println("Enter the id from the medicine to be deleted: ");
            	int id=Integer.parseInt(consola.readLine());
            	j.deleteMedicat(id);
            	break;
            case 4:
            	System.out.println("Enter the drug name: ");
            	m=j.searchMed(consola.readLine());
            	System.out.println(m);
            	break;

        }
	}
}

