# 2DTrees-Range-Search-NN
Create and query 2D Trees with Nearest Neighbor and Range Search Queries


## 1. Introduction
This project implements the processes of creating a 2 dimensional tree and how to apply Nearest Neighbor and Range Search queries on it. The project was developed by students of Athens University of Economics and Business Ilias Katsabalos and Matthew Kalkounis, during a relative assignment. In the following chapters we discuss all the classes needed to implement this project and particularly the two main algorithms, namely Nearest Neighbor and Range Search. For further details, please refer to the comments on the actual code.

## 2. Project’s Input

The input to the project is a txt file which holds an amount of points. According to those points we construct the tree. The format of the file is the following:  
5  
70,20  
50,40  
20,30  
40,70  
80,90   
The first line states the number of points included in our tree. Later the user is prompt with some selections, whether he wants to perform a range search or a nearest neighbor search. According to the selection we execute the relative method.  

## 3. The Point Class
The Point Class consists of two private fields x, y, as in reality(x,y). Each instance of this class is an actual Point e.g. (10,30). By the definition of the assignment, the limits for our x, y axis is 100. No point can exceed this border. Some methods included in this class are pretty straightforward, such as:

*	distanceTo(Point): The Euclidean distance between two points
*	squareDistanceTo(Point)
*	equals(Point): (overrided)checks whether two Points are the same

## 4. The Rectangle Class
The Rectangle Class consists of four private fields: xmin, xmax, ymin, ymax. The representation of a rectangle is as follows: (xmin, xmax, ymin, ymax). The methods included in the Rectangle class are the following:

*	contains(Point p ): checks if a Point is contained by a rectangle
*	intersects(Rectangle r): checks whether two rectangles overlap each other.
*	distanceTo(Point p): finds the closer distance from our Point to the rectangle.
*	Includes(Rectangle r): shows if a rectangle contains another rectangle in all its entirety.

### 4.2 Why to create the Rectangle Class?
According to the theory of KD trees and particularly the 2D Trees, each node in the tree splits the tree into 2 rectangles. At root level, the plane is splitted in parallel with the y axis and in the next level the plane is split parallel to x axis. This sequence-alternation is continued throughout the tree. Let’s see an example for the point (70,20) and (50,40):

