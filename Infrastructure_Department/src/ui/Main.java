package ui;

import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.Scanner;

import model.InfrastructureDepartment;

public class Main {

	private InfrastructureDepartment infrastructure;
	private final static int EXIT_OPTION = 4;
	public Scanner sn = new Scanner(System.in);

	public Main() {
		infrastructure = new InfrastructureDepartment();

	}

	public static void main(String [] args) throws IOException {		
		Main controller = new Main();
		controller.menu();			
	}
	public void menu() throws IOException {
		infrastructure.importData();
		boolean out = false;	
		while (!out) {
			System.out.println("===================");
			System.out.println("WELCOME TO THE MENU");
			System.out.println("===================");
			System.out.println("1. Add billboard");
			System.out.println("2. Show billboards");
			System.out.println("3. Export dangerous billboards report");
			System.out.println("4. Exit");
			int option = Integer.parseInt(sn.nextLine());			 
			switch (option) {
			case 1:
				addBillboard();
				break;
			case 2:
				infrastructure.showList();				
				break;
			case 3:
				infrastructure.exportDangerousBillboardReport();
				
				break;
			case EXIT_OPTION:
				out = true;
				break;
			default:
				System.out.println("Only numbers between 1 and 4");
			}

		}


	}



	public void addBillboard() throws FileNotFoundException, IOException{
		System.out.println("Please enter the width, height, inUse, brand separate by ++");
		String data = sn.nextLine();
		String [] array = data.split("\\+\\+");
		Double part0 = Double.parseDouble(array[0]);
		Double part1 = Double.parseDouble(array[1]);
		Boolean part2 = Boolean.parseBoolean(array[2]);
		infrastructure.addBillboard(part0, part1, part2, array[3]);


	}

	

}


