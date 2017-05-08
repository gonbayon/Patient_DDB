package Interface;

import java.io.*;
import java.sql.SQLException;

import sample.db.patient.JDBC;
import sample.db.pojos.*;


public class MENU_Complete {
	public void menu() throws IOException {
        System.out.println(""
            + "999. Conectar \n"
            + "1. Patient \n"
            + "2. Food\n"
            + "3. Illness \n"
            + "4. Medication\n"
            + "5. Schedule \n"
            + "6. Visitor \n"
            + "7. Chronic\n"
            + "0. Salir\n\n"
            + "Introduzca una opcion:");
    }
	public static void main(String[]args) throws IOException, SQLException {
		MENU_Complete menu=new MENU_Complete();
		MenuChronic chr=null;
		Chronic ch=null;
		MenuPat mp=null;
		Patient p=null;
		JDBC j=null;
		MenuF fo=null;
		Food f=null;
		MenuIll il=null;
		Illness i=null;
		MenuSche sche=null;
		Schedule sch=null;
		MenuVisit vi=null;
		Visitor v=null;
		Salt s=null;
		MenuMedic me=null;
		Medication m=null;
        BufferedReader consola = new BufferedReader(new InputStreamReader(System.in));
        int option=0;
              do{
        menu.menu();
        option=Integer.parseInt(consola.readLine());
        switch (option) {
	        case 999:
	        	System.out.println("Name of DDB to connect");
	            j=new JDBC(consola.readLine());
	            j.connect();
	            try{
	                j.createTablePat();
	                j.createTableFood();
	                j.createTableSalt();
	                j.createTableMedic();
	                j.createTableChronic();
	                j.createTableIll();
	                j.createTableSchedule();
	                j.createTableVisit();
	                j.createTablePatFood();
	                j.createTablePatMed();
	                j.createTableVisSche();
	            	}catch(SQLException np){
	            		System.out.println("Tables already created");
	            	}
	            break;
            case 1:
            	mp=new MenuPat();
            	mp.menupatient(j);
                break;
            case 2:
            	fo= new MenuF();
            	fo.menufood(j);
                break;
            case 3:
            	il=new MenuIll();
            	il.menuillness(j);
            	break;
            /*case 4:
            	me=new MenuMedic();
            	me.menumedication(j);
            	
            	

            case 5:
            	sche=new MenuSche();
            	sche.menuschedule(j);    
            	break;
            case 6:
            	vi=new MenuVisit();
            	vi.menuvisit(j); 
            	break;
            case 7:
            	chr=new MenuChronic();
            	chr.menuchronic(j);
            	break;*/
            case 0:
            	System.exit(0);
            	break;
        }
}while(true);
	}
}