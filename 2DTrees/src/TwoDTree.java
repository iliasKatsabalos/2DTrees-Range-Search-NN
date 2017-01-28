//Authors Ilias Katsabalos, Matthew Kalkounis
//1. This method is responsible for adding new Points to a tree
//1.1 If the root is null we begin by setting the root
//1.2 We start descending the tree with the root as current node.
//1.3 The parent node is assigned with our current since we are going to move deeper
//1.4 our current node becomes the left child in this case
//1.5 if the current node is null then we set the left child of the parent as the new Point inserted
//1.6 We set the level of our new TreeNode
//2 Similar to the add method. we serach for a given point
//2.1 We check for the equality
//3. This method is used in the nearest Neighbor method
//3.1 We descend the tree and find the closest leaf to a given point
//4. The nearest Neighbor method. We begin with finding the closest leaf
//4.1 The distance to our closest leaf
//4.2 we set the right and left rectangle of the root, in order to start the recursion
//4.3 we start the recursion by executing the nearest method
//4.4 The nearest method is the base of our recursion
//4.5 we need to know what is our current TreeNode
//4.6 what point are we searching for
//4.7 what is the best distance found so far
//4.8 what is our parent rectange, who servers as the borders for our new node's rectangles
//4.9 Check the distance to our current TreeNode. If better update	
//4.10 We find the perpendicular distance to the further rectangle. If smaller than min check the other part of the tree
//4.11 We set the new parentRect for the next step of the recursion
//4.12 We update our current Node
//4.13 We set the new rectangle of our current node, using the parentRect as border
//4.14 We execute the recursio//4.15 If perpendicular distance>min we only check one side of the treen with our new current TreeNode
//4.15 If perpendicular distance>min we only check one side of the tree
//4.16 This method finds the perpendicular distance from a point to the further rectangle
//4.17 the further recangle lies on the opposite side from our point, according to the splitting axis
//5. This method finds all the points in a tree that are included in a given rectangle
//5.1 If the rectangle given includes the parent rectangle, the add all the points of the subtree
//5.2 If the userRect contains the current point then add this point to the stack
//5.3 We check if the rectangle intersects with the left and right rectangle of our current TreeNode
//5.4 If both then check both sides, else check only overlapped side
//6 Buffered reader reads the points in a text file
//6.1 The points are added to a list
//6.2 Check the list for duplicate points
//6.3 Begin the mainloop

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.lang.Exception;

public class TwoDTree {
	
	private TreeNode root =null;
	
	public TreeNode root(){
		return this.root;
	}
	
	public void setRoot(TreeNode n){
		this.root = n;
	}
	
	//1. This method is responsible for adding new Points to a tree
	public void add(Point p){
		
		TreeNode node = new TreeNode(p);
		
		//1.1 If the root is null we begin by setting the root
		if (root() == null){
			setRoot(node);
			root.setLevel(0);
			System.out.println(node.toString() + " " + node.getLevel());
		}else{
		
			//1.2 We start descending the tree with the root as current node.
			TreeNode current = root;
			TreeNode parent = null;
			int level = 0; 
			boolean flag = true;
			while(flag){
				//1.3 The parent node is assigned with our current since we are going to move deeper
				parent = current;
				if (node.compareData(p, level)<current.compareData(current, level)){
					//1.4 our current node becomes the left child in this case
					current = current.leftNode();
					//1.5 if the current node is null then we set the left child of the parent as the new Point inserted
					if (current==null){
						parent.sLeftNode(node);
						level++;
						//1.6 We set the level of our new TreeNode
						node.setLevel(level);
						System.out.println(node.toString() + " " + node.getLevel());
						flag = false;
					}
					level++;
				}else{
					current = current.rightNode();
					if (current==null){
						parent.sRightNode(node);
						level++;
						node.setLevel(level);
						System.out.println(node.toString() + " " + node.getLevel());
						flag = false;
					}
					level++;
				}
			} 
		}
	}
	
	public int size(){
		return TreeNode.getSize();
	}

