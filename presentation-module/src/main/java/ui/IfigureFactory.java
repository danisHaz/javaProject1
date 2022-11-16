package ui;

import java.util.HashMap;

import app.IShape;
import app.NGon;
import app.Point2D;

public class IfigureFactory {
    
    public static IShape create(String buildString) {
        IShape shape = null;
        try {
            ConstructorHolder holder = ConstructorHolder.parseFromString(buildString);
            shape = createByName(holder.name, holder.args);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return shape;
    }

    private static Point2D createPoint(HashMap<String, String> args) throws Exception {
        Point2D point = null;
        
        if (args.containsKey("dims") && args.containsKey("x")) {
            Integer dims = Integer.parseInt(args.get("dims"));
            String xCoords = args.get("x");
            String[] coords = xCoords.substring(1, xCoords.length() - 1).split(", ");
            if (coords.length != dims || dims != 2) {
                throw new Exception("Dims not valid");
            }

            double[] intCoords = new double[dims];
            for (int i = 0; i < coords.length; i++) {
                intCoords[i] = (double)Double.parseDouble(coords[i]);
            }

            point = new Point2D(intCoords);
        }

        return point;
    }

    private static NGon createNGon(HashMap<String, String> args) throws Exception {
        NGon ngon = null;

        if (args.containsKey("n") && args.containsKey("p")) {
            Integer n = Integer.parseInt(args.get("n"));

            String pCoords = args.get("p");
            String[] coords = pCoords.substring(1, pCoords.length() - 1).split(", ");
            if (coords.length != n) {
                throw new Exception("NGon: n not valid");
            }

            Point2D[] points = new Point2D[n];
            for (int i = 0; i < coords.length; i++) {
                ConstructorHolder holder = ConstructorHolder.parseFromString(coords[i]);
                points[i] = createPoint(holder.getArgs());
            }

            ngon = new NGon(points);
        }

        return ngon;
    }

    private final static class ConstructorHolder {
        private String name = null;
        private HashMap<String, String> args = new HashMap<>();

        private ConstructorHolder(String name, HashMap<String, String> args) {
            this.name = name;
            this.args = args;
        }

        public String getName() {
            return name;
        }

        public HashMap<String, String> getArgs() {
            return args;
        }
        
        public static ConstructorHolder parseFromString(String buildString) throws Exception {
            String[] buildRes = new String[2];

            parsing:
            for (int i = 0; i < buildString.length(); i++) {
                char c = buildString.charAt(i);
                if (c == '(') {
                    for (int j = buildString.length() - 1; j >= i; j--) {
                        char t = buildString.charAt(j);
                        if (t == ')') {
                            buildRes[0] = buildString.substring(0, i);
                            buildRes[1] = buildString.substring(i + 1, j);
                            break parsing;
                        }
                    }
                }
            }
            String name = buildRes[0];
            String args = buildRes[1];
            return new ConstructorHolder(name, parseArgs(args));
        }

        private static HashMap<String, String> parseArgs(String strArgs) {
            HashMap<String, String> args = new HashMap<>();
            System.out.println(strArgs);

            int lastInd = 0;
            int bracketsBalance = 0;
            int state = 0; // 0 - бежим по имени аргумента, 1 - бежим по значению аргумента, 2 - бежим по внутренней кухне аргумента,
            String curArgName = null;
            for (int i = 0; i < strArgs.length(); i++) {
                char curChar = strArgs.charAt(i);
                if (state == 0) {
                    if (curChar != '=') {
                        continue;
                    }
                    curArgName = strArgs.substring(lastInd, i);
                    state = 1;
                    lastInd = i + 1;
                } else if (state == 1) {
                    if (curChar == '(') {
                        bracketsBalance++;
                        state = 2;
                        continue;
                    }

                    if (curChar == ',') {
                        args.put(curArgName, strArgs.substring(lastInd, i));
                        curArgName = null;
                        state = 0;
                        i++;
                        continue;
                    }
                } else if (state == 2) {
                    if (curChar == '(')
                        bracketsBalance++;

                    if (curChar == ')' && --bracketsBalance == 0) {
                        state = 1;
                        continue;
                    }
                }
            }

            return args;
        }
    }

    private static IShape createByName(String name, HashMap<String, String> args) throws Exception {
        switch(name){
            case "NGon":
                return createNGon(args);
            case "Polyline":
                return createNGon(args);
            case "QGon":
                return createNGon(args);
            case "Rectangle":
                return createNGon(args);
            case "Segment":
                return createNGon(args);
            case "TGon":
                return createNGon(args);
            case "Trapeze":
                return createNGon(args);
            default:
                return null;
        }
    };

}
