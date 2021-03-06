package Interface;

import java.io.*;
import java.sql.SQLException;

import sample.db.patient.*;
import sample.db.pojos.*;


public class MenuIll {
	public MenuIll(){
		
	}
	public void menu() throws IOException {
        System.out.println(""
            + "1. Insert an illness\n"
            + "2. Show all illnesses\n"
            + "3. Delete an illness\n"
            + "4. Search for an illness\n"
            + "5. Insert the medication to overtake the illness\n"
            + "Introduce an option:");
    }
	public void menuillness(JDBC j) throws IOException, SQLException {
		MenuIll menu=new MenuIll();
		Illness i=null;
        BufferedReader consola = new BufferedReader(new InputStreamReader(System.in));
        int option =0;
        menu.menu();
        option=Integer.parseInt(consola.readLine());
        switch (option) {
            case 1:
            	System.out.println("Illness name: ");
            	String st=consola.readLine();
            	i=new Illness(st);
            	j.insertIll(i);
                break;
            case 2:
            	System.out.println(j.selectI());
                break;
            case 3:
            	System.out.println("Insert the id of the illness to be erased: ");
            	System.out.println(j.selectI());
            	j.deleteIllness(Integer.parseInt(consola.readLine()));
            	break;
            case 4:
            	System.out.println("Enter an Illness: ");
            	String ill=consola.readLine();
            	i=j.searchIll(ill);
            	if(i!=null){
            	System.out.println(i);
            	}
            	else{
            		System.out.println("That illness doesn´t appear in our database,"
            				+ " do you want to insert it? [yes/no]");
            		switch(consola.readLine().toLowerCase()){
            		case "yes":
            			i=new Illness(ill);
            			j.insertIll(i);
            			break;
            		default:
            			break;
            		}
            	}
            	break;
            case 5:
            	System.out.println("Enter the illness id: ");
            	System.out.println(j.selectI());
            	System.out.println("Type it´s id: ");
            	int num=Integer.parseInt(consola.readLine());
            	System.out.println("Enter the medication name: ");
            	j.assignIllnessMedication(num,consola.readLine());
            	break;

        }
	}
}