package readdocs;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

import Jama.Matrix;

public class Analysis {
	String pathU = "model/U.txt";
	String pathS = "model/S.txt";
	String pathV = "model/V.txt";
	double[][] U;
	double[][] V;
	double[] S;
	double[][] Sm;
	Matrix Us, Vs, Ss, ans;
	ArrayList<ArrayList<Double>> resultPath = new ArrayList<ArrayList<Double>>();
	
	void loadModel() {
		try {
			Scanner input = new Scanner(new File(pathS));
			int sizeSx = input.nextInt();
			int sizeSy = input.nextInt();
			Sm = new double [sizeSx][sizeSy];
			for (int i = 0; i < sizeSx; i++) {
				for (int j = 0; j < sizeSy; j++) {
					Sm[i][j] = input.nextDouble();
				}
				if ((i+1)%200 == 0) {
					System.out.println("process@"+(i+1));
				}
			}
		} catch (IOException e) {
			System.out.println("cannot find the file.");
		}
		try {
			Scanner input = new Scanner(new File(pathU));
			int sizeUx = input.nextInt();
			int sizeUy = input.nextInt();
			U = new double [sizeUx][sizeUy];
			for (int i = 0; i < sizeUx; i++) {
				for (int j = 0; j < sizeUy; j++) {
					U[i][j] = input.nextDouble();
					if (i == 0 && j == 0) {
						System.out.println("U["+i+","+j+"]:"+U[i][j]*1000);
					}
				}
				if ((i+1)%1000 == 0) {
					System.out.println("process@"+(i+1));
				}
			}
		} catch (IOException e) {
			System.out.println("cannot find the file.");
		}
		try {
			Scanner input = new Scanner(new File(pathV));
			int sizeVx = input.nextInt();
			int sizeVy = input.nextInt();
			V = new double [sizeVx][sizeVy];
			for (int i = 0; i < sizeVx; i++) {
				for (int j = 0; j < sizeVy; j++) {
					V[i][j] = input.nextDouble();
				}
				if ((i+1)%200 == 0) {
					System.out.println("process@"+(i+1));
				}
			}
		} catch (IOException e) {
			System.out.println("cannot find the file.");
		}
		System.out.println("succeed load model!");
	}
	void getMatrix() {
		System.out.println("start calulate matrix");
		Us = new Matrix(U);
		Ss = new Matrix(Sm);
		Vs = new Matrix(V);
		Vs = Vs.transpose();
		ans = Us.times(Ss).times(Vs);
		System.out.println("end calulate matrix");
	}
	void getChangePath(int term, int docs) {
		
		//System.out.println(ans.get(term, docs));
		double sums = 0.0;
		ArrayList<Double> result = new ArrayList<Double>();
		for (int i = 0; i < Sm.length; i++) {
			sums += U[term][i] * Sm[i][i] * V[docs][i];
			result.add(sums);
			//System.out.println(i+":"+sums);
		}
		this.resultPath.add(result);
		System.out.println(sums);
		
	}
	
	void saveResultPath(String path) {
		try {
			PrintStream out = new PrintStream(new File(path));
			if (this.resultPath.size() == 0) return;
			for (int i = 0; i < Sm.length; i++) {
				for (int j = 0; j < this.resultPath.size(); j++) {
					if (j != this.resultPath.size()-1) {
						out.print(this.resultPath.get(j).get(i)+" ");
					} else {
						out.println(this.resultPath.get(j).get(i));
					}
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		Analysis an1 = new Analysis();
		an1.loadModel();
		//an1.getMatrix();
		boolean isZero = true;
		if (isZero) {
			an1.getChangePath(17335, 0); //蝴蝶/n-1
			an1.getChangePath(10345, 1); //早已/d-2
			an1.saveResultPath("model/result2.txt");
		} else {
			an1.getChangePath(18942, 0); //中国/ns-1
			an1.getChangePath(17339, 0); //一九九七年/t-1
			an1.saveResultPath("model/result1.txt");
		}
		
		
	}
}
