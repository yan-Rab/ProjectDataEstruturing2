package calculator;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	public static ArrayList<String> splitNumber(String number) {
		ArrayList<String> list = new ArrayList<String>();
		int breakIndex = 2;
		
		for (int endIndex = number.length(); endIndex > 0;) {
			int beginIndex = endIndex - breakIndex >= 0 ? endIndex - breakIndex : endIndex - 1;
			list.add(number.substring(beginIndex, endIndex));
			endIndex = beginIndex;
		}
		
		return list;
	}
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Informe o primeiro número => ");
		String n1 = scanner.nextLine();
		
		System.out.println("Informe o segundo número => ");
		String n2 = scanner.nextLine();
		scanner.close();
		
		ArrayList<String> firstList = n1.length() <= n2.length() ? splitNumber(n1) : splitNumber(n2);
		ArrayList<String> secondList = n2.length() >= n1.length() ? splitNumber(n2) : splitNumber(n1);
		
		ArrayList<ArrayList<String>> stackResults = new ArrayList<ArrayList<String>>();
		 
		for (int i = 0; i < firstList.size(); i++) {
			int rest = 0;
			ArrayList<String> stackOperation = new ArrayList<String>();
			
			for (int j = 0; j < i; j++) {
				stackOperation.add("0");
			}
			
			for (int j = 0; j < secondList.size(); j++) {
				int operation = Integer.parseInt(firstList.get(i)) * Integer.parseInt(secondList.get(j));
				
				if(rest != 0) {
					operation += rest;
					rest = 0;
				}

				ArrayList<String> splitOperation = splitNumber(Integer.toString(operation));
				
				stackOperation.add(splitOperation.get(0));
				
				if(splitOperation.size() > 1) {
					rest = Integer.parseInt(splitOperation.get(1));
				}
				
				if(j == secondList.size() - 1)
					stackOperation.add(Integer.toString(rest));
			}
			
			if(i != firstList.size() - 1)
				stackOperation.add("0");
			
			stackResults.add(stackOperation);
		}
		
		ArrayList<String> finalResult = new ArrayList<String>();
		
		for (int i = 0; i < stackResults.get(0).size(); i++) {
			finalResult.add("0");
		}
		
		for (int i = 0; i < stackResults.size(); i++) {
			ArrayList<String> item = stackResults.get(i);
			
			for (int j = 0; j < item.size(); j++) {
				int operation = Integer.parseInt(finalResult.get(j)) + Integer.parseInt(item.get(j));
				finalResult.set(j, Integer.toString(operation));
			}
		}
		
		System.out.print(finalResult.toString());
	}
} 
