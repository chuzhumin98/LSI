package readdocs;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Analysis {
	String pathU = "model/U.txt";
	String pathS = "model/S.txt";
	String pathV = "model/V.txt";
	double[][] U;
	double[][] V;
	double[] S;
	
	
	void loadModel() {
		try {
			Scanner input = new Scanner(new File(pathS));
			int sizeS = input.nextInt();
			S = new double [sizeS];
			for (int i = 0; i < sizeS; i++) {
				S[i] = input.nextDouble();
				System.out.println(i+":"+S[i]);
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
				}
				if ((i+1)%1000 == 0) {
					System.out.println("process@"+(i+1));
				}
			}
		} catch (IOException e) {
			System.out.println("cannot find the file.");
		}
		try {
			Scanner input = new Scanner(new File(pathU));
			int sizeVx = input.nextInt();
			int sizeVy = input.nextInt();
			V = new double [sizeVx][sizeVy];
			for (int i = 0; i < sizeVx; i++) {
				for (int j = 0; j < sizeVy; j++) {
					V[i][j] = input.nextDouble();
				}
			}
		} catch (IOException e) {
			System.out.println("cannot find the file.");
		}
		System.out.println("succeed load model!");
	}
	void getChangePath(int term, int docs, int k, String path) {
		if (k > S.length) {
			System.out.println("error for k is out of dim!");
			return;
		}
		
	}
	public static void main(String[] args) {
		Analysis an1 = new Analysis();
		an1.loadModel();
	}
}
