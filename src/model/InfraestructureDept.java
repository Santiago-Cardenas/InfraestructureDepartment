package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class InfraestructureDept {

	private List<Billboard> billboards;
	private String FILE_DATA_CSV_PATH = "data/BillboardDataExported.csv";
	public String BILLBOARD_FILE_NAME = "data/billboard.bbd";
	public String BILLBOARD_REPORT_FILE_NAME = "data/report.txt";

	public InfraestructureDept() {
		billboards = new ArrayList<Billboard>();
	}

	public void addBillboard(double w, double h, boolean iU, String b) {
		billboards.add(new Billboard(w, h, iU, b));
	}
	
	public List<Billboard> getBillboards() {
		return billboards;
	}

	public void saveBillboards() throws FileNotFoundException, IOException {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(BILLBOARD_FILE_NAME));
		oos.writeObject(billboards);
		oos.close();
	}

	public void saveBillboardsReport() throws IOException{
		File file = new File (BILLBOARD_REPORT_FILE_NAME);
		FileOutputStream fos = new FileOutputStream(file);
		fos.write(exportDangerousBillboardsReport().getBytes());
		fos.close();
	}

	public void importData() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader (FILE_DATA_CSV_PATH));
		String line = br.readLine();
		int count=0;
		while (line != null) {
			count++;
			String[] parts = line.split("\\|");
			if (count > 1) {
				double w = Double.parseDouble(parts[0]);
				double h = Double.parseDouble(parts[1]);
				boolean iU = true;
				if (parts[2].equalsIgnoreCase("true")) {
					iU = true;
				} else if (parts[2].equalsIgnoreCase("false")) {
					iU = false;
				}
				addBillboard(w, h, iU, parts[3]);
			}
			line = br.readLine();
		}
		br.close();
	}

	public void exportData() throws IOException {
		FileWriter fw = new FileWriter(FILE_DATA_CSV_PATH,false);
		fw.write("width|height|inUse|brand\n");
		for (int i = 0; i < billboards.size(); i++) {
			Billboard myBillboard = billboards.get(i);
			fw.write(myBillboard.getWidth() + "|" + myBillboard.getHeight() + "|" + myBillboard.isInUse() + "|"
					+ myBillboard.getBrand() + "\n");
		}
		fw.close();
	}
		
	public String exportDangerousBillboardsReport() {
		String report="===========================\n"+
					  "DANGEROUS BILLBOARD REPORT\n"+
					  "===========================\n"+
					  "The dangerous billboard are:\n";
		int pos=1;
		for(int i=0; i<billboards.size();i++) {
			
			double area=billboards.get(i).calculateArea();
			if(area>=160) {
				report+= pos +". Billboard " + billboards.get(i).getBrand() + " with area " + area + "\n";
				pos++;
			}
		}
		return report;
	}
		
	@Override
	public String toString() {
		String msg="";
		for(int i=0; i<billboards.size();i++) {
			msg+=billboards.get(i).getWidth() + "	";
			msg+=billboards.get(i).getHeight() + "	";
			msg+=billboards.get(i).isInUse() + "	";
			msg+=billboards.get(i).getBrand() + "\n";
		}
		return msg;
	}

}
