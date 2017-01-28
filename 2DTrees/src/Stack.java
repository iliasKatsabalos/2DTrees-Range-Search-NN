

import java.io.PrintStream;
import java.util.NoSuchElementException;


public class Stack<I> implements StackInterface<I> {
	private int N;
	public Node<I> head;
	String name;
	
	public Stack(){
		this("Stack");
	}
	
	public Stack(String name){
		this.name = name;
		head = null;
		N = 0;
	}
	
	public boolean isEmpty(){
		return head==null;
	}
	
	public void push(I item){
		Node<I> p = head;
		head = new Node<I>(item);
		head.item = item;
		head.next = p;
		N++;
	}
	
	public I pop(){
		if (isEmpty())
			throw new NoSuchElementException ("stack underFlow");
		I k = head.item;
		head = head.next;
		N--;
		return k;
	}
	
	public I peek(){
		if (isEmpty())
			throw new NoSuchElementException ("stack underFlow");
		return head.item;
	}
	
	public int size(){
		return N;
	}
	
	public void printStack(PrintStream stream){
		if (isEmpty()) {
			System.out.printf("Empty %s\n", name);
			return;
		} // end if

		System.out.printf(name);
		Node<I> p = head;

		// while not at end of list, output p node's item
		while (p != null) {
			System.out.printf("%s ", p.item);
			p = p.next;
		} // end while

		System.out.println("\n");
	}
	
}