package readdocs;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

import Jama.Matrix;
import Jama.SingularValueDecomposition;
import net.sf.json.JSONArray;

public class DoSVD {
	Matrix term_docsMatrix = null;
	int rowNum = 0;
	int columnNum = 0;
	double[][] array;
	public void readMatrix(String path) {
		try (Scanner input = new Scanner(new File(path))) {
			this.rowNum = input.nextInt();
			this.columnNum = input.nextInt();
			System.out.println("rowNum:"+this.rowNum);
			System.out.println("columnNum:"+this.columnNum);
			array = new double [this.rowNum][this.columnNum];
			String tmp1 = input.nextLine();
			double max = 0;
			int maxRow = -1;
			int maxColumn = -1;
			int count = 0;
			for (int i = 0; i < this.rowNum; i++) {
				String tmp = input.nextLine();
				JSONArray js = JSONArray.fromObject(tmp);
				for (int j = 0; j < this.columnNum; j++) {
					array[i][j] = js.getInt(j);
					if (max < array[i][j]) {
						max = array[i][j];
						maxRow = i;
						maxColumn = j;
					}
					if (array[i][j] > 0) {
						count++;
					}
				}
				if ((i+1) % 500 == 0) {
					System.out.println("process@"+(i+1));
				}
				//System.out.println(js.size()+":"+js);
			}
			System.out.println("max:"+max);
			System.out.println("row:"+maxRow+"  column:"+maxColumn);
			System.out.println("count:"+count);
			this.term_docsMatrix = new Matrix(this.array);	
			System.out.println(this.term_docsMatrix.get(18942, 0));
			System.out.println(this.term_docsMatrix.get(17339, 0));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	void saveSVDModel() {
		//this.array = null;
		long start = System.currentTimeMillis() ;
		SingularValueDecomposition s = this.term_docsMatrix.svd();
        System.out.println("use time "+(System.currentTimeMillis()-start));
        Matrix S = s.getS();
        Matrix U = s.getU();
        Matrix V = s.getV();
        Matrix Ap = U.times(S).times(V.transpose());
        double error = 0.0;
        for (int i = 0; i < Ap.getRowDimension(); i++) {
        	for (int j = 0; j < Ap.getColumnDimension(); j++) {
        		error += Math.abs(this.term_docsMatrix.get(i, j)-Ap.get(i, j));
        	}
        }
        System.out.println("error:"+error);
		try {
			
			for (int i = 0; i < S.getRowDimension(); i++) {
				System.out.println(i+":"+S.get(i, i));
			}
			PrintStream out = new PrintStream(new File("model/S.txt"));
        	out.println(S.getRowDimension());
        	out.println(S.getColumnDimension());
        	for (int i = 0; i < S.getRowDimension(); i++) {
        		for (int j = 0; j < S.getColumnDimension(); j++) {
        			out.print(S.get(i, j)+" ");
        		}
        		out.println();
        	}
        	
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
		try {
			PrintStream out = new PrintStream(new File("model/U.txt"));
        	out.println(U.getRowDimension());
        	out.println(U.getColumnDimension());
        	for (int i = 0; i < U.getRowDimension(); i++) {
        		for (int j = 0; j < U.getColumnDimension(); j++) {
        			out.print(U.get(i, j)+" ");
        		}
        		out.println();
        	}
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
		try {
			
			PrintStream out = new PrintStream(new File("model/V.txt"));
        	out.println(V.getRowDimension());
        	out.println(V.getColumnDimension());
        	for (int i = 0; i < V.getRowDimension(); i++) {
        		for (int j = 0; j < V.getColumnDimension(); j++) {
        			out.print(V.get(i, j)+" ");
        		}
        		out.println();
        	}
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	}
	
	public static void main(String[] args) {
		DoSVD ds1 = new DoSVD();
		ds1.readMatrix("ext/matrixSmall.txt");
		ds1.saveSVDModel();
	}
}
