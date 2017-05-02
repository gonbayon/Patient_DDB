package Interface;

import java.io.*;
import java.sql.SQLException;

import sample.db.patient.JDBC;
import sample.db.pojos.*;


public class MenuSche {
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
		Schedule s=null;
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
                j.createTableVisit();
            	}catch(NullPointerException np){
            		System.out.println("You first need to connect to a DDB");
            	}
                break;
            case 3:
            	try{
            		try{
            			String start,end,day;
            			System.out.println("Start:");
            			start=consola.readLine();
            			System.out.println("End:");
            			end=consola.readLine();
            			System.out.println("Day:");
            			day=consola.readLine();
            			s=new Schedule(start,end,day);
            			j.insertSche(s);
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
            			System.out.println(j.selectSh());
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
            			System.out.println("Enters the id from the schedule you want to delete: ");
            			int id=Integer.parseInt(consola.readLine());
            			j.deleteSchedule(id);
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
            			System.out.println("Enter the Schedule id to be shown");
            			int n=Integer.parseInt(consola.readLine());
            			Schedule sc=j.searchSche(n);
            			System.out.println(sc);
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
            			System.out.println("Enter the id of the schedule to be changed");
            			int id=Integer.parseInt(consola.readLine());
            			System.out.println("Enter its new start");
            			String start=consola.readLine();
            			System.out.println("Enter its new end");
            			String end=consola.readLine();
            			System.out.println("Enter its new day");
            			String day=consola.readLine();
            			j.updateSchedule(start,end,day,id);
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
            			j.dropTableSh();
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