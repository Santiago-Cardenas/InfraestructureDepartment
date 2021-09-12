package ui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import model.InfraestructureDept;

public class Main {

	private static Scanner sc = new Scanner(System.in);
	private InfraestructureDept dept;

	public Main() throws IOException {
		dept = new InfraestructureDept();
		try {
			dept.importData();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws IOException {

		Main menu = new Main();
		
		int option = 0;
		do {
			option = menu.showMenuAndReadInput();
			menu.dispatchOption(option);
		} while (option != 4);
	}
		
	public int showMenuAndReadInput() {
		System.out.println("Please, select one option:\n"+
							"(1) Agregar valla publicitaria \n"+
							"(2) Mostrar vallas publicitarias \n"+
							"(3) Exportar reporte de peligrosidad  \n"+
							"(4) Salir");
		
		int number= sc.nextInt();
		sc.nextLine();
		
		return number;
	}

	public void dispatchOption(int option) {
		switch (option) {
		case 1:
			creatBillboard();
			break;
		case 2:
			System.out.println("W	H	inUse	Brand\n");
			System.out.println(dept.toString());
			System.out.println("Total: " + dept.getBillboards().size() + " vallas\n");
			break;
		case 3:
			System.out.println(dept.exportDangerousBillboardsReport());
			try {
				dept.saveBillboardsReport();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;

		}
	}
	
	private void creatBillboard() {
		System.out.println("		Crear Valla Publicitaria\n"
				+ "Crear valla de la manera siguiente: Ancho++Altura++¿Esta en uso?(responder con true o false)++Marca");
		String billboard = sc.nextLine();
		String[] parts = billboard.split("\\+\\+");

		double w = Double.parseDouble(parts[0]);
		double h = Double.parseDouble(parts[1]);
		boolean iU=true;
		if (parts[2].equalsIgnoreCase("true")) {
			iU = true;
		}
		else if(parts[2].equalsIgnoreCase("false")) {
			iU = false;
		}
		dept.addBillboard(w, h, iU, parts[3]);
		try {
			dept.saveBillboards();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			dept.exportData();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
