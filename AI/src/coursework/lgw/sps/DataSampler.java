package coursework.lgw.sps;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;

public class DataSampler {
	private int windowsize;
	ArrayList<ArrayList<Double>> helixVectorsTrain, sheetVectorsTrain,
			coilVectorsTrain, helixVectorsTest, sheetVectorsTest,
			coilVectorsTest;
	ArrayList<ArrayList<Integer>> helixClassTrain, sheetClassTrain,
			coilClassTrain, helixClassTest, sheetClassTest, coilClassTest;
	ArrayList<ArrayList<Double>> trainingSet,testSet;
	ArrayList<ArrayList<String>> tempVecs;
	ArrayList<String> tempResults;
	int tempCounter;
	public static final String HELIX = "h", SHEET = "e", COIL = "_",
			START = "<>", END = "<end>";
	private String train = "Data" + File.separator + "train";
	private String test = "Data" + File.separator + "test";

	public DataSampler(int windowsize) {
		this.windowsize = windowsize;
		helixVectorsTrain = new ArrayList<ArrayList<Double>>();
		sheetVectorsTrain = new ArrayList<ArrayList<Double>>();
		coilVectorsTrain = new ArrayList<ArrayList<Double>>();
		helixVectorsTest = new ArrayList<ArrayList<Double>>();
		sheetVectorsTest = new ArrayList<ArrayList<Double>>();
		coilVectorsTest = new ArrayList<ArrayList<Double>>();
		helixClassTrain = new ArrayList<ArrayList<Integer>>();
		sheetClassTrain = new ArrayList<ArrayList<Integer>>();
		coilClassTrain = new ArrayList<ArrayList<Integer>>();
		helixClassTest = new ArrayList<ArrayList<Integer>>();
		sheetClassTest = new ArrayList<ArrayList<Integer>>();
		coilClassTest = new ArrayList<ArrayList<Integer>>();
		trainingSet=new ArrayList<ArrayList<Double>>();
		testSet = new ArrayList<ArrayList<Double>>();
	}

	public void run() {
		buildData(train, this.helixVectorsTrain, this.sheetVectorsTrain,
				this.coilVectorsTrain);
		buildData(test, this.helixVectorsTest, this.sheetVectorsTest,
				this.coilVectorsTest);
		buildSampleSets();		
	}

