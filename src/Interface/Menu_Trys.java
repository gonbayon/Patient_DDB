package Interface;

import java.io.*;
import java.sql.SQLException;

import sample.db.patient.JDBC;
import sample.db.pojos.*;

public class Menu {
	public void menu() throws IOException {
        System.out.println(""
            + "1. Connect \n"
            + "2. Create table\n"
            + "3. Insert patient \n"
            + "4. Insert food\n"
            + "5. Insert salt\n"
            + "6. Link food salt \n"
            + "7. link food patient \n"
            + "8. Show food\n"
            + "9. Show patient\n"
            + "10. Disconnect\n"
            + "0. Salir\n\n"
            + "Introduzca una opcion:");
	}
	public static void main(String[] args) throws IOException, SQLException {
		Menu menu=new Menu();
		Patient p=null;
		Food f=null;
		Salt s=null;
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
                j.createTablePat();
                j.createTableFood();
                j.createTableSalt();
                j.createTablePatFood();
            	}catch(NullPointerException np){
            		System.out.println("You first need to connect to a DDB");
            	}
                break;
            case 3:
            	String n,s1;
            	int rn;
            	System.out.println("Name:");
            	n=consola.readLine();
            	System.out.println("Surname:");
            	s1=consola.readLine();
            	System.out.println("Room number:");
            	rn=Integer.parseInt(consola.readLine());
            	Patient p2=new Patient(n, s1, rn);
            	j.insertPatient(p2);
            	break;
            case 4:
            	String name1;
    			float cal1;
    			System.out.println("Food name:");
    			name1=consola.readLine();
    			System.out.println("Calories:");
    			cal1=Float.parseFloat(consola.readLine());
    			f=new Food(name1, cal1);
    			j.insertFood(f);
            	break;
            case 5:
            	Float min,max;
            	System.out.println("Insert the minimum ammount of salt the dish can have:");
            	min=Float.parseFloat(consola.readLine());
            	System.out.println("Insert the maximum ammount of salt the dish can have:");
            	max=Float.parseFloat(consola.readLine());
            	s=new Salt(min,max);
            	j.insertSalt(s);
            	break;
            case 6:
            	System.out.println(j.selectS());
    			System.out.println("Connect food and salt, enter food id and salt id");
    			int n1=Integer.parseInt(consola.readLine());
    			int m=Integer.parseInt(consola.readLine());
    			j.assignSaltFood(n1,m);
            	break;
            case 7:
            	System.out.println("Enter the patient id and food id: ");
    			System.out.println(j.selectF());
    			int id2=Integer.parseInt(consola.readLine());
    			int id11=Integer.parseInt(consola.readLine());
    			j.assignPatientFood(id2, id11, " " );
            	break;
            case 8:
            	System.out.println(j.selectF());
            	break;
            case 9:
            	System.out.println(j.selectP());
            	break;
            case 10:
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

