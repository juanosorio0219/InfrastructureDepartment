package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class InfrastructureDepartment {
	
	private ArrayList <Billboard> billboards = new ArrayList <Billboard>();
	public final static String FILE_IMPORT_TXT_PATH = "data/BillboardDataExported.csv";
	public String FILE_REPORT_TXT_PATH = "data/report.txt";
	public String FILE_SAVE = "data/billboard.juan";
	
	
	
	
	public InfrastructureDepartment() {
		
	}
	
	public void addBillboard(double width, double height, boolean inUse, String brand) throws FileNotFoundException, IOException {
		Double w = width;
		Double h = height;
		boolean iu = inUse;
		String b = brand;
		Billboard newBillboard = new Billboard(w, h, iu, b);
		billboards.add(newBillboard);
		FileWriter fw = new FileWriter(FILE_IMPORT_TXT_PATH,true);
		fw.write(w+"|"+h+"|"+iu+"|"+b+"\n");
		fw.close();
		saveBillboards();
		
		
		
		
		
	}
	
	private void saveBillboards() throws FileNotFoundException, IOException {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream (FILE_SAVE));
		oos.writeObject(billboards);
		oos.close();
		
		
	}
	
	
	@SuppressWarnings("unchecked")
	public boolean loadBillboards() throws FileNotFoundException, IOException, ClassNotFoundException {
		File f = new File(FILE_SAVE);
		boolean isLoaded = false;
		if(f.exists()) {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
			billboards = (ArrayList<Billboard>) ois.readObject();
			ois.close();
			isLoaded = true;
			
		}
		return isLoaded;
		
				
	}
	
	public void exportDangerousBillboardReport() throws FileNotFoundException, IOException {
		FileWriter fw = new FileWriter(FILE_REPORT_TXT_PATH,true);
		ArrayList <Billboard> danger = new ArrayList<Billboard>();
		System.out.println("==========================");
		System.out.println("DANGEROUS BILLBOARD REPORT");
		System.out.println("==========================");	
		System.out.println("The dangerous billboard are:");
		for(int i =0; i<billboards.size(); i++) {
			if(billboards.get(i).CalculateArea()>=160) {
				danger.add(billboards.get(i));
				System.out.println((i+1)+". Billboard "+billboards.get(i).getBrand()+" with area "+billboards.get(i).CalculateArea());
				fw.write((i+1)+". Billboard "+billboards.get(i).getBrand()+" with area "+billboards.get(i).CalculateArea()+"\n");				
			}
			
		}
		
		fw.close();
		
		
		
		
		
		
	}
	
	public void importData() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(FILE_IMPORT_TXT_PATH));
		String line = br.readLine();
		while(line!=null) {
			String parts[] = line.split("\\|");
			Double part0 = Double.parseDouble(parts[0]);
			Double part1 = Double.parseDouble(parts[1]);
			Boolean part2 = Boolean.parseBoolean(parts[2]);			
			Billboard billboard = new Billboard(part0, part1, part2, parts[3]);
			billboards.add(billboard);
			line = br.readLine();
					
		}
		
		br.close();
		
		
	}
	public void showList() {
		int total = 0;
		System.out.println("W		H		inUse		Brand");
		for(int i=0; i<billboards.size(); i++) {
			System.out.println(billboards.get(i).getWidth()+"		"+billboards.get(i).getHeight()
					+"		"+billboards.get(i).isInUse()+"		"+billboards.get(i).getBrand());	
			total++;
			
		}
		System.out.println("TOTAL: "+total+" billboards");
		
	}
	
	

}
