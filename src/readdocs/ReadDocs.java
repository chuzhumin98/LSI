package readdocs;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;


import Jama.Matrix;
import Jama.SingularValueDecomposition;;

public class ReadDocs {
	final static int docsNum = 500;
    private static int depth=1;  
    public static Map<String, ArrayList<Integer>> term_docs = new HashMap<String, ArrayList<Integer>>();
    public static ArrayList<String> fileNames = new ArrayList<String>();
    public static void writeMatrix(String pathName,int depth) throws IOException{  
        int filecount=0;  
        //获取pathName的File对象  
        File dirFile = new File(pathName);  
        //判断该文件或目录是否存在，不存在时在控制台输出提醒  
        if (!dirFile.exists()) {  
            System.out.println("do not exit");  
            return ;  
        }  
        //判断如果不是一个目录，就判断是不是一个文件，时文件则输出文件路径  
        if (!dirFile.isDirectory()) {  
            if (dirFile.isFile()) {  
                System.out.println(dirFile.getCanonicalFile());  
            }  
            return ;  
        }  
          
        for (int j = 0; j < depth; j++) {  
            System.out.print("  ");  
        }  
        System.out.print("|--");  
        System.out.println(dirFile.getName());  
        //获取此目录下的所有文件名与目录名  
        String[] fileList = dirFile.list();  
        int currentDepth=depth+1;  
        for (int i = 0; i < fileList.length; i++) {  
            //遍历文件目录  
            String string = fileList[i];  
            //File("documentName","fileName")是File的另一个构造器  
            File file = new File(dirFile.getPath(),string);  
            String name = file.getName();  
            //如果是一个目录，搜索深度depth++，输出目录名后，进行递归  
            if (file.isDirectory()) {  
                //递归  
                writeMatrix(file.getCanonicalPath(),currentDepth);  
            }else{  
                //如果是文件，则直接输出文件名              	
                if ((filecount+1) % 200 == 0) {
                	for (int j = 0; j < currentDepth; j++) {  
                        System.out.print("   ");  
                    }  
                    System.out.print("|--");  
                    System.out.println(name);  
                }
            	readFile(file, filecount);
            	fileNames.add(name);
                filecount++;
                if (filecount == ReadDocs.docsNum) {
                	return;
                }
            }  
        }  
        System.out.println("filecount:"+filecount);
        System.out.println("termcount:"+term_docs.size());
    }  
    
    static void readFile(File file, int index) {
    	try {
    		InputStreamReader isr = new InputStreamReader(new FileInputStream(file), "GBK");
			BufferedReader br = new BufferedReader(isr);
            String temp = null;
            while ((temp = br.readLine()) != null) {
                String[] strs = temp.split(" ");
                for (int i = 0; i < strs.length; i++) {
                	if (strs[i].length() >= 1 && strs[i].indexOf(0) != ' ') {
                		if (!term_docs.containsKey(strs[i])) {
                			ArrayList<Integer> ar1 = new ArrayList<Integer>();
                			for (int j = 0; j < ReadDocs.docsNum; j++) {
                				ar1.add(0);
                			}
                			term_docs.put(strs[i], ar1);
                			term_docs.get(strs[i]).set(index, 1);
                		} else {
                			int num = term_docs.get(strs[i]).get(index)+1;
                			term_docs.get(strs[i]).set(index, num);
                		}
                	}
                }
            }	
    	} catch (IOException e){
    		System.out.println("something wrong when IO!");
    	}
    }
    
    // 写入映射到文件中，格式：termNum;term;docsNum,docs
    public static void writeMap(String path) { 
        try (PrintStream out = new PrintStream(new File(path))) {
        	out.println(ReadDocs.term_docs.size());
        	for (Map.Entry<String, ArrayList<Integer>> entry : term_docs.entrySet()) {
        		out.println(entry.getKey());
        	}
        	out.println("");
        	out.println(ReadDocs.docsNum);
        	for (int i = 0; i < ReadDocs.fileNames.size(); i++) {
        		out.println(ReadDocs.fileNames.get(i));
        	}
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public static void writeMatrix(String path) {
    	try (PrintStream out = new PrintStream(new File(path))) {
    		out.println(ReadDocs.term_docs.size());
    		out.println(ReadDocs.docsNum);
        	for (Map.Entry<String, ArrayList<Integer>> entry : term_docs.entrySet()) {
        		ArrayList<Integer> al1 = entry.getValue();
        		out.println(al1.toString());
        	}
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
	public static void main(String[] args) throws IOException {
		//writeMatrix("D:/学习/大三上/信息检索/HW3/PKU_corpus",0);
		//writeMap("ext/mapSmall.txt");
		//writeMatrix("ext/matrixSmall.txt");
		// create M-by-N matrix that doesn't have full rank  
	      int M = 8, N = 5;  
	      Matrix B = Matrix.random(5, 3);  
	      Matrix A = Matrix.random(M, N).times(B).times(B.transpose());  
	      System.out.print("A = ");  
	      System.out.println(A.get(0, 1));
	      A.print(A.getRowDimension(), A.getColumnDimension()); 
	      // compute the singular vallue decomposition  
	      System.out.println("A = U S V^T");  
	      System.out.println();  
	      SingularValueDecomposition s = A.svd(); 
	      System.out.print("U = ");  
	      Matrix U = s.getU();  
	      U.print(9, 6);  
	      System.out.print("Sigma = ");  
	      Matrix S = s.getS();  
	      S.print(9, 6);  
	      System.out.print("V = ");  
	      Matrix V = s.getV();  
	      V.print(9, 6);  
	      Matrix Ap = U.times(S).times(V.transpose());
	      Ap.print(9, 9);
	      System.out.println("rank = " + s.rank());  
	      System.out.println("condition number = " + s.cond());  
	      System.out.println("2-norm = " + s.norm2());  
	  
	      // print out singular values  
	      System.out.print("singular values = ");  
	      Matrix svalues = new Matrix(s.getSingularValues(), 1);  
	      svalues.print(9, 6);    
	}
}