	//2 Similar to the add method. we serach for a given point
	public Boolean search(Point p){
		TreeNode node = new TreeNode(p);
		if (isEmpty()){
			return  false;
		}
		else{
			TreeNode current = root;
			int i = 1;
			while(current!=null){	
				//2.1 We check for the equality
				if (current.equals(p)){
					return true;
				}else{
					if (node.compareData(p,i)<current.compareData(current,i)){
						current = current.leftNode();
					}
					else{
						current = current.rightNode();
					}
				}
				i++;
			}
			return false;
		}
	}
	
	public boolean isEmpty(){
		if (root()==null){
			return true;
		}else{
			return false;
		}
	}
	
	//3. This method is used in the nearest Neighbor method
	public TreeNode findClosestLeaf(TreeNode node){
		if (isEmpty()){
			return null;
		}
		//3.1 We descend the tree and find the closest leaf to a given point
		else{
			int i = 0;
			TreeNode current = root;
			TreeNode parent = null;
			while(true){
				if (node.compareData(node, i)<current.compareData(current, i)){
					parent = current;
					current = current.leftNode();
					if (current == null){
						return parent;
					}
				}else{
					parent = current;
					current = current.rightNode();
					if (current == null){
						return parent;
					}
				}i++;
			}
		}
	}
		
	//4. The nearest Neighbor method. We begin with finding the closest leaf
	public Point nearestNeighbor(Point p){
		TreeNode node = new TreeNode(p);
		TreeNode closestLeaf = findClosestLeaf(node);
		//4.1 The distance to our closest leaf
		double min = p.distanceTo(closestLeaf);
		//4.2 we set the right and left rectangle of the root, in order to start the recursion
		root().sLeftRect(0, root.x(), 0, 100);
		root().sRightRect(root().x(), 100, 0, 100);
		//4.3 we start the recursion by executing the nearest method
		Point better = nearest(root(), p, min, new Rectangle(0,100,0,100));
		if (better!=null){
			return better;
		}else{
			return closestLeaf;
		}
	}
	
	//4.4 The nearest method is the base of our recursion
	//4.5 we need to know what is our current TreeNode
	//4.6 what point are we searching for
	//4.7 what is the best distance found so far
	//4.8 what is our parent rectangle, who servers as the borders for our new node's rectangles
	private Point nearest(TreeNode current, Point p, double min, Rectangle parentRect){
		Point result = null;
		Point newResult = null;
		//4.9 Check the distance to our current TreeNode. If better update	
		double d = p.distanceTo(current);
		if (d<min){
			result = current;
			min = d;
		}
		//4.10 We find the perpendicular distance to the further rectangle. If smaller than min check the other part of the tree
		double dp = distanceToRect(current, p );
		if (dp<min){
			if(current.leftNode()!=null){
				//4.11 We set the new parentRect for the next step of the recursion
				parentRect = current.leftRect();
				//4.12 We update our current Node
				current = current.leftNode();
				if(current.getLevel() % 2 == 0){
					//4.13 We set the new rectangles of our current node, using the parentRect as border
					current.sLeftRect(parentRect.xmin(), current.x(), parentRect.ymin(), parentRect.ymax());
					current.sRightRect(current.x(), parentRect.xmax(), parentRect.ymin(), parentRect.ymax());
				}else{
					current.sLeftRect(parentRect.xmin(), parentRect.xmax(), parentRect.ymin(), current.y());
					current.sRightRect(parentRect.xmin(), parentRect.xmax(),current.y(), parentRect.ymax());
				}
				//4.14 We execute the recursion with our new current TreeNode
				Point pt = nearest(current, p, min, parentRect);
				if (pt!=null){
					if(pt.distanceTo(p)<min){
						newResult = pt; 
						min = pt.distanceTo(p);
					}
				}
			}
			if (current.rightNode()!=null){
				parentRect = current.rightRect();
				current = current.rightNode();
				if(current.getLevel() % 2 == 0){
					current.sLeftRect(parentRect.xmin(), current.x(), parentRect.ymin(), parentRect.ymax());
					current.sRightRect(current.x(), parentRect.xmax(), parentRect.ymin(), parentRect.ymax());
				}else{
					current.sLeftRect(parentRect.xmin(), parentRect.xmax(), parentRect.ymin(), current.y());
					current.sRightRect(parentRect.xmin(), parentRect.xmax(),current.y(), parentRect.ymax());
				}
				Point pt = nearest(current, p, min, parentRect);
				if (pt!=null){
					if(pt.distanceTo(p)<min){
						newResult = pt; 
						min = pt.distanceTo(p);
					}
				}
			}
		//4.15 If perpendicular distance>min we only check one side of the tree
		}else{
			if(current.getLevel() % 2 == 0 ){
				if (p.x()<current.x()){
					if(current.leftNode()!=null){
						parentRect = current.leftRect();
						current = current.leftNode();
						current.sLeftRect(parentRect.xmin(), parentRect.xmax(), parentRect.ymin(), current.y());
						current.sRightRect(parentRect.xmin(), parentRect.xmax(), current.y(), parentRect.ymax());
						newResult = nearest(current, p, min, parentRect);
					}
				}else{
					if(current.rightNode()!=null){
						parentRect = current.rightRect();
						current = current.rightNode();
						current.sLeftRect(parentRect.xmin(), parentRect.xmax(), parentRect.ymin(), current.y());
						current.sRightRect(parentRect.xmin(), parentRect.xmax(), current.y(), parentRect.ymax());
						newResult = nearest(current, p, min, parentRect);
					}
				}
			}else{
				if (p.y()<current.y()){
					if(current.leftNode()!=null){
						parentRect = current.leftRect();
						current = current.leftNode();
						current.sLeftRect(parentRect.xmin(), current.x(), parentRect.ymin(), parentRect.ymax());
						current.sRightRect(current.x(), parentRect.xmax(), parentRect.ymin(), parentRect.ymax());
						newResult = nearest(current, p, min, parentRect);
					}
				}else{
					if(current.rightNode()!=null){
						parentRect = current.rightRect();
						current = current.rightNode();
						current.sLeftRect(parentRect.xmin(), current.x(), parentRect.ymin(), parentRect.ymax());
						current.sRightRect(current.x(), parentRect.xmax(), parentRect.ymin(), parentRect.ymax());
						newResult = nearest(current, p, min, parentRect);
					}
				}
			}
			
		}
		if (newResult!=null){return newResult;};
		return result;
	}
	
	//4.16 This method finds the perpendicular distance from a point to the further rectangle
	//4.17 the further recangle lies on the opposite side from our point, according to the splitting axis
	private static double distanceToRect(TreeNode node, Point p) {
		TreeNode point = new TreeNode(p);
		double distance;
		Rectangle furtherRect = null;
		if (point.compareData(p, point.getLevel())<node.compareData(node, node.getLevel())){
			furtherRect = node.rightRect();
		}else{
			furtherRect = node.leftRect();
		}
		distance = furtherRect.distanceTo(p);
		return distance;
	}

	//5. This method finds all the points in a tree that are included in a given rectangle
	public void rangeSearch(Rectangle userRec,Rectangle parentRect, TreeNode parent, Stack<Point> stack){
	
		//5.1 If the rectangle given includes the parent rectangle, the add all the points of the subtree
		if (userRec.includes(parentRect)) {
            this.addAllSubTree(parent, stack);
            return;
        }
		
		//5.2 If the userRect contains the current point then add this point to the stack
		if (userRec.contains(parent)){
			stack.push(parent);
		}
		
		//5.3 We check if the rectangle intersects with the left and right rectangle of our current TreeNode
		//5.4 If both then check both sides, else check only overlapped side
		if (parent.leftNode() != null){
			if(userRec.intersects(parent.leftRect())){
				parentRect = parent.leftRect();
				TreeNode current = parent.leftNode();
				if(current.getLevel()%2 == 0){
					current.sLeftRect(parentRect.xmin(),current.x(), parentRect.ymin(), parentRect.ymax());
					current.sRightRect(current.x(), parentRect.xmax(), parentRect.ymin(), parentRect.ymax());
					rangeSearch(userRec,parentRect, current, stack);
				}
				else{
					current.sLeftRect(parentRect.xmin(), parentRect.xmax(), parentRect.ymin(), current.y());
					current.sRightRect(parentRect.xmin(), parentRect.xmax(), current.y(), parentRect.ymax());
					rangeSearch(userRec,parentRect, current, stack);
				}
			}
		}
		
		if (parent.rightNode() != null){
			if(userRec.intersects(parent.rightRect())){
				parentRect = parent.rightRect();
				TreeNode current = parent.rightNode();
				if(current.getLevel()%2 == 0){
					current.sLeftRect(parentRect.xmin(),current.x(), parentRect.ymin(), parentRect.ymax());
					current.sRightRect(current.x(), parentRect.xmax(), parentRect.ymin(), parentRect.ymax());
					rangeSearch(userRec,parentRect, current, stack);
				}
				else{
					current.sLeftRect(parentRect.xmin(), parentRect.xmax(), parentRect.ymin(), current.y());
					current.sRightRect(parentRect.xmin(), parentRect.xmax(), current.y(), parentRect.ymax());
					rangeSearch(userRec,parentRect, current, stack);
				}
			}
		}
	}
	
	
	public void addAllSubTree(TreeNode node, Stack<Point> stack){
		stack.push(node);
		if(node.leftNode()!=null){
			addAllSubTree(node.leftNode(), stack);
		}
		if(node.rightNode()!=null){
			addAllSubTree(node.rightNode(), stack);
		}
	}
	
