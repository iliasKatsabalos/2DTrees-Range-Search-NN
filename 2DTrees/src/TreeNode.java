//1. leftNode and rightNode fields are used as pointers. They are the children of a TreeNode
//2. leftRect and righRect are the two rectangles created by a TreeNode, which splits the plane into two areas, right and left.
//3. The level field shows how deep lies the point into the tree, starting at zero level (root)
//4. size is a static field, which increases every time a node is created. It represents the size of the tree.
//5. According to the level of the tree, we decide which dimension to use for the comparison
public class TreeNode extends Point{
	//1. leftNode and rightNode fields are used as pointers. They are the children of a TreeNode
	//2. leftRect and righRect are the two rectangles created by a TreeNode, which splits the plane into two areas, right and left.
	//3. The level field shows how deep lies the point into the tree, starting at zero level (root)
	//4. size is a static field, which increases every time a node is created. It represents the size of the tree.
	private Point node;
	private TreeNode leftNode;
	private TreeNode rightNode;
	private int level;
	private static int size = 0;
	private Rectangle leftRect;
	private Rectangle rightRect;
	
	
	public TreeNode(Point p) {
		super(p.x(), p.y());
		this.node = p;
		this.leftNode =null;
		this.rightNode = null;
		level = 0;
		size++;
		
	}
	
	public void setLevel(int level){
		this.level = level;
	}
	
	public int getLevel(){
		return level;
	}
	
	public TreeNode leftNode(){
		return this.leftNode;
	}
	
	public TreeNode rightNode(){
		return this.rightNode;
	}
	
	public void sLeftNode(TreeNode n){
		this.leftNode = n;
	}
	
	public void sRightNode(TreeNode n){
		this.rightNode = n;
	}
	
	//6. According to the level of the tree, we decide which dimension to use for the comparison 
	public int compareData(Point p, int i){
		if ((i % 2)==1){
			return p.y();
		}else{
			return p.x();
		}
	}
	
	public String display(TreeNode n){
		
		if (leftNode()!=null && rightNode()!=null){
			return this.leftNode().toString() + " " + this.node.toString() + " " + this.rightNode().toString();
		}else{
			if (leftNode()==null){
				return this.node.toString() + " " + this.rightNode().toString();
			}else{
				return this.leftNode().toString() + " " + this.node.toString();
			}
		}
	}
	
	public static int getSize(){
		return size;
	}
	
	public Rectangle leftRect(){
		return this.leftRect;
	}
	
	public Rectangle rightRect(){
		return this.rightRect;
	}
	
	public void sLeftRect(int xmin, int xmax, int ymin, int ymax){
		this.leftRect = new Rectangle(xmin, xmax, ymin, ymax);
	}
	
	public void sRightRect(int xmin, int xmax, int ymin, int ymax){
		this.rightRect = new Rectangle(xmin, xmax, ymin, ymax);
	}

	
	
	
}
