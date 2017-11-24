package readdocs;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import Jama.Matrix;
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
			int count = 0;
			for (int i = 0; i < this.rowNum; i++) {
				String tmp = input.nextLine();
				JSONArray js = JSONArray.fromObject(tmp);
				for (int j = 0; j < this.columnNum; j++) {
					array[i][j] = js.getInt(j);
					if (max < array[i][j]) {
						max = array[i][j];
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
			System.out.println("count:"+count);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		DoSVD ds1 = new DoSVD();
		ds1.readMatrix("ext/matrix.txt");
	}
}