	public static void main(String[] args) throws IOException {
		 try {
			
			//6 Buffered reader reads the points in a text file
			BufferedReader in = new BufferedReader(new FileReader(args[0]));
			String line;
			
			String treeSize = in.readLine();
			TwoDTree tree = new TwoDTree();
			List<Point> list = new ArrayList<Point>();
			
			while((line = in.readLine()) != null)
			{
				String[] table1 = line.split(" ");
				int x = Integer.parseInt(table1[0]);
				int y = Integer.parseInt(table1[1]);
				if (x<0 || x>100 || y<0 || y>100) throw new MyOwnException("out of boundaries");
				Point p = new Point(x,y);
				//6.1 The points are added to a list
				list.add(p);
			}
			in.close();
			boolean flag = true;
			//6.2 Check the list for duplicate points
			for (Iterator<Point> iterator = list.iterator(); iterator.hasNext();) {
			    Point p1 = iterator.next();
			    iterator.remove();
			    for (Iterator<Point> iterator2 = list.iterator(); iterator2.hasNext();) {
			    	Point p2 = iterator2.next();
			    	if (p2.equals(p1)) throw new MyOwnException("duplicate");
			    }
			    if (flag == true){
			    	tree.add(p1);
			    }
			    
			}
			
			//6.3 Begin the mainloop
			boolean flag2 = true;
			do{
				System.out.println("Choose between Query rectangle-1 , Query point-2, exit-3 ");
				Scanner scn= new Scanner(System.in);
				int num = scn.nextInt();
				if(num == 3) {
					flag2 = false;
					scn.close();
				}
				else if(num == 1){
					System.out.println("input xmin");
					int xmin = scn.nextInt();
					System.out.println("input xmax");
					int xmax = scn.nextInt();
					if(xmin>xmax) throw new MyOwnException("xmin>xmax");
					System.out.println("input ymin");
					int ymin = scn.nextInt();
					System.out.println("input ymax");
					int ymax = scn.nextInt();
					if(ymin>ymax) throw new MyOwnException("ymin>ymax");
					tree.root().sLeftRect(0, tree.root().x(), 0, 100);
					tree.root().sRightRect(tree.root().x(), 100, 0, 100);
					Stack<Point> stack = new Stack<Point>();
					Rectangle parentRect = new Rectangle(0,100,0,100);
					Rectangle userRect = new Rectangle(xmin,xmax , ymin, ymax);
					tree.rangeSearch(userRect ,parentRect,  tree.root(), stack);
					System.out.print("Points included: " );
					stack.printStack(System.out);
					System.out.println();
				}
				else if(num == 2){
					System.out.println("input x");
					int x = scn.nextInt();
					System.out.println("input y");
					int y = scn.nextInt();
					Point p = new Point(x,y);
					Point result = tree.nearestNeighbor(p);
					System.out.println("The nearest neighbor is:  " + result.toString());
					System.out.println("The distance is:  " + p.distanceTo(result));
					
					
				}
				
			}while(flag2);
			
			

			
		}catch(IOException exps){
			System.out.println("Error1");
		}
		catch(NullPointerException ejs){
			System.out.println("Error2");
		}
		catch (MyOwnException e) {
			e.printStackTrace();
	    }
		
	}
	   

	
	
}


class MyOwnException extends Exception {
	public MyOwnException(String msg){
	      super(msg);
	}
}

