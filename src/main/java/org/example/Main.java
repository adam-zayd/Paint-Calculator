package org.example;
import java.util.Scanner;
4//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    private static int getValidInt(Scanner reader, String outp, String err) {
        while (true) {
            System.out.println(outp);
            if (reader.hasNextInt()) {
                int value = reader.nextInt();
                if (value>=0) {
                    return value;
                }
            } else {
                reader.next();
            }
            System.out.println(err);
        }
    }

    private static String getValidSurfaceType(Scanner reader, String outp) {
        while (true) {
            System.out.println(outp);
            String input = reader.next().toLowerCase();
            if (input.equals("rectangle") || input.equals("square")||input.equals("trapezia") || input.equals("circle") ||
                    input.equals("semicircle") || input.equals("triangle")) {
                return input;
            }
            System.out.println("Invalid surface type. Please enter a valid type from the list.");
        }
    }






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


        int numSurfaces = getValidInt(reader, "Enter the number of surfaces that need painting:", "Your entry is invalid. \nMake sure your input is 0 or greater. \nMake sure it is only a number. \nMake sure it has no decimal place.");
        if (numSurfaces==0){
            System.out.println("Total paintable surface = 0 \nTotal paint required =0");
            System.exit(0);
        }
        double totalPaintableArea = 0;

        //get details of surface (shape, dimensions) and number of obstacles
        for (int i = 1; i <= numSurfaces; i++) {
            String surfaceType = getValidSurfaceType(reader, "Enter the surface type (rectangle, trapezia, circle, semicircle, triangle, square) for surface " + i + ":");

            System.out.println("Enter dimensions for the surface:");
            double surfaceArea = calculateShapeArea(surfaceType, reader);

            int numObstacles = getValidInt(reader, "Enter the number of obstacles on this surface. That is, surfaces that can not be painted over::", "Your entry is invalid. \nMake sure your input is 0 or greater. \nMake sure it is only a number. \nMake sure it has no decimal place.");;

            double obstacleArea = 0;

            //also need same details of obstacles (shape, dimensions)
            for (int j = 1; j <= numObstacles; j++) {
                String obstacleType = getValidSurfaceType(reader, "Enter the obstacle type (rectangle, trapezia, circle, semicircle, triangle, square) for obstacle " + j + ":");

                System.out.println("Enter dimensions for the obstacle:");
                obstacleArea += calculateShapeArea(obstacleType, reader);
            }

            double paintableArea = surfaceArea - obstacleArea;
            System.out.println("Paintable area for surface " + i + ": " + paintableArea + " units squared");
            totalPaintableArea += paintableArea;
        }
        System.out.println("Total paintable area: " + totalPaintableArea + " units squared");
        return totalPaintableArea;
    }

    private static void requiredPaint(double totalPaintableArea, Scanner reader) {
        System.out.println("Enter the coverage of your paint. Firstly, the units squared - this must be the same unit of measurement used earlier, then, per how many litres. \nPress enter after typing each figure");
        double mCoverage = reader.nextDouble();
        double lCoverage = reader.nextDouble();
        double coverage = lCoverage/mCoverage;
        double paintRequired= coverage*totalPaintableArea;

        int coats= getValidInt(reader, "Enter the number of paint coats that you want:", "Your entry is invalid. \nMake sure your input is 0 or greater. \nMake sure it is only a number. \nMake sure it has no decimal place.");;

        paintRequired*=coats;

        System.out.println("You will require "+paintRequired+"litres of your chosen paint.");

    }

    public static void main(String[] args) {
        System.out.println("DISCLAIMER: Please be consistent with all unit measurements (keep them all in centimetres or inches. This should be based on the paint coverage unit of measurement, found on the back of the can). Please press enter whenever entering requested information");

        Scanner reader = new Scanner(System.in);
        double totalPaintableArea= calculateSurfaceArea(reader);
        requiredPaint(totalPaintableArea, reader);
        reader.close();
        }
        }