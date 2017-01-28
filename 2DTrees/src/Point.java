//1.Private fieds x, y are used for the representation of a 2D Point p(x,y)
//2. Euclidean Distance between two points
//3. Equals methos checks if two points are the same
public class Point {
//1.Private fieds x, y are used for the representation of a 2D Point p(x,y)	
	private int x;
	private int y;
	
	public Point(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public Point(){
		this.x = (int) Math.random();
		this.y = (int) Math.random();
	}
	
	public int x(){
		return this.x;
	}
	
	public int y(){
		return this.y;
	}
	
	//2. Euclidean Distance between two points
	public double distanceTo(Point z){
		int dx = this.x - z.x;
		int dy = this.y - z.y;
		double distance = Math.sqrt(dx*dx + dy*dy);
		return distance;
	}
	
	//3. Equals methos checks if two points are the same
	public boolean equals(Point p){
		boolean result = false;
		if (this.x() == p.x() && this.y() == p.y()){
			result = true;
		}
		return result;
	}
	public int squareDistanceTo(Point z){
		int dx = this.x - z.x;
		int dy = this.y - z.y;
		int sDistance = dx*dx + dy*dy;
		return sDistance;
	}
	
	public String toString(){
		return "(" + x + "," + y + ")";
	}
	
}
