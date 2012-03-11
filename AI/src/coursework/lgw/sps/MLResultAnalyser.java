package coursework.lgw.sps;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class MLResultAnalyser {
	private static int windowsize=13;
	
	public static void main(String[] args){
		analyze("Results"+File.separator+"w13-13phl-1hl-i100.csv");
	}
	
	private static void analyze(String filepath){
		double fp=0,tp=0,fn=0,tn=0,cp=0,hp=0,sp=0,count=0;
		try {
			FileInputStream fstream = new FileInputStream(filepath);
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;			
			// Read File Line By Line
			while ((strLine = br.readLine()) != null) {
				if(!strLine.contains("AA")){
					String[] values = strLine.split(",");
					String c = values[20*windowsize+1];
					String p = values[20*windowsize+2];
					if(c.equals(p)){
						if(c.equals("\"helix\""))hp++;
						if(c.equals("\"coil\""))cp++;
						if(c.equals("\"sheet\""))sp++;
					}
				}
				count++;
			}
			// Close the input stream
			in.close();
		} catch (Exception e) {			
			e.printStackTrace();
		}
		double q3=(hp+sp+cp)/(count-1);
		System.out.println("Q3: "+q3);
	}

}
