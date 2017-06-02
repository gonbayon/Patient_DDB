package Interface;

import java.io.*;
import java.sql.SQLException;

import sample.db.patient.JDBC;
import sample.db.pojos.*;

public class MenuF {
	public MenuF(){
		
	}
	public void menu() throws IOException {
        System.out.println(""
            + "1. Insert  \n"
        	+ "2. Insert salt\n"
            + "3. Link food salt \n"
        	+ "4. Show different salt possibilities\n"
            + "5. Show all foods\n"
            + "6. Delete a food or salt range \n"
            + "7. Search for a food or salt range \n"
            + "8. Update the calories of a food \n"
            + "Introduce an option:");
    }
	public void menufood(JDBC j) throws IOException, SQLException {
		MenuF menu=new MenuF();
		Food f=null;
		Salt s=null;
        BufferedReader consola = new BufferedReader(new InputStreamReader(System.in));
        
        menu.menu();
        int opcion=Integer.parseInt(consola.readLine());
        switch (opcion) {
            case 1:
            	String name1;
    			float cal1;
    			System.out.println("Food name:");
    			name1=consola.readLine();
    			System.out.println("Calories:");
    			cal1=Float.parseFloat(consola.readLine());
    			f=new Food(name1, cal1);
    			j.insertFood(f);
    			break;
            case 2:
            	Float min,max;
            	System.out.println("Insert the minimum ammount of salt the dish can have:");
            	min=Float.parseFloat(consola.readLine());
            	System.out.println("Insert the maximum ammount of salt the dish can have:");
            	max=Float.parseFloat(consola.readLine());
            	s=new Salt(min,max);
            	j.insertSalt(s);
            	break;
            case 3:
    			System.out.println(j.selectS());
            	System.out.println(j.selectF());
            	System.out.println("Enter food id and salt id: ");
    			int n1=Integer.parseInt(consola.readLine());
    			int me=Integer.parseInt(consola.readLine());
    			j.assignSaltFood(n1,me);
            	break;
            case 4:
            	System.out.println(j.selectS());
            	break;
            case 5:
            	System.out.println(j.selectF());
            	break;
            case 6:
            	System.out.println("What are you going to delete:"
            			+ "\n1. Salt"
            			+ "\n2. Food"
            			+ "\nIntroduce an option: ");
            	switch(Integer.parseInt(consola.readLine())){
            	case 1:
            		System.out.println(j.selectS());
            		System.out.println("Enter the id from the salt to be removed: ");
            		j.deleteSalt(Integer.parseInt(consola.readLine()));
            		break;
            	case 2:
            		System.out.println(j.selectF());
            		System.out.println("Enter the id from tthe food to be removed: ");
            		j.deleteFood(Integer.parseInt(consola.readLine()));
            		break;
            	}
            	break;
            case 7:
            	System.out.println("What are you loooking for:"
            			+ "\n1. Salt"
            			+ "\n2. Food"
            			+ "\nIntroduce an option: ");
            	switch(Integer.parseInt(consola.readLine())){
            	case 1:
            		System.out.println(j.selectS());
            		break;
            	case 2:
            		System.out.println(j.selectF());
            		break;
            	}
            	break;
            case 8:
            	System.out.println(j.selectF());
            	System.out.println("Enter the id from the food that´s going to be updated: ");
            	int id=Integer.parseInt(consola.readLine());
            	System.out.println("Enter the new calories the dish is going to have");
            	j.updateFood(id, Integer.parseInt(consola.readLine()));
            	break;
        }
	}
}


