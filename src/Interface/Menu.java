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
            + "3. Insert \n"
            + "4. Show all\n"
            + "5. Delete \n"
            + "6. Search \n"
            + "7. Update \n"
            + "8. Drop\n"
            + "9. Disconnect\n"
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
                //j.createTablePat();
                j.createTableFood();
                j.createTableSalt();
                j.createTableMedic();
            	}catch(NullPointerException np){
            		System.out.println("You first need to connect to a DDB");
            	}
                break;
            case 3:
            	try{
            		try{
            	/*String n,s;
            	int rn;
            	System.out.println("Name:");
            	n=consola.readLine();
            	System.out.println("Surname:");
            	s=consola.readLine();
            	System.out.println("Room number:");
            	rn=Integer.parseInt(consola.readLine());
            	p=new Patient(n,s,rn);
            	j.insertPatient(p);*/
            			String name;
            			float cal;
            			System.out.println("Food name:");
            			name=consola.readLine();
            			System.out.println("Calories:");
            			cal=Float.parseFloat(consola.readLine());
            			f=new Food(name, cal);
            			s=new Salt(3,1);
            			j.insertFood(f);
            			j.insertSalt(s);
            		}catch(SQLException sq){
            			System.out.println("You first need to create a table");
            			sq.printStackTrace();
            		}
            	}catch(NullPointerException np){
            		System.out.println("You first need to connect to a DDB");
      	}
            	break;
            case 4:
            	//try{
            		//try{
            			System.out.println(j.selectF());
            			/*System.out.println(j.selectS());
            			System.out.println("Connect food and salt, enter food id and salt id");
            			int n=Integer.parseInt(consola.readLine());
            			int m=Integer.parseInt(consola.readLine());
            			j.assignSaltFood(n,m);
            			System.out.println(j.selectF());
            			//System.out.println(j.selectP());*/
            		/*}catch(SQLException sq){
            			System.out.println("You first need to create a table");
            		}
            	}catch(NullPointerException np){
            		np.printStackTrace();
            		System.out.println("You first need to connect to a DDB");
            	}*/
            	break;
            case 5:
            	try{
            		try{
            			/*System.out.println(j.selectP());
            			System.out.println("Enters the id from the patient you want to delete: ");
            	int id=Integer.parseInt(consola.readLine());
            	j.deletePatient(id);*/
            			System.out.println("Enters the id from the food you want to delete: ");
            			int id=Integer.parseInt(consola.readLine());
            			j.deleteFood(id);
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
            			/*System.out.println("Enter the room number from the patient to be shown");
            			int n=Integer.parseInt(consola.readLine());
            			Patient p1=j.searchPatient(n);
            			System.out.println(p1);*/
            			System.out.println("Enter the food id to be shown");
            			int n=Integer.parseInt(consola.readLine());
            			Food fod=j.searchFood(n);
            			System.out.println(fod);
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
            			/*System.out.println("Enter the id of the patient to be changed of room");
            			int id=Integer.parseInt(consola.readLine());
            			System.out.println("Enter its new room number");
            			int room=Integer.parseInt(consola.readLine());
            			j.updatePatient(id, room);*/
            			System.out.println("Enter the id of the food to be changed");
            			int id=Integer.parseInt(consola.readLine());
            			System.out.println("Enter its new calories");
            			int cal=Integer.parseInt(consola.readLine());
            			j.updateFood(id, cal);
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
            			//j.dropTableP();
            			j.dropTableF();
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

