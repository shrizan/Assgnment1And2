import models.Triangle;
import utils.TriangleUtils;

public class Main {
    
    public static void main(String[] args) {
        Triangle[] triangles = new Triangle[3];
        TriangleUtils utils = new TriangleUtils();
        utils.fillTrianglesArray(triangles);
    }
}