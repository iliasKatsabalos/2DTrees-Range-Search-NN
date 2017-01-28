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



