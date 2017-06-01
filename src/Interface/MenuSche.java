package Interface;

import java.io.*;
import java.sql.SQLException;

import sample.db.patient.JDBC;
import sample.db.pojos.*;


public class MenuSche {
	public void menu() throws IOException {
        System.out.println(""
            + "1. Insert a new schedule \n"
            + "2. Show all schedules\n"
            + "3. Delete a single schedule\n"
            + "4. Search for a schedule\n"
            + "5. Update a schedule \n"
            + "Introduce an option:");
    }
	public void menuSche(JDBC j) throws IOException, SQLException {
		MenuSche menu=new MenuSche();
		Schedule s=null;
        BufferedReader consola = new BufferedReader(new InputStreamReader(System.in));
        int opcion =0;
        menu.menu();
        opcion=Integer.parseInt(consola.readLine());
        switch (opcion) {
            case 1:
            	System.out.println("Enter the starting time: ");
            	String start=consola.readLine();
            	System.out.println("Enter the finishing time: ");
            	String fin=consola.readLine();
            	System.out.println("Enter the day when it will occur: ");
            	String when=consola.readLine();
            	s=new Schedule(start, fin, when);
            	j.insertSche(s);
                break;
            case 2:
            	System.out.println(j.selectSh());
                break;
            case 3:
            	System.out.println(j.selectSh());
            	System.out.println("Enter the id from the scheduled to be erased: ");
            	j.deleteSchedule(Integer.parseInt(consola.readLine()));
            	break;
            case 4:
            	System.out.println("Enter the starting time: ");
            	String _start=consola.readLine();
            	System.out.println("Enter the finishing time: ");
            	String _fin=consola.readLine();
            	s=j.searchSche(_start, _fin);
            	System.out.println(s);
            	break;
            case 5:
            	System.out.println("Choose the schedule to be updated: ");
            	System.out.println(j.selectSh());
            	System.out.println("Enter the id from the schedule to be updated: ");
            	s=j.getSchebyId(Integer.parseInt(consola.readLine()));
            	System.out.println("What do you want to update");
            	System.out.println(""
            			+ "1. Starting time\n"
            			+ "2. Finishing time\n"
            			+ "3. Day\n"
            			+ "4. Starting and finishing time\n"
            			+ "4. Everything\n"
            			+ "Introduce an option: ");
            	switch (Integer.parseInt(consola.readLine())) {
				case 1:
					System.out.println("Enter the new starting time: ");
					j.updateSchedule(consola.readLine(), s.getEnd(), s.getDay(),s.getId());
					break;
				case 2:
					System.out.println("Enter the new finishing time: ");
					j.updateSchedule(s.getStart(),consola.readLine(),s.getDay(),s.getId());
					break;
				case 3:
					System.out.println("Enter the new day: ");
					j.updateSchedule(s.getStart(), s.getEnd(),consola.readLine(),s.getId());
					break;
				case 4:
					System.out.println("Enter the new starting time: ");
					String s1=consola.readLine();
					System.out.println("Enter the new finishing time: ");
					String s2=consola.readLine();
					j.updateSchedule(s1,s2,s.getDay(),s.getId());
					break;
				case 5:
					System.out.println("Enter the new starting time: ");
					String s11=consola.readLine();
					System.out.println("Enter the new finishing time: ");
					String s22=consola.readLine();
					System.out.println("Enter the new day: ");
					String s222=consola.readLine();
					j.updateSchedule(s11, s22, s222,s.getId());
					break;
				}
            	break;

	}
}
}