
public class Node<I> {
	public I item;
	public Node<I> next;
	
	Node(I item){
		this.item = item;
		this.next = null; 
	}
	
	Node(I item, Node<I> next){
		this.item = item;
		this.next = next;
	}
	
	I getObject(){
		return item;
	}
	
	Node<I> getNext(){
		return next;
	}
}