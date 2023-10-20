package utils;

import models.Triangle;
import models.Vertex;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class TriangleUtils {

    /**
     * this method return double data enter by the user
     *
     * @param message:String Message to display while getting user input
     * @return double: returns user entered value
     */
    private double getUserInput(String message) {
        boolean validInput = false;
        double userInput = 0;
        System.out.print(message);

        while (!validInput) {
            try {
                Scanner scanner = new Scanner(System.in);
                userInput = scanner.nextDouble();
                validInput = true;
            } catch (InputMismatchException ex) {
                System.out.println("Please provide a valid input !!!");
            }
        }

        return userInput;
    }

    /**
     * This method create a new vertex taking two input from the user
     *
     * @param message: String message to display to user
     * @return Vertex construct vertex from getting x,y from input
     */
    private Vertex getTriagleSingleVertex(String message) {
        System.out.println(message);
        double x = getUserInput("Please enter x:");
        double y = getUserInput("Please enter y:");
        return new Vertex(x, y);
    }

    /**
     * This method construct Triangle by getting 3 vertex input from the user
     *
     * @param message: String message to display to user
     * @return: Triangle it construct the triangle
     */
    private Triangle getTriangle(String message) {
        System.out.println(message);
        Vertex[] vertices = new Vertex[3];
        for (int i = 0; i < 3; i++) {
            vertices[i] = getTriagleSingleVertex("X and Y for Vertex " + (i + 1) + ":");
        }
        return new Triangle(vertices);
    }


    /**
     * This method fills and perform task on triangle
     *
     * @param triangles: Triangle[] getting user input and calculating perimeter etc
     */
    public void performTaskOnTriangle(Triangle[] triangles) {
        for (int i = 0; i < triangles.length; i++) {
            Triangle triangle = getTriangle("Vertices for triangle " + (i + 1) + " :");
            triangles[i] = triangle;
            calculatePerimeterAndCheckIsosceles(triangle);
            checkIfAPointIsInsideTriangle(triangle);
        }
    }

    /**
     * This method calculates the perimeter and checks if the triangle is isosceles
     *
     * @param triangle: Triangle
     */
    private void calculatePerimeterAndCheckIsosceles(Triangle triangle) {
        Map<Double, Double> distanceMap = new HashMap<>();
        int numberOfVertices = 3;
        Vertex[] vertices = triangle.getVertices();
        double perimeter = 0;
        boolean isIso = false;
        for (int i = 0; i < numberOfVertices; i++) {
            Vertex vertex1 = vertices[i];
            Vertex vertex2;
            if (i == 2) {
                vertex2 = vertices[0];
            } else {
                vertex2 = vertices[i + 1];
            }
            double distance = getDistanceBetweenTwoPoints(vertex1, vertex2);
            if (distanceMap.get(distance) != null) {
                isIso = true;
            } else {
                distanceMap.put(distance, distance);
            }
            perimeter += distance;
        }
        String message = isIso ? "Triangle is isosceles." : "Triangle is not isosceles.";
        System.out.printf("Perimeter: %f and %s\n", perimeter, message);

    }

    /**
     * This method get new point from the user and check if the triangle is inside
     *
     * @param traTriangle: Triangle
     */
    public void checkIfAPointIsInsideTriangle(Triangle traTriangle) {
        Vertex vertex = getTriagleSingleVertex("Please enter a point:");
        Vertex[] triangleVertices = traTriangle.getVertices();
        double triangleArea = calculateTriangleArea(triangleVertices);
        double area1 = calculateTriangleArea(vertex, triangleVertices[1], triangleVertices[2]);
        double area2 = calculateTriangleArea(triangleVertices[0], vertex, triangleVertices[2]);
        double area3 = calculateTriangleArea(triangleVertices[0], triangleVertices[1], vertex);
        boolean isInside = triangleArea == (area1 + area2 + area3);
        System.out.printf(isInside ?
                String.format("%s is inside of the triangle!!!\n", vertex.toString())
                : "%s is not inside of the triangle!!!\n", vertex.toString());
    }

    /**
     * this method calculates distance between two points
     *
     * @param vertex1: Vertex vertex 1 to calculate
     * @param vertex2: Vertex Vertex 2 to calculate
     * @return return distance between vertex1 and vertex2
     */
    private double getDistanceBetweenTwoPoints(Vertex vertex1, Vertex vertex2) {
        return Math.sqrt(Math.pow((vertex1.getX() - vertex2.getX()), 2) + Math.pow((vertex1.getY() - vertex2.getY()), 2));
    }


    /**
     * This method calculates the triangle area
     *
     * @param vertices Vertex[]
     * @return area
     */
    private double calculateTriangleArea(Vertex... vertices) {
        if (vertices.length != 3) {
            throw new IllegalArgumentException("to calculate area of triangle there need 3 vertices");
        }
        Vertex vertex1 = vertices[0];
        Vertex vertex2 = vertices[1];
        Vertex vertex3 = vertices[2];
        return calculateTriangleArea(
                vertex1.getX(),
                vertex1.getY(),
                vertex2.getX(),
                vertex2.getY(),
                vertex3.getX(),
                vertex3.getY()
        );
    }

    /**
     * This method calculate from 3 vertex x and y
     *
     * @param x1: vertex 1 x
     * @param y1: vertex 1 y
     * @param x2: vertex 2 x
     * @param y2: vertex 2 y
     * @param x3: vertex 3 x
     * @param y3: vertex 3 y
     * @return
     */
    private double calculateTriangleArea(double x1, double y1, double x2, double y2,
                                         double x3, double y3) {
        return Math.abs((x1 * (y2 - y3) + x2 * (y3 - y1) +
                x3 * (y1 - y2)) / 2.0);
    }
}
