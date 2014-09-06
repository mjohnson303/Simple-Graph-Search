import java.util.HashMap;

import javax.swing.JOptionPane;


public class GraphSearch {
	private static int[][] a;
	private static double[] thresh;
	private static int[] x;
	private static int count;
	private static HashMap<Integer, String> intMap;
	private static HashMap<String, Integer> stringMap;
	
	public static void main(String[] args){
		getInput();
		count=0;
		intMap = new HashMap<Integer, String>();
		intMap.put(count, arrToString(x));
		stringMap = new HashMap<String, Integer>();
		stringMap.put(arrToString(x), count);
		int[] newX;
		System.out.println("A: \n"+print(a));
		System.out.println("Threshold: "+print(thresh));
		System.out.println("x0: "+print(x));
		while(true){
			newX = calc();
			count++;
			System.out.println("Count: "+count+", "+print(newX));
			if(stringMap.containsKey(arrToString(newX))){
				intMap.put(count, arrToString(newX));
				end(newX);
				break;
			}
			else{
				stringMap.put(arrToString(newX), count);
				intMap.put(count, arrToString(newX));
				x=newX;
			}
		}
	}
	
	public static void getInput(){
		String inputValue = JOptionPane.showInputDialog("How many rows does A have.");
		int arows = Integer.parseInt(inputValue);
		inputValue = JOptionPane.showInputDialog("How many cols does A have.");
		int acols = Integer.parseInt(inputValue);
		a = new int[arows][acols];
		thresh = new double[acols];
		x = new int[acols];
		String[] temp;
		for(int i=0; i<arows; i++){
			inputValue = JOptionPane.showInputDialog("Input row "+(i+1)+" of A separated by commas(no spaces): ");
			temp=inputValue.split(",");
			if(temp.length!=acols){
				error();
			}
			for(int j=0; j<acols; j++){
				a[i][j]=Integer.parseInt(temp[j]);
			}
			
		}
		inputValue = JOptionPane.showInputDialog("Input threshold vector separated by commas(no spaces): ");
		temp=inputValue.split(",");
		if(temp.length!=acols){
			error();
		}
		for(int k=0; k<acols; k++){
			thresh[k]=Double.parseDouble(temp[k]);
		}
		inputValue = JOptionPane.showInputDialog("Input x0 vector separated by commas(no spaces): ");
		temp=inputValue.split(",");
		if(temp.length!=acols){
			error();
		}
		for(int m=0; m<acols; m++){
			x[m]=Integer.parseInt(temp[m]);
		}
	}
	
	public static int[] calc(){
		int[] newX = new int[x.length];
		int dP=0;
		for(int i=0; i<newX.length; i++){
			dP=dotProduct(x,i, newX.length);
			if(dP<thresh[i])
				newX[i]=0;
			else
				newX[i]=1;
		}
		return newX;
	}
	
	public static void error(){
		JOptionPane.showMessageDialog(null, "Bad Input, Closing Program", "Bad Input, Closing Program", JOptionPane.ERROR_MESSAGE);
		System.exit(0);
	}
	
	public static int dotProduct(int[] y, int index, int length){
		int total=0;
		for(int i=0; i<length; i++){
			total+=y[i]*a[index][i];
		}
		System.out.println("dotProduct: "+total);
		return total;
	}
	
	public static void end(int[] arr){
		int m = stringMap.get(arrToString(arr));
		int t = count-m;
		String path="Path: ";
		int i;
		for(i=0; i<count; i++){
			path+="("+intMap.get(i)+"), ";
		}
		path+="("+intMap.get(i)+")";
		
		JOptionPane.showMessageDialog(null, "m: "+m+", t: "+t+"\n"+path, "Finished Cycle", JOptionPane.ERROR_MESSAGE);
	}
	
	public static String print(int[][] arr){
		String s="";
		for(int i=0; i<arr.length; i++){
			s+="{ ";
			for(int j=0; j<arr[0].length; j++){
				s+=arr[i][j]+" ";
			}
			s+="}\n";
		}
		return s;
	}
	
	public static String print(int[] arr){
		String s="{ ";
		for(int i=0; i<arr.length; i++){
			s+=arr[i]+" ";
		}
		s+="}";
		return s;
	}
	
	public static String print(double[] arr){
		String s="{ ";
		for(int i=0; i<arr.length; i++){
			s+=arr[i]+" ";
		}
		s+="}";
		return s;
	}
	
	public static String arrToString(int[] arr){
		String s="";
		int i;
		for(i=0; i<arr.length-1; i++){
			s+=arr[i]+",";
		}
		s+=arr[i];
		return s;
	}
}
