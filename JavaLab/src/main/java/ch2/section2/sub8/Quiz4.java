package ch2.section2.sub8;

import java.util.*;

public class Quiz4 {
	public static void main(String[] args) {

		int num;

		Scanner scanner = new Scanner(System.in);

		System.out.print("Input number: ");

		num = scanner.nextInt();

		boolean check = true;

		for(int i = 2; i <= num; i++){
			check = true;
			for(int j = 2; j < i; j++){
				if(i % j == 0){
					check = false;
					break;
				}
			}
			if(check){
				System.out.print(i + " ");
			}
		}
	}
}
