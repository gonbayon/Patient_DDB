package Interface;

import java.io.*;
import java.sql.SQLException;

import sample.db.patient.JDBC;
import sample.db.pojos.*;


public class MenuChronic {
	public void menu() throws IOException {
        System.out.println(""
            + "1. Insert a chronic illness \n"
            + "2. Show all the chronic illnesses list\n"
            + "3. Insert the medication for an illness\n"
            + "4. Delete a chronic illness \n"
            + "5. Search for a chronic illness\n"
            + "Introduce an option:");
    }
	public void menuChronic(JDBC j) throws IOException, SQLException {
		MenuChronic menu=new MenuChronic();
		Chronic ch=null;
        BufferedReader consola = new BufferedReader(new InputStreamReader(System.in));
        int opcion =0;
        menu.menu();
        opcion=Integer.parseInt(consola.readLine());
        switch (opcion) {
            case 1:
            	System.out.println("Enter the illness name: ");
            	ch=new Chronic(consola.readLine());
            	j.insertChronic(ch);
            	System.out.println("Does the illness reject any food?[Y/N]");
            	if(consola.readLine().toLowerCase().equals("y")){
            	System.out.println("Enter the maximum food calories this patients can eat");
            	j.rejectFoodbyChronic(ch.getId(), Integer.parseInt(consola.readLine()));
            	}
                break;
            case 2:
            	System.out.println(j.selectC());
                break;
            case 3:
            	System.out.println("Enter the chronic id: ");
            	System.out.println(j.selectC());
            	System.out.println("Type it´s id: ");
            	int num=Integer.parseInt(consola.readLine());
            	System.out.println("Enter the medication name: ");
            	j.assignChronicMedication(num, consola.readLine());
            	break;
            case 4:
            	System.out.println("Choose an illness: ");
            	System.out.println(j.selectC());
            	System.out.println("Enter the id from the illness you want to delete: ");
            	j.deleteChronic(Integer.parseInt(consola.readLine()));
            	break;
            case 5:
            	System.out.println("Enter the illness name: ");
            	System.out.println(j.searchChro(consola.readLine()));
            	break;
        }
	}
}