package readdocs;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import Jama.Matrix;

public class CreateMatrix {
	String pathU = "model/U.txt";
	String pathS = "model/S.txt";
	String pathV = "model/V.txt";
	double[][] U;
	double[][] V;
	double[] S;
	double[][] Sm;
	Matrix Us, Vs, Ss, ans;
	CreateMatrix() {
		this.loadModel();
	}
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
			for (int i = 0; i < sizeSx; i++) {
				System.out.println(Sm[i][i]);
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
	
	//计算term-term matrix,结果放入到ans矩阵中
	public void createTerm2TermMatrix(int k) {
		if (this.Us == null || this.Us.getColumnDimension() != k) {
			this.Us = new Matrix(this.U.length,k);
			for (int i = 0; i < this.U.length; i++) {
				for (int j = 0; j < k; j++) {
					this.Us.set(i, j, this.U[i][j]);
				}
			}
		}
		if (this.Ss == null || this.Ss.getColumnDimension() != k) {
			this.Ss = new Matrix(k, k, 0.0);
			for (int i = 0; i < k; i++) {
				this.Ss.set(i, i, this.Sm[i][i]);
			}
		}
		this.ans = this.Us.times(Ss).times(Ss).times(Us.transpose());
	}
	
	//计算doc-doc matrix,结果放入到ans矩阵中
	public void createDoc2DocMatrix(int k) {
		if (this.Vs == null || this.Vs.getColumnDimension() != k) {
			this.Vs = new Matrix(this.V.length,k);
			for (int i = 0; i < this.V.length; i++) {
				for (int j = 0; j < k; j++) {
					this.Vs.set(i, j, this.V[i][j]);
				}
			}
		}
		if (this.Ss == null || this.Ss.getColumnDimension() != k) {
			this.Ss = new Matrix(k, k, 0.0);
			for (int i = 0; i < k; i++) {
				this.Ss.set(i, i, this.Sm[i][i]);
			}
		}
		this.ans = this.Vs.times(Ss).times(Ss).times(Vs.transpose());
	}	
	public static void main(String[] args) {
		CreateMatrix cm1 = new CreateMatrix();
		cm1.createDoc2DocMatrix(2);
		System.out.println(cm1.ans.get(0, 1));
		System.out.println(cm1.ans.get(0, 0));
		System.out.println(cm1.ans.get(1, 1));
		cm1.createDoc2DocMatrix(cm1.Sm.length);
		System.out.println(cm1.ans.get(0, 1));
		System.out.println(cm1.ans.get(0, 0));
		System.out.println(cm1.ans.get(1, 1));
		cm1.createDoc2DocMatrix(3);
		System.out.println(cm1.ans.get(0, 1));
		System.out.println(cm1.ans.get(0, 0));
		System.out.println(cm1.ans.get(1, 1));
	}
}
