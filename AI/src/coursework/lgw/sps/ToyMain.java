package coursework.lgw.sps;

import java.util.ArrayList;

public class ToyMain {

	public static void main(String[] args) {
		ArrayList<ArrayList<Double>> trainInput = new ArrayList<ArrayList<Double>>();
		ArrayList<Double> temp = new ArrayList<Double>();
		temp.add(196.0);
		temp.add(123.0);
		trainInput.add(temp);
		ArrayList<Double> temp2= new ArrayList<Double>();
		temp2.add(182.0);
		temp2.add(82.0);
		trainInput.add(temp2);
		ArrayList<Double> temp3= new ArrayList<Double>();
		temp3.add(176.0);
		temp3.add(82.0);
		trainInput.add(temp3);
		ArrayList<Double> temp4= new ArrayList<Double>();
		temp4.add(169.0);
		temp4.add(105.0);
		trainInput.add(temp4);
		ArrayList<Double> temp5= new ArrayList<Double>();
		temp5.add(155.0);
		temp5.add(95.0);
		trainInput.add(temp5);
		ArrayList<Double> temp6= new ArrayList<Double>();
		temp6.add(147.0);
		temp6.add(70.0);
		trainInput.add(temp6);
		ArrayList<Double> temp7= new ArrayList<Double>();
		temp7.add(150.0);
		temp7.add(50.0);
		trainInput.add(temp7);
		ArrayList<Double> temp8= new ArrayList<Double>();
		temp8.add(170.0);
		temp8.add(125.0);
		trainInput.add(temp8);
		ArrayList<Double> temp9= new ArrayList<Double>();
		temp9.add(185.0);
		temp9.add(95.0);
		trainInput.add(temp9);
		ArrayList<Double> temp10= new ArrayList<Double>();
		temp10.add(190.0);
		temp10.add(63.0);
		trainInput.add(temp10);
		

		ArrayList<ArrayList<Integer>> trainexpected = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> temp11 = new ArrayList<Integer>();

		temp11.add(1);
		trainexpected.add(temp11);
		ArrayList<Integer> temp12= new ArrayList<Integer>();
		temp12.add(0);
		trainexpected.add(temp12);
		ArrayList<Integer> temp13= new ArrayList<Integer>();
		temp13.add(0);
		trainexpected.add(temp13);
		ArrayList<Integer> temp14= new ArrayList<Integer>();
		temp14.add(1);
		trainexpected.add(temp14);
		ArrayList<Integer> temp15= new ArrayList<Integer>();
		temp15.add(1);
		trainexpected.add(temp15);
		ArrayList<Integer> temp16= new ArrayList<Integer>();
		temp16.add(1);
		trainexpected.add(temp16);
		ArrayList<Integer> temp17= new ArrayList<Integer>();
		temp17.add(0);
		trainexpected.add(temp17);
		ArrayList<Integer> temp18= new ArrayList<Integer>();
		temp18.add(1);
		trainexpected.add(temp18);
		ArrayList<Integer> temp19= new ArrayList<Integer>();
		temp19.add(0);
		trainexpected.add(temp19);
		ArrayList<Integer> temp20= new ArrayList<Integer>();
		temp20.add(0);
		trainexpected.add(temp20);
		

		ArrayList<ArrayList<Double>> testInput = new ArrayList<ArrayList<Double>>();
		ArrayList<Double> temp21= new ArrayList<Double>();
		temp21.add(199.0);
		temp21.add(130.0);
		testInput.add(temp21);
		ArrayList<Double> temp22= new ArrayList<Double>();
		temp22.add(186.0);
		temp22.add(88.0);
		testInput.add(temp22);
		ArrayList<Double> temp23= new ArrayList<Double>();
		temp23.add(172.0);
		temp23.add(117.0);
		testInput.add(temp23);
		ArrayList<Double> temp24= new ArrayList<Double>();
		temp24.add(149.0);
		temp24.add(70.0);
		testInput.add(temp24);
		ArrayList<Double> temp25= new ArrayList<Double>();
		temp25.add(151.0);
		temp25.add(57.0);
		testInput.add(temp25);
		ArrayList<Double> temp26= new ArrayList<Double>();
		temp26.add(163.0);
		temp26.add(76.0);
		testInput.add(temp26);
		ArrayList<Double> temp27= new ArrayList<Double>();
		temp27.add(177.0);
		temp27.add(50.0);
		testInput.add(temp27);
		ArrayList<Double> temp28= new ArrayList<Double>();
		temp28.add(191.0);
		temp28.add(99.0);
		testInput.add(temp28);
		ArrayList<Double> temp29= new ArrayList<Double>();
		temp29.add(158.0);
		temp29.add(95.0);
		testInput.add(temp29);
		ArrayList<Double> temp30= new ArrayList<Double>();
		temp30.add(165.0);
		temp30.add(128.0);
		testInput.add(temp30);
		

		ArrayList<ArrayList<Integer>> testexpected = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> temp31=new ArrayList<Integer>();
		temp31.add(1);
		testexpected.add(temp31);
		ArrayList<Integer> temp32= new ArrayList<Integer>();
		temp32.add(0);
		testexpected.add(temp32);
		ArrayList<Integer> temp33= new ArrayList<Integer>();
		temp33.add(1);
		testexpected.add(temp33);
		ArrayList<Integer> temp34= new ArrayList<Integer>();
		temp34.add(1);
		testexpected.add(temp34);
		ArrayList<Integer> temp35= new ArrayList<Integer>();
		temp35.add(0);
		testexpected.add(temp35);
		ArrayList<Integer> temp36= new ArrayList<Integer>();
		temp36.add(0);
		testexpected.add(temp36);
		ArrayList<Integer> temp37= new ArrayList<Integer>();
		temp37.add(0);
		testexpected.add(temp37);
		ArrayList<Integer> temp38= new ArrayList<Integer>();
		temp38.add(0);
		testexpected.add(temp38);
		ArrayList<Integer> temp39= new ArrayList<Integer>();
		temp39.add(1);
		testexpected.add(temp39);
		ArrayList<Integer> temp40= new ArrayList<Integer>();
		temp40.add(1);
		testexpected.add(temp40);		

		Perceptron ai = new Perceptron(2, 1);

		// train
		for (int j = 0; j < 1000; j++) {
			for (int i = 0; i < trainInput.size(); i++) {
				ai.train(trainInput.get(i), trainexpected.get(i));
			}
		}
		// test
		for (int k = 0; k < testInput.size(); k++) {
			System.out.println("Test: " + (k + 1) + " Predict: "
					+ ai.predict(testInput.get(k))[0] + " Expected: "
					+ testexpected.get(k).get(0));
		}
		System.out.println("done");

	}
}
