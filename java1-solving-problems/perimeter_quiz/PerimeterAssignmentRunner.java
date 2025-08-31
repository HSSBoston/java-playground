import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public int getNumPoints (Shape s) {
        int totalPoints = 0;
        for(Point pt : s.getPoints()){
            totalPoints = totalPoints + 1;
        }
        return totalPoints;
    }

    public double getAverageLength(Shape s) {
        double perim = getPerimeter(s);
        int points = getNumPoints(s);
        double avLength = perim/points;
        return avLength;
    }

    public double getLargestSide(Shape s) {
        Point prevPt = s.getLastPoint();
        double longLength = 0;
        for(Point currPt : s.getPoints()){
            double length = prevPt.distance(currPt);
            String stringLength = Double.toString(length);
            System.out.println(stringLength);
            if(length > longLength){
                longLength = length;
            }
        }
        return longLength;
    }

    public double getLargestX(Shape s) {
        double largestX = 0;
        for(Point pt : s.getPoints()){
            double currX = pt.getX();
            if(currX > largestX){
                largestX = currX;
            }
        }
        return largestX;
    }

    public double getLargestPerimeterMultipleFiles() {
        DirectoryResource dr = new DirectoryResource();
        double largePerim = 0;
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double currPerim = getPerimeter(s);
            if(currPerim > largePerim){
                largePerim = currPerim;
            }
        }
        return largePerim;
    }

    public String getFileWithLargestPerimeter() {
        DirectoryResource dr = new DirectoryResource();
        double largePerim = 0;
        File temp = null;
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double currPerim = getPerimeter(s);
            if(currPerim > largePerim){
                largePerim = currPerim;
                temp = f;
            }
        }
        return temp.getName();
    }

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        int numPoints = getNumPoints(s);
        double averageLength = getAverageLength(s);
        double longestLength = getLargestSide(s);
        double largestXValue = getLargestX(s);
        System.out.println("perimeter = " + length);
        System.out.println("number of points = " + numPoints);
        System.out.println("average side length = " + averageLength);
        System.out.println("largest side length = " + longestLength);
        System.out.println("largest x value = " + largestXValue);
    }
    
    public void testPerimeterMultipleFiles() {
        double largestPerimeter = getLargestPerimeterMultipleFiles();
        System.out.println("largest perimeter across multiple files = " + largestPerimeter);
    }

    public void testFileWithLargestPerimeter() {
        String fileWithLargestPerimeter = getFileWithLargestPerimeter();
        System.out.println("file with the largest perimeter = " + fileWithLargestPerimeter);
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        pr.testPerimeter();
        pr.testPerimeterMultipleFiles();
        pr.testFileWithLargestPerimeter();
    }
}