![picture alt](https://github.com/iliasKatsabalos/2DTrees-Range-Search-NN/blob/master/res1.jpg)

When we first insert the point (70,20) our space is divided into 2 rectangles(because we have the borders of 100 in x and y axis). The first Rectangle (left) is (0,70)x(0,100) and the other (right) is (70,100)x(0,100).  
Then we insert the point (50,40) which is the left child of our root, so it lies at the left rectangle of the root, which serves as the borderlines for our new point(50,40). The new point splits his parent rectangle (0,70)x(0,100) into 2 new Rectangles: left (0,70)x(0,40) and right (0,70)x(40,100).  
You can now understand the connection of the rectangles in the 2D Tree. The use of them will be clearer later in this file. 

## 5. The TreeNode Class
The TreeNode class is used for adding nodes to our tree. It extends the Point class. It consists of the following private fields:

*	Point node
*	TreeNode leftNode: the left child of our TreeNode
*	TreeNode rightNode: the right child of our TreeNode
*	Int level: the level that our TreeNode holds in the tree
*	Rectangle leftRect: the left Rectangle of our TreeNode
*	Rectangle righRect: the right Rectangle of our TreeNode
*	Static int Size: it increases for every TreeNode created so we know the size of the tree

The most useful method in this class, apart from setters and getters is the compare(Point p , int level) method, which shows what dimension we check for comparing, either the x or y, according to the depth (level) of the TreeNode. 

## 6. The TwoDTree Class
The TwoDTree class mainly includes all the main functionality of our project. Let us run through the methods it contains in discuss them further.

### 6.1 The add method
This is a simple method and is used for adding new points to our tree. We descend to the tree and check if we have reached a leaf by using the leftNode or rightNode for any nulls. In other words, if our current TreeNode has no left or right child, it is considered a possible lead and we can add to it a new child if the appropriate one is null.
If we have reached a null TreeNode, we add the point and set the level according to the level of our parent node. 

###6.2 The search method 
The user gives a specific point and we need to search the tree to find it. This method is pretty similar to the add method. We descend the tree and for each node we check if the given point is the same with our current TreeNode. According to the results, we decided if we need to descend right or left and descend further into the tree, or return the correct point if found. 

### 6.3 Nearest Neighbor
In order to understand better this method we need review the algorithm followed. The algorithm used is described in O’Reilly *Algorithms in a nutshell 2nd Edition*. Here is the algorithm:

```java
nearest (T, x)
  n = find parent node in T where x would have been inserted	 
  min = distance from x to n.point
  better = nearest (T.root, min, x)
  if better found then return better
    return n.point
end

nearest (node, min, x)
  d = distance from x to node.point
  if d < min then
    result = node.point
    min = d
  dp = perpendicular distance from x to node
  if dp < min then
    pt = nearest (node.above, min, x)
    if distance from pt to x < min then
      result = pt
      min = distance from pt to x
    pt = nearest (node.below, min, x)
    if distance from pt to x < min then
      result = pt
      min = distance from pt to x
  else
    if node is above x then
      pt = nearest (node.above, min, x)
    else
      pt = nearest (node.below, min, x)
  if pt exists then return pt
  return result
end
```

As you can see from the algorithm above, there are two methods described. First we run the nearest (Point p) which uses only one parameter, and then we start the recursion of the tree using the nearest (node, min, x) which uses three parameters, giving the node of the root as the start of the recursion. 

### 6.3.1 The findClosestLeaf Method
In the beginning of the algorithm we need to find the distance from our given point to the appropriate leaf node, the part of the tree that our point would be normally inserted. For that purpose we use the findClosestLeaf(Point p) method which returns the closest leaf to our pint, in similar fashion as the add and search method described in the previous sections. 

### 6.3.2 The distanceToRect method
In some part of the algorithm, we need to find the dp(perpendicular distance) from the point to the closest part of the rectangle represented by our current TreeNode.  It is represented at the schema below. If this distance is smaller than our current nearest distance (let’s say it is the distance of P(60,40) and (70,20) ) then we need to search also and on the other side of the subtree (in this case the right side).  
Let’s see with a geometric point of view. Our current best distance is r(radius).  So if there is closer point, it must lie between this circle. This circle although, overlaps with the other side of the subtree, because the border of the opposite rectangle is closer than our best distance r. Thus, we need to check the other side of the tree as well.
The distanceToRect(TreeNode node, Point p) is responsible for deciding which is the opposite rectangle (according to if we are left or right from the splitting line) and retrieve the perpendicular distance from that rectangle. If the dp distance is bigger we do not have to search that part of the tree.  

![picture alt](https://github.com/iliasKatsabalos/2DTrees-Range-Search-NN/blob/master/res2.jpg)

### 6.3.3 The rest of the algorithm
After finding the perpendicular distance, we know if we will check both sides of the subtree or only one. In either case, before going deeper into the recursion, we set the rectangles of our new current point according to our parent rectangle. Later we run the recursion. See the code for deeper explanation. 

## 6.4 Range Search Method
This method is responsible for finding all the points in a tree, that are included in a rectangle that a user specifies. First we present the algorithm that we implemented, described in *O’Reilly Algorithms in a nutshell* 2nd Edition. 

```java
range (space)
  results = new Set
  range (space, root, results)
  return results
end

range (space, node, results)
  if space contains node.region then
    add node.points and all of its descendants to results
    return
  if space contains node.point then
    add node.point to results
  if space extends below node.coord then
    range (space, node.below, results)
  if space extends above node.coord then
    range (space, node.above, results)
end
```

Based on the above algorithm, for each TreeNode visited we proceed into three checks:
*	Does the user specified rectangle fully contains our current rectangle? If true then add all the subtree to the results and return.
*	If not, does the user defined rectangle contain our current TreeNode? If true then add the current point to our set.
*	Does the user defined rectangle extends to both sides of the splitting line of our current TreeNode? In other words, does the user defined rectangle intersects with our current’s left or right rectangle? Continue the recursion with the appropriate one or both.

### 6.4.1 The addAllSubTree method
This method adds all the TreeNodes of a subtree to a stack. We execute this method, when the user defined rectangle completely contains the current TreeNode rectangle. 

## 6.5 The main method
This method is pretty straight forward. A buffered reader reads all our points from our text file and inserts them into a list. The appropriate checks are taking place. Then we loop through the list in order to find if there are any duplicates, which by the definition of the assignment are prohibited. 
Then we enter the main loop, where the user is prompt with the choices of running a nearest neighbor query or running a range search query. The relative methods are executed. 
