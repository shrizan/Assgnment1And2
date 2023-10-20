package utils;

import models.Triangle;
import models.Vertex;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class TriangleUtils {

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


    private Vertex getTriagleSingleVertex(String message) {
        System.out.println(message);
        double x = getUserInput("Please enter x:");
        double y = getUserInput("Please enter y:");
        return new Vertex(x, y);
    }

    private Triangle getTriangle(String message) {
        System.out.println(message);
        Vertex[] vertices = new Vertex[3];
        for (int i = 0; i < 3; i++) {
            vertices[i] = getTriagleSingleVertex("X and Y for Vertex " + (i + 1) + ":");
        }
        return new Triangle(vertices);
    }

    public void fillTrianglesArray(Triangle[] triangles) {
        for (int i = 0; i < triangles.length; i++) {
            Triangle triangle = getTriangle("Vertices for triangle " + (i + 1) + " :");
            triangles[i] = triangle;
            calculatePerimeterAndCheckIsosceles(triangle);
            checkIfAPointIsInsideTriangle(triangle);
        }
    }

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

    public void checkIfAPointIsInsideTriangle(Triangle traTriangle) {
        Vertex vertex = getTriagleSingleVertex("Please enter a point:");
        Vertex[] triangleVertices = traTriangle.getVertices();
        double triangleArea = calculateTriangleArea(triangleVertices);
        double area1 = calculateTriangleArea(vertex, triangleVertices[1], triangleVertices[2]);
        double area2 = calculateTriangleArea(triangleVertices[0], vertex, triangleVertices[2]);
        double area3 = calculateTriangleArea(triangleVertices[0], triangleVertices[1], vertex);
        boolean isInside = triangleArea == (area1 + area2 + area3);
        System.out.printf(isInside ?
    String.format("%s is inside of the triangle!!!\n",vertex.toString())
    : "%s is not inside of the triangle!!!\n",vertex.toString());
    }

    private double getDistanceBetweenTwoPoints(Vertex vertex1, Vertex vertex2) {
        return Math.sqrt(Math.pow((vertex1.getX() - vertex2.getX()), 2) + Math.pow((vertex1.getY() - vertex2.getY()), 2));
    }

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

    private double calculateTriangleArea(double x1, double y1, double x2, double y2,
                                         double x3, double y3) {
        return Math.abs((x1 * (y2 - y3) + x2 * (y3 - y1) +
                x3 * (y1 - y2)) / 2.0);
    }
}
