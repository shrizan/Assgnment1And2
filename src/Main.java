import models.Triangle;
import utils.TriangleUtils;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static int getNumberOfTriangle() {
        System.out.println("How many triangle you want to enter(At least 3) is require:");
        boolean inputDone = false;
        int totalInput = 0;
        while (!inputDone) {
            try {
                Scanner scanner = new Scanner(System.in);
                totalInput = scanner.nextInt();
                if (totalInput < 3) {
                    System.out.println("Must be greater than 3!!!");
                } else {
                    inputDone = true;
                }
            } catch (InputMismatchException ex) {
                System.out.println("Please enter valid input !!!");
            }
        }
        return totalInput;
    }

    public static void main(String[] args) {
        Triangle[] triangles = new Triangle[getNumberOfTriangle()];
        TriangleUtils utils = new TriangleUtils();
        utils.performTaskOnTriangle(triangles);
    }
}