import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class CheckNextJob {

	private static List<String> status;

	private static String[] task_list= {"Realizar Backup",
			"Importar Repositorio",
			"Importar SIF",
			"Renombrar Repositorio",
			"Compilacion de nuevos SRF",
			"Ejecutar pasos PRE",
			"Cambio SRF y Browser Scritps",
			"Ddlsync",
			"Stop",
			"Start",
			"LOVs",
			"Aplicacion",
			"Interfaces",
			"Triggers",
			"Instalacion Catalogo",
			"Instalacion Migracion",
			"Instalacion TIBCO",
			"Ejecutar pasos POST",
			"Stop",
	"Start"};

	public static void main(String[] args) {
		if(args.length <= 0){
			System.out.println("Error\tInvalid arguments --> \"java CheckNextJob route.txt JOB\"");
			System.exit(1);
		}else{
			String file_name = args[0];
			int job = Integer.valueOf(args[1]);
			status = readFile(args[0]);

			if(status != null && job > 0 && job <= 20){
				if(isNextStatus(job)){//Is the next job
					makeChanges(file_name, job, "#");
				}else{
					if(isExecuting(job)){//Is not the next but is executing
						makeChanges(file_name, job, "0");
					}else{//Is not the next and is not executing
						showStatus();
						System.exit(1);
					}//if-else
				}//if-else
			}else{
				if(status == null){
					System.out.println("Error\tFailure reading the file --> \"route.txt\"");
				}else{
					System.out.println("Error\tInvalid arguments --> \"java CheckNextJob route.txt JOB\"");
				}//if-else
			}//if-else
		}//if-else

	}//main


	/**
	 * @param file_name
	 * @param job
	 */
	private static void makeChanges(String file_name, int job, String next_status) {
		changeStatus(next_status, job);
		saveChangeStatus(file_name);
		showStatus();
		System.exit(0);
	}


	/**
	 * Determine if the job is executing or not
	 * @param job
	 * @return TRUE if is executing
	 * @return FALSE if is not executing
	 */
	private static boolean isExecuting(int job) {
		return status.get(job-1).equals("#");
	}//isExecuting


	/**
	 * Save the status to a file for next executions
	 * @param file_name
	 */
	private static void saveChangeStatus(String file_name) {
		try {
			File tempFile = new File("tmp.txt");
			File file = new File(file_name);

			PrintWriter pw = new PrintWriter(new FileWriter(tempFile));

			for(String element : status){
				pw.println(element);
			}//for

			pw.close();

			// Delete the original file
			if(!file.delete()) {
				System.out.println("Error\tCould not delete file");
				return;
			}//if

			//Rename the temp file from original file
			if(!tempFile.renameTo(file)){
				System.out.println("Error\tCould not rename file");
				System.out.println("\tPlease rename the file tmp.txt to route.txt");
			}//if
		}catch (FileNotFoundException ex) {
			System.out.println("Error\tFile not found");
		}catch (IOException ex) {
			System.out.println("Error\tProblem managing the file");
		}//try-catch

	}//saveChangeStatus

	/**
	 * Change the status if the list
	 */
	private static boolean changeStatus(String stat, int next) {
		boolean changed = true;
		try{
			status.set(next-1, stat);
		}catch(Exception e){
			changed = false;
		}//try-catch
		return changed;
	}//changeStatus

	/**
	 * This method determine if the JOB pased by argument is the next to execute
	 * @param next
	 * @return TRUE if is the next
	 * @return FALSE if is not the next
	 */
	private static boolean isNextStatus(int job) {
		boolean isNext = false;
		int next = 0;
		for(int count = 0; count < status.size() && !isNext; count++){
			if(status.get(count).toLowerCase().equals("x")){
				isNext = true;
				next = count + 1;
			}//if
		}//for

		if(job != next){
			isNext = false;
		}//if

		return isNext;
	}//isNextStatus

	/**
	 * Read the file "file_name" and copy the content to a List of a String
	 * @param file_name
	 * @return a list with all the lines of the file
	 */
	private static List<String> readFile(String file_name){
		List<String> list = new ArrayList<String>();
		File file = new File(file_name);

		if(!file.isFile() || !file.exists()){
			return null;
		}//if

		BufferedReader reader = null;

		try {
			reader = new BufferedReader(new FileReader(file));
			String text = null;

			while ((text = reader.readLine()) != null) {
				list.add(text);
				//System.out.println(text);
			}//while

			if (reader != null) {
				reader.close();
			}//if

		} catch (FileNotFoundException e) {
			//e.printStackTrace();
		} catch (IOException e) {
			//e.printStackTrace();
		}//try-catch
		return list;
	}//readFile

	/**
	 * Show the status of the installation by console.
	 */
	private static void showStatus(){
		String completed_tasks = "";
		String todo_tasks = "";
		int number_todo_tasks = 0;
		int number_completed_tasks = 0;

		for(int number = 0; number < status.size(); number++){
			String task_status;
			if(status.get(number).toLowerCase().equals("x")){
				task_status = "\t\tTO DO";
				todo_tasks += "  ";
				number_todo_tasks++;
			}else if(status.get(number).equals("#")){
				task_status = "\t\tDOING";
				completed_tasks += "##";
				number_todo_tasks++;
			}else if(status.get(number).equals("0")){
				task_status = "\t\tCOMPLETED";
				completed_tasks += "##";
				number_completed_tasks++;
			}else{
				task_status = "\t\t";
			}//if-else

			System.out.printf("%-2s - [%s] - %-25s %s\n",number+1,status.get(number),task_list[number],task_status);

		}//for

		double percent = number_completed_tasks*100/(number_completed_tasks + number_todo_tasks);
		System.out.println("____________________________________________________________");
		System.out.println("[" + completed_tasks + todo_tasks + "] --> " + percent + "% completed");
	}//showStatus

}//class