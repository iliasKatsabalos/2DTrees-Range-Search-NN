import java.util.ArrayList;

//1. Private fields xmin, xmax, ymin, ymax are used for the representation of a rectangle (xmin, xmax)*(ymin,ymax)
//2. Checks whether a rectangle contains a given point
//3. Checks whether two rectangles overlap each other
//4. Distance from a point to the closest part of e rectangle
//5. Checks if a given rectangle includes another rectangle in its entirety
public class Rectangle {
	//1. Private fields xmin, xmax, ymin, ymax are used for the representation of a rectangle (xmin, xmax)*(ymin,ymax)	
	private int xmin;
	private int xmax;
	private int ymin;
	private int ymax;
	
	
	public Rectangle(int xmin, int xmax, int ymin, int ymax){
		this.xmin = xmin;
		this.xmax = xmax;
		this.ymin = ymin;
		this.ymax = ymax;
	}
	
	public int xmin(){
		return xmin;
	}
	
	public int xmax(){
		return xmax;
	}
	
	public int ymin(){
		return ymin;
	}
	
	public int ymax(){
		return ymax;
	}
	
	//2. Checks whether a rectangle contains a given point
	public boolean contains(Point z){
		boolean result;
		if (z.x()>=xmin && z.x()<=xmax && z.y()>=ymin && z.y()<=ymax){
			result =true;
		}
		else{
			result = false;
		}
		return result;
	}
	
	//3. Checks whether two rectangles overlap each other
	public boolean intersects(Rectangle that){
		boolean result;
		if (this.xmax() < that.xmin() || this.xmin() > that.xmax() || this.ymax() < that.ymin() || this.ymin()>that.ymax()){
			result = false;
		}
		else{
			result = true;
		}
		return result;
	}
	
	//4. Distance from a point to the closest part of e rectangle
	public double distanceTo(Point z){
		
		double distance;
		if (contains(z)){
			int distToBottomSide = z.y() - ymin();
			int distToUpperSide = ymax()- z.y();
			int distToLeftSide = z.x() - xmin();
			int distToRightSide = xmax() - z.x();
			
			ArrayList<Integer> list = new ArrayList<Integer>();
			list.add(distToBottomSide);
			list.add(distToUpperSide);
			list.add(distToLeftSide);
			list.add(distToRightSide);
			
			int min = list.get(0);
			for(int i : list){
				if (i<min){
					min = i;
				}
			}
			distance = min;
			 
		}
		
		else if (z.x()>xmax()){
			if (z.y()>ymax()){
				int dx = z.x()-xmax();
				int dy = z.y()-ymax();
				distance = Math.sqrt(dx*dx+dy*dy);
			}
			else if (z.y()<ymin()){
				int dx = z.x()-xmax();
				int dy = ymin()-z.y();
				distance = Math.sqrt(dx*dx+dy*dy);
			}
			else{
				distance = z.x()-xmax;
			}
		}
		
		else if (z.x()<xmin()){
			if (z.y()>ymax()){
				int dx = xmin()-z.x();
				int dy = z.y()-ymax();
				distance = Math.sqrt(dx*dx+dy*dy);
			}
			else if (z.y()<ymin()){
				int dx = xmin()-z.x();
				int dy = ymin()-z.y();
				distance = Math.sqrt(dx*dx+dy*dy);
			}
			else{
				distance = xmin()-z.x();
			}
		}
		else{
			if(z.y()>ymax()){
				distance = z.y()-ymax();
			}
			else{
				distance = ymin()-z.y();
			}
		}
		return distance;
		
	}
	
	public double squareDistanceTo(Point z){
		return Math.pow(distanceTo(z), 2);
	}
	
	//5. Checks if a given rectangle includes another rectangle in its entirety
	public boolean includes(Rectangle that){
		if(this.xmin()<=that.xmin() && this.xmax()>=that.xmax() && this.ymin()<=that.ymax() && this.ymax()>=that.ymax()){
			return true;
		}else{
			return false;
		}
	}
	
	public String toString(){
		return "[" + xmin() + "," + xmax() + "] , [" + ymin() + "," + ymax() + "]"; 
	}
	
}
