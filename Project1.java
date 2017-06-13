package cs241_ass1;

import java.util.Scanner;

/**
 * CS 241 Project 1
 * A application that performs the what Project 1 requires.
 * @author liang dong
 *
 */
public class Project1 {

	public static void main(String[] args) {


		BinarySearchTree tree = new BinarySearchTree();
		Scanner sc = new Scanner(System.in);
		String prompt = "Please enter the initial sequence of values:";
		System.out.println(prompt);
		tree = new BinarySearchTree();
		String string = sc.nextLine();
		int input;
		Scanner scan = new Scanner(string);
		while(scan.hasNext()) {
			tree.addWithRec(scan.nextInt());		
		}
		System.out.print("\nPre-order: ");
		tree.preOrderTraverse();
		System.out.print("\nIn-order: ");
		tree.inOrderTraverse();
		System.out.print("\nPost-order: ");
		tree.postOrderTraverse();
		scan.close();
		String command;
		do{
			System.out.print("\nCommand? ");
			command = sc.next();
			command = command.toLowerCase();
			switch(command) {
				case "h" : 
					System.out.println("I  Insert a value"
						+ "\nD  Delete a value"
						+ "\nP  Find predecessor"
						+ "\nS  Find successor"
						+ "\nE  Exit the program"
						+ "\nH  Display this message ");
					break;
				case "i" : 
					input = sc.nextInt();
					if(tree.addWithRec(input)) {
						System.out.print("In-order: ");
						tree.inOrderTraverse();
					}
					else {
						System.out.println(input + " already exists, ignore.");
					}
					break;
				case "d" : 
					input = sc.nextInt();
					if(tree.removeWithRec(input)) {
						System.out.print("In-order: ");
						tree.inOrderTraverse();
					}
					else {
						System.out.println(input + " doesn't exist!");
					}
					break;
				case "p" : 
					System.out.println(tree.getPredecessor(sc.nextInt()));
					break;
				case "s" : 
					System.out.println(tree.getSuccessor(sc.nextInt()));
					break;
				case "e" :
					sc.close();
					System.out.println("Thank you for using my program!");
					System.exit(0);
			}
		}while(true);
	
	}

}
