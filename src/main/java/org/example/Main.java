package org.example;
import java.util.Scanner;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {




    //Validation checks
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

    private static double getValidDouble(Scanner reader, String outp, String err) {
        while (true) {
            System.out.println(outp);
            if (reader.hasNextDouble()||reader.hasNextInt()) {
                double value = Double.parseDouble(reader.next());
                if (value>0) {
                    return value;
                }
            } else {
                reader.next();
            }
            System.out.println(err);
        }
    }






    private static double calculateShapeArea(String type, Scanner reader) {
        double area = 0;
        switch (type) {
            case "rectangle":
                double width = getValidDouble(reader, "Enter width:", "Please enter a valid number. \nMust be greater than zero");
                double height = getValidDouble(reader, "Enter height:", "Please enter a valid number. \nMust be greater than zero");

                area = width * height;
                break;
            case "trapezia":
                double base1 = getValidDouble(reader, "Enter base1:", "Please enter a valid number. \nMust be greater than zero");
                double base2 = getValidDouble(reader, "Enter base2:", "Please enter a valid number. \nMust be greater than zero");
                double trapeziaHeight = getValidDouble(reader, "Enter height:", "Please enter a valid number. \nMust be greater than zero");
                area = ((base1 + base2) / 2) * trapeziaHeight;
                break;
            case "circle":
                double radius = getValidDouble(reader, "Enter radius:", "Please enter a valid number. \nMust be greater than zero");
                area = Math.PI * radius * radius;
                break;

            case "semicircle":
                double radius_semi = getValidDouble(reader, "Enter radius:", "Please enter a valid number. \nMust be greater than zero");
                area = 0.5*Math.PI * radius_semi * radius_semi;
                break;

            case "triangle":
                double triangleBase = getValidDouble(reader, "Enter base:", "Please enter a valid number. \nMust be greater than zero");
                double triangleHeight = getValidDouble(reader, "Enter height:", "Please enter a valid number. \nMust be greater than zero");
                area = (triangleBase * triangleHeight) / 2;
                break;

            case "square":
                double length = getValidDouble(reader, "Enter length:", "Please enter a valid number. \nMust be greater than zero");
                area = length * length;
                break;

            default:
                System.out.println("ERROR with Surface type being unrecognised");
                break;
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

            System.out.println("Enter dimensions for the surface...");
            double surfaceArea = calculateShapeArea(surfaceType, reader);

            int numObstacles = getValidInt(reader, "Enter the number of obstacles on this surface. That is, surfaces that can not be painted over::", "Your entry is invalid. \nMake sure your input is 0 or greater. \nMake sure it is only a number. \nMake sure it has no decimal place.");

            double obstacleArea = 0;

            //also need same details of obstacles (shape, dimensions)
            for (int j = 1; j <= numObstacles; j++) {
                String obstacleType = getValidSurfaceType(reader, "Enter the obstacle type (rectangle, trapezia, circle, semicircle, triangle, square) for obstacle " + j + ":");

                System.out.println("Enter dimensions for the obstacle...");
                double tmp=calculateShapeArea(obstacleType, reader);
                if (surfaceArea<tmp){
                    System.out.println("You may have made a mistake entering a dimension of an obstacle.\nThe area of the obstacle using the entered dimensions: "+  tmp);
                    System.out.println("Please restart...");
                    System.exit(0);
                }
                obstacleArea += tmp;
            }

            double paintableArea = surfaceArea - obstacleArea;
            System.out.println("Paintable area for surface " + i + ": " + paintableArea + " units squared");
            totalPaintableArea += paintableArea;
        }
        System.out.println("Total paintable area: " + totalPaintableArea + " units squared");
        return totalPaintableArea;
    }

    private static void requiredPaint(double totalPaintableArea, Scanner reader) {
        System.out.println("Enter the coverage of your paint. Firstly, the units squared - this must be the same unit of measurement used earlier. Then, enter per how many litres. \n");
        double mCoverage = getValidDouble(reader, "Enter units squared:", "Please enter only a valid number. \nMust be greater than zero");
        double lCoverage = getValidDouble(reader, "Enter 'per litres squared':", "Please enter only a valid number. \nMust be greater than zero");

        double coverage = lCoverage/mCoverage;
        double paintRequired= coverage*totalPaintableArea;

        int coats= getValidInt(reader, "Enter the number of paint coats that you want:", "Your entry is invalid. \nMake sure your input is 0 or greater. \nMake sure it is only a number. \nMake sure it has no decimal place.");

        paintRequired*=coats;

        double canSize= getValidDouble(reader, "Enter the amount of litres per paint can","Please enter only a valid number. \nMust be greater than zero");

        double cansNeeded = paintRequired/canSize;

        System.out.println("You will require " + Math.ceil(paintRequired * 1000) / 1000 + " litres of your chosen paint.");
        System.out.println("You will require " + Math.ceil(cansNeeded) + " cans of your chosen paint.");

    }

    public static void main(String[] args) {
        System.out.println("DISCLAIMER: Please be consistent with all unit measurements (keep them all in centimetres or inches. This should be based on the paint coverage unit of measurement, found on the back of the can). Please press enter whenever entering requested information");

        Scanner reader = new Scanner(System.in);
        double totalPaintableArea= calculateSurfaceArea(reader);
        requiredPaint(totalPaintableArea, reader);
        reader.close();
        }
}