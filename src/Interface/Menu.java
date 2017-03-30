package Interface;

import java.io.*;
import java.sql.SQLException;

import sample.db.patient.JDBC;
import sample.db.pojos.Patient;

public class Menu {
	public void menu() throws IOException {
        System.out.println("\n\n"
            + "1. Connect \n"
            + "2. Create table\n"
            + "3. Insert\n"
            + "4. Select\n"
            + "5. Delete\n"
            + "6. Disconnect\n"
            + "0. Salir\n\n"
            + "Introduzca una opcion:");
    }
	public static void main(String[] args) throws IOException, SQLException {
		Menu menu=new Menu();
		Patient p=null;
		JDBC j=null;
        BufferedReader consola = new BufferedReader(new InputStreamReader(System.in));
        int contador =0;
              do{
        menu.menu();
        contador=Integer.parseInt(consola.readLine());
        switch (contador) {
            case 1:
                System.out.println("Name of DDB to connect");
                j=new JDBC(consola.readLine());
                j.connect();
                break;
            case 2:
                j.createTable();
                break;
            case 3:
            	String n,s;
            	int rn;
            	System.out.println("Name:");
            	n=consola.readLine();
            	System.out.println("Surname:");
            	s=consola.readLine();
            	System.out.println("Number:");
            	rn=Integer.parseInt(consola.readLine());
            	p=new Patient(n,s,rn);
            	j.insertPatient(p);
            	//hola
            	break;
            case 4:
            	System.out.println(j.select());
            	break;
            case 5:
            	System.out.println("Enters the id from the patient you want to delete: ");
            	int id=Integer.parseInt(consola.readLine());
            	j.deletePatient(id);
            	break;
            case 6:
            	j.disconnect();
            	break;
            case 0:
            	System.exit(0);
            	break;
        }
}while(true);
	}
}

