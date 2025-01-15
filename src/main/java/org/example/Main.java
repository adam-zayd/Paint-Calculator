package org.example;
import java.util.Scanner;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    private static double calculateShapeArea(String type, Scanner reader) {
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

            case "semicircle":
                System.out.println("Enter radius:");
                double radius_semi = reader.nextDouble();
                area = 0.5*Math.PI * radius_semi * radius_semi;
                break;

            case "triangle":
                System.out.println("Enter base and height:");
                double triangleBase = reader.nextDouble();
                double triangleHeight = reader.nextDouble();
                area = (triangleBase * triangleHeight) / 2;
                break;

            case "square":
                System.out.println("Enter length:");
                double length = reader.nextDouble();
                area = length * length;
                break;

            default:
                System.out.println("Surface type invalid. Please restart and be careful with spelling.");
        }
        return area;
    }
    private static double calculateSurfaceArea(Scanner reader) {

        System.out.println("Enter the number of surfaces that need painting:");
        int numSurfaces = reader.nextInt();

        double totalPaintableArea = 0;

        //get details of surface (shape, dimensions) and number of obstacles
        for (int i = 1; i <= numSurfaces; i++) {
            System.out.println("Enter the surface type (rectangle, trapezia, circle, semicircle, triangle, square) for surface " + i + ":");
            String surfaceType = reader.next().toLowerCase();

            System.out.println("Enter dimensions for the surface:");
            double surfaceArea = calculateShapeArea(surfaceType, reader);

            System.out.println("Enter the number of obstacles on this surface. That is, surfaces that can not be painted over:");
            int numObstacles = reader.nextInt();

            double obstacleArea = 0;

            //also need same details of obstacles (shape, dimensions)
            for (int j = 1; j <= numObstacles; j++) {
                System.out.println("Enter the obstacle type (rectangle, trapezia, circle, semicircle, triangle, square) for obstacle " + j + ":");
                String obstacleType = reader.next().toLowerCase();

                System.out.println("Enter dimensions for the obstacle:");
                obstacleArea += calculateShapeArea(obstacleType, reader);
            }

            double paintableArea = surfaceArea - obstacleArea;
            System.out.println("Paintable area for surface " + i + ": " + paintableArea + " units squared");
            totalPaintableArea += paintableArea;
        }
        System.out.println("Total paintable area: " + totalPaintableArea + " units squared");
        return totalPaintableArea
    }

    private static void requiredPaint(double totalPaintableArea, Scanner reader) {
        System.out.println("Enter the coverage of your paint. Firstly, the units squared - this must be the same unit of measurement used earlier, then, per how many litres. \nPress enter after typing each figure");
        double mCoverage = reader.nextDouble();
        double lCoverage = reader.nextDouble();
        double coverage = lCoverage/mCoverage;
        double paintRequired= coverage*totalPaintableArea;

        System.out.println("Enter how many coats you want:");
        int coats= reader.nextInt();

        paintRequired*=coats;

        System.out.println("You will require "+paintRequired+"litres of your chosen paint.");

    }

    public static void main(String[] args) {
        System.out.println("DISCLAIMER: Please be consistent with all unit measurements (keep them all in centimetres or inches. This should be based on the paint coverage unit of measurement, found on the back of the can)");

        Scanner reader = new Scanner(System.in);
        double totalPaintableArea= calculateSurfaceArea(reader);
        requiredPaint(totalPaintableArea, reader);
        reader.close();
        }

        }