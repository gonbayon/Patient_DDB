package Interface;

import java.io.*;
import java.sql.SQLException;

import sample.db.patient.JDBC;
import sample.db.pojos.*;


public class MENU_Complete {
	public void menu() throws IOException {
        System.out.println(""
            + "999. Connect \n"
            + "1.	Patient \n"
            + "2.	Food\n"
            + "3.	Illness \n"
            + "4. 	Medication\n"
            + "5. 	Schedule \n"
            + "6.	Visitor \n"
            + "7. 	Chronic\n"
            + "8.	Doctors\n"
            + "9.	Drop all tables\n"
            + "0. 	Salir\n\n"
            + "Introduce an option:");
    }
	public static void main(String[]args) throws IOException, SQLException {
		MENU_Complete menu=new MENU_Complete();
		MenuChronic chr=null;
		MenuPat mp=null;
		JDBC j=null;
		MenuF fo=null;
		MenuIll il=null;
		MenuSche sche=null;
		MenuVisit vi=null;
		MenuMedic me=null;
		MenuDoc md=null;
    	int cont=0;
        BufferedReader consola = new BufferedReader(new InputStreamReader(System.in));
        int option=0;
              do{
        menu.menu();
        do{
        try{
        	cont=0;
        option=Integer.parseInt(consola.readLine());
        }catch(NumberFormatException np){
        	cont++;
        	System.out.println("Please introduce a number");
        }
        }while(cont>0);
        switch (option) {
	        case 999:
	        	System.out.println("Name of DDB to connect");
	            j=new JDBC(consola.readLine());
	            j.connect();
	            try{
	            	j.createTableDoctor();
	                j.createTablePat();
	                j.createTableFood();
	                j.createTableSalt();
	                j.createTableMedic();
	                j.createTableChronic();
	                j.createTableIll();
	                j.createTableSchedule();
	                j.createTableVisit();
	                j.createTablePatFood();
	                j.createTableVisSche();
	                j.createTableChroFood();
	                j.createTableIllMed();
	                j.createTablePatDoc();
	                j.createTableChroMed();
	                j.createTableDocSche();
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
            case 4:
            	me=new MenuMedic();
            	me.menuMedicine(j);
            	break;
            case 5:
            	sche=new MenuSche();
            	sche.menuSche(j);  
            	break;
            case 6:
            	vi=new MenuVisit();
            	vi.menuVisit(j); 
            	break;
            case 7:
            	chr=new MenuChronic();
            	chr.menuChronic(j);;
            	break;
            case 8:
            	md=new MenuDoc();
            	md.menuDoctor(j);
            	
            	break;
            case 9:
            	j.dropTableC();
            	j.dropTableF();
            	j.dropTableI();
            	j.dropTableD();
            	j.dropTableM();
            	j.dropTableP();
            	j.dropTableS();
            	j.dropTableSh();
            	j.dropTableV();
            	j.dropTableI_Med();
            	j.dropTableP_Doctor();
            	j.dropTableP_Food();
            	j.dropTableC_Food();
            	j.dropTableV_Schedule();
            	j.dropTableC_Food();
            	j.dropTableChro_Med();
            	break;
            case 0:
            	System.exit(0);
            	break;
        }
}while(true);
	}
}