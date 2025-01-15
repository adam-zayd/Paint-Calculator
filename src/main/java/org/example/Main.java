package org.example;
import java.util.Scanner;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    private static double calculateArea(String type, Scanner reader) {
        double area = 0;
        switch (type) {
            case "rectangle":
                System.out.println("Enter width and height:");
                double width = reader.nextDouble();
                double height = reader.nextDouble();
                area = width * height;
                break;
            case "trapezia":
                System.out.println("Enter base1, base2, and height:");
                double base1 = reader.nextDouble();
                double base2 = reader.nextDouble();
                double trapeziaHeight = reader.nextDouble();
                area = ((base1 + base2) / 2) * trapeziaHeight;
                break;
            case "circle":
                System.out.println("Enter radius:");
                double radius = reader.nextDouble();
                area = Math.PI * radius * radius;
                break;
            case "triangle":
                System.out.println("Enter base and height:");
                double triangleBase = reader.nextDouble();
                double triangleHeight = reader.nextDouble();
                area = (triangleBase * triangleHeight) / 2;
                break;
            default:
                System.out.println("Unknown surface type.");
        }
        return area;
    }
    public static void main(String[] args) {
        System.out.println("DISCLAIMER: Please be consistent with all unit measurements (keep them all in centimetres or inches)");

        Scanner reader = new Scanner(System.in);

        System.out.println("Enter the number of surfaces that need painting:");
        int numSurfaces = reader.nextInt();

        double totalPaintableArea = 0;

        //get details of surface (shape, dimensions) and number of obstacles
        for (int i = 1; i <= numSurfaces; i++) {
            System.out.println("Enter the surface type (rectangle, trapezia, circle, triangle) for surface " + i + ":");
            String surfaceType = reader.next().toLowerCase();

            System.out.println("Enter dimensions for the surface:");
            double surfaceArea = calculateArea(surfaceType, reader);

            System.out.println("Enter the number of obstacles on this surface:");
            int numObstacles = reader.nextInt();

            double obstacleArea = 0;

            //also need same details of obstacles (shape, dimensions)
            for (int j = 1; j <= numObstacles; j++) {
                System.out.println("Enter the obstacle type (rectangle, trapezia, circle, triangle) for obstacle " + j + ":");
                String obstacleType = reader.next().toLowerCase();

                System.out.println("Enter dimensions for the obstacle:");
                obstacleArea += calculateArea(obstacleType, reader);
            }

            double paintableArea = surfaceArea - obstacleArea;
            System.out.println("Paintable area for surface " + i + ": " + paintableArea + " units squared");
            totalPaintableArea += paintableArea;
        }

        System.out.println("Total paintable area: " + totalPaintableArea + " units squared");
        reader.close();
        }
    }