	private void buildSampleSets() {
		int smallest=sheetVectorsTrain.size();
		if(smallest>helixVectorsTrain.size())smallest=helixVectorsTrain.size();
		if(smallest>coilVectorsTrain.size())smallest=coilVectorsTrain.size();
		LinkedList<ArrayList<Double>> st = getRandomVectors(sheetVectorsTrain, smallest);
		LinkedList<ArrayList<Double>> ht = getRandomVectors(helixVectorsTrain, smallest);
		LinkedList<ArrayList<Double>> ct = getRandomVectors(coilVectorsTrain, smallest);
		while(st.size()>0||ht.size()>0||ct.size()>0){
			int g = (int)(3*Math.random());
			
			switch(g){
			case 0:				
				if(st.size()>0){trainingSet.add(st.remove((int)(st.size()*Math.random())));
				ArrayList<Integer> temp = new ArrayList<Integer>();
				temp.add(0);
				helixClassTrain.add(temp);
				temp.clear();
				temp.add(0);
				coilClassTrain.add(temp);
				temp.clear();
				temp.add(1);
				sheetClassTrain.add(temp);}
				break;
			case 1:				
				if(ht.size()>0){trainingSet.add(ht.remove((int)(ht.size()*Math.random())));
				ArrayList<Integer> temp = new ArrayList<Integer>();
				temp.add(1);
				helixClassTrain.add(temp);
				temp.clear();
				temp.add(0);
				coilClassTrain.add(temp);
				temp.clear();
				temp.add(0);
				sheetClassTrain.add(temp);}
				break;
			case 2:
				if(ct.size()>0){trainingSet.add(ct.remove((int)(ct.size()*Math.random())));
				ArrayList<Integer> temp = new ArrayList<Integer>();
				temp.add(0);
				helixClassTrain.add(temp);
				temp.clear();
				temp.add(1);
				coilClassTrain.add(temp);
				temp.clear();
				temp.add(0);
				sheetClassTrain.add(temp);}
				break;
			default:
				break;				
			}
		}
		smallest=sheetVectorsTest.size();
		if(smallest>helixVectorsTest.size())smallest=helixVectorsTest.size();
		if(smallest>coilVectorsTest.size())smallest=coilVectorsTest.size();
		st = getRandomVectors(sheetVectorsTest, smallest);
		ht = getRandomVectors(helixVectorsTest, smallest);
		ct = getRandomVectors(coilVectorsTest, smallest);
		while(st.size()>0||ht.size()>0||ct.size()>0){
			int g = (int)(3*Math.random());			
			switch(g){
			case 0:				
				if(st.size()>0){testSet.add(st.remove((int)(st.size()*Math.random())));
				ArrayList<Integer> temp = new ArrayList<Integer>();
				temp.add(0);
				helixClassTest.add(temp);
				temp.clear();
				temp.add(0);
				coilClassTest.add(temp);
				temp.clear();
				temp.add(1);
				sheetClassTest.add(temp);}
				break;
			case 1:				
				if(ht.size()>0){testSet.add(ht.remove((int)(ht.size()*Math.random())));
				ArrayList<Integer> temp = new ArrayList<Integer>();
				temp.add(1);
				helixClassTest.add(temp);
				temp.clear();
				temp.add(0);
				coilClassTest.add(temp);
				temp.clear();
				temp.add(0);
				sheetClassTest.add(temp);}
				break;
			case 2:
				if(ct.size()>0){testSet.add(ct.remove((int)(ct.size()*Math.random())));
				ArrayList<Integer> temp = new ArrayList<Integer>();
				temp.add(0);
				helixClassTest.add(temp);
				temp.clear();
				temp.add(1);
				coilClassTest.add(temp);
				temp.clear();
				temp.add(0);
				sheetClassTest.add(temp);}
				break;
			default:
				break;				
			}
		}		
	}
	
	

	private LinkedList<ArrayList<Double>> getRandomVectors(
			ArrayList<ArrayList<Double>> vectors, int amount) {
		LinkedList<ArrayList<Double>> result = new LinkedList<ArrayList<Double>>();
		LinkedList<ArrayList<Double>> vecs = new LinkedList<ArrayList<Double>>();		
		for(int i=0;i<vectors.size();i++){			
			vecs.add(vectors.get(i));
		}
		for (int j = 0; j < amount; j++) {
			int k =(int)(vecs.size()*Math.random());
			result.add(vecs.remove(k));
		}
		return result;
	}

	private void buildData(String filepath,
			ArrayList<ArrayList<Double>> helixVec,
			ArrayList<ArrayList<Double>> sheetVec,
			ArrayList<ArrayList<Double>> coilVec) {
		LinkedList<String> window = new LinkedList<String>();
		tempVecs = new ArrayList<ArrayList<String>>();
		tempResults = new ArrayList<String>();
		tempCounter = 0;
		try {
			FileInputStream fstream = new FileInputStream(filepath);
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			boolean inProtein = false;
			// Read File Line By Line
			while ((strLine = br.readLine()) != null) {
				String line = strLine.trim();				
				if (line.equals(START)) {
					inProtein = true;
				} 
				else if (inProtein) {
					if (line.equals(END)) {
						window.clear();
						inProtein = false;
					} else {
						window.add(line);
						if (window.size() == windowsize) {
							analyseSegment(window);
						}
						else if (window.size() > windowsize) {
							window.poll();
							analyseSegment(window);

						}
					}
				}
			}
			// Close the input stream
			in.close();
		} catch (Exception e) {			
			e.printStackTrace();
		}
		populateVectorsAndResults(helixVec, sheetVec, coilVec);
	}

	private void analyseSegment(LinkedList<String> segment) {
		ArrayList<String> vec = new ArrayList<String>();
		for (int i = 0; i < segment.size(); i++) {
			String line = segment.get(i);
			String amino = line.substring(0, 1);
			vec.add(amino);
			if (i == windowsize / 2 + 1)
				tempResults.add(line.substring(1));
		}
		tempVecs.add(vec);
	}

	private void populateVectorsAndResults(
			ArrayList<ArrayList<Double>> helixVec,
			ArrayList<ArrayList<Double>> sheetVec,
			ArrayList<ArrayList<Double>> coilVec) {		
		for (int i = 0; i < tempVecs.size(); i++) {
			ArrayList<String> sVec = tempVecs.get(i);
			String c = tempResults.get(i).trim();
			if (c.equals(HELIX)) {
				helixVec.add(getWindowVector(sVec)); 
			} else if (c.equals(SHEET)) {
				sheetVec.add(getWindowVector(sVec));
			} else if (c.equals(COIL)) {
				coilVec.add(getWindowVector(sVec));
			}
		}
	}

	private ArrayList<Double> getWindowVector(ArrayList<String> sVec) {
		ArrayList<Double> result = new ArrayList<Double>();
		for (int i = 0; i < sVec.size(); i++) {
			String amino = sVec.get(i);
			result.addAll(getAminoVector(amino));
		}
		return result;
	}

	private ArrayList<Double> getAminoVector(String a) {
		String aa = a.trim().toUpperCase();
		if (aa.equals("A")) {
			return makeVector(0);
		} else if (aa.equals("C")) {
			return makeVector(1);
		} else if (aa.equals("D")) {
			return makeVector(2);
		} else if (aa.equals("E")) {
			return makeVector(3);
		} else if (aa.equals("F")) {
			return makeVector(4);
		} else if (aa.equals("G")) {
			return makeVector(5);
		} else if (aa.equals("H")) {
			return makeVector(6);
		} else if (aa.equals("I")) {
			return makeVector(6);
		} else if (aa.equals("K")) {
			return makeVector(8);
		} else if (aa.equals("L")) {
			return makeVector(9);
		} else if (aa.equals("M")) {
			return makeVector(10);
		} else if (aa.equals("N")) {
			return makeVector(11);
		} else if (aa.equals("P")) {
			return makeVector(12);
		} else if (aa.equals("Q")) {
			return makeVector(13);
		} else if (aa.equals("R")) {
			return makeVector(14);
		} else if (aa.equals("S")) {
			return makeVector(15);
		} else if (aa.equals("T")) {
			return makeVector(16);
		} else if (aa.equals("V")) {
			return makeVector(17);
		} else if (aa.equals("W")) {
			return makeVector(18);
		} else if (aa.equals("Y")) {
			return makeVector(19);
		}
		return null;
	}

	private ArrayList<Double> makeVector(int on) {
		ArrayList<Double> vec = new ArrayList<Double>();
		for (int i = 0; i < 20; i++) {
			if (i != on)
				vec.add((double) 0);
			else
				vec.add((double) 1);
		}
		return vec;
	}

	public ArrayList<ArrayList<Integer>> getHelixClassTrain() {
		return helixClassTrain;
	}

	public ArrayList<ArrayList<Integer>> getSheetClassTrain() {
		return sheetClassTrain;
	}

	public ArrayList<ArrayList<Integer>> getCoilClassTrain() {
		return coilClassTrain;
	}

	public ArrayList<ArrayList<Integer>> getHelixClassTest() {
		return helixClassTest;
	}

	public ArrayList<ArrayList<Integer>> getSheetClassTest() {
		return sheetClassTest;
	}

	public ArrayList<ArrayList<Integer>> getCoilClassTest() {
		return coilClassTest;
	}

	public ArrayList<ArrayList<Double>> getTrainingSet() {
		return trainingSet;
	}

	public ArrayList<ArrayList<Double>> getTestSet() {
		return testSet;
	}
	
	

}
