package ui;

import java.util.ArrayList;
import java.util.HashMap;

import app.Circle;
import app.IShape;
import app.NGon;
import app.Point2D;
import app.Polyline;
import app.QGon;
import app.Rectangle;
import app.Segment;
import app.TGon;
import app.Trapeze;

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

    private static Point2D createPoint(HashMap<String, String[]> args) throws Exception {
        Point2D point = null;
        
        if (args.containsKey("dims") && args.containsKey("x")) {
            Integer dims = Integer.parseInt(args.get("dims")[0]);
            String[] coords = args.get("x");
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

    private interface Creator<T> {
        T create(Point2D[] points) throws Exception;
    }

    private static <T> T createNShape(HashMap<String, String[]> args, Creator<T> creator) throws Exception {
        T ngon = null;

        if (args.containsKey("n") && args.containsKey("p")) {
            Integer n = Integer.parseInt(args.get("n")[0]);

            String[] coords = args.get("p");
            if (coords.length != n) {
                throw new Exception("NGon: n not valid");
            }

            Point2D[] points = new Point2D[n];
            for (int i = 0; i < coords.length; i++) {
                System.out.println(coords[i]);
                ConstructorHolder holder = ConstructorHolder.parseFromString(coords[i]);
                points[i] = createPoint(holder.getArgs());
            }

            ngon = creator.create(points);
        }

        return ngon;
    }

    private static Circle createCircle(HashMap<String, String[]> args) throws Exception {
        Circle circle = null;

        if (args.containsKey("p") && args.containsKey("r")) {
            Double r = Double.parseDouble(args.get("r")[0]);

            String center = args.get("p")[0];

            Point2D point = null;
            ConstructorHolder holder = ConstructorHolder.parseFromString(center);
            point = createPoint(holder.getArgs());

            circle = new Circle(point, r);
        }

        return circle;
    }

    private static Segment createSegment(HashMap<String, String[]> args) throws Exception {
        Segment segment = null;

        if (args.containsKey("start") && args.containsKey("finish")) {
            String start = args.get("start")[0];
            String finish = args.get("finish")[0];

            Point2D startP = null;
            Point2D finishP = null;
            ConstructorHolder startHolder = ConstructorHolder.parseFromString(start);
            ConstructorHolder finishHolder = ConstructorHolder.parseFromString(finish);

            startP = createPoint(startHolder.getArgs());
            finishP = createPoint(finishHolder.getArgs());

            segment = new Segment(startP, finishP);
        }

        return segment;
    }

    private final static class ConstructorHolder {
        private String name = null;
        private HashMap<String, String[]> args = new HashMap<>();

        private ConstructorHolder(String name, HashMap<String, String[]> args) {
            this.name = name;
            this.args = args;
        }

        public String getName() {
            return name;
        }

        public HashMap<String, String[]> getArgs() {
            return args;
        }
        
        public static ConstructorHolder parseFromString(String buildString) throws Exception {
            String[] buildRes = new String[2];

            System.out.println(buildString + " fdsfdssfdfdsfs");

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
            System.out.println(name + " wrewr " + args);
            return new ConstructorHolder(name, parseArgs(args));
        }

        private static HashMap<String, String[]> parseArgs(String strArgs) {
            HashMap<String, String[]> args = new HashMap<>();

            int lastInd = 0;
            int bracketsBalance = 0;
            int state = 0; // 0 - бежим по имени аргумента, 1 - бежим по значению аргумента, 2 - бежим по внутренней кухне аргумента,
            String curArgName = null;
            for (int i = 0; i <= strArgs.length(); i++) {
                if (i >= strArgs.length()) {
                    args.put(curArgName, parseArrayArgs(strArgs.substring(lastInd, i)));
                    break;
                }
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
                        args.put(curArgName, parseArrayArgs(strArgs.substring(lastInd, i)));
                        curArgName = null;
                        state = 0;
                        lastInd = i + 2;
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

            if (args.containsKey("start")) {
                System.out.println(args.get("start")[0]);
            }

            if (args.containsKey("finish")) {
                System.out.println(args.get("finish")[0]);
            }

            return args;
        }

        private static String[] parseArrayArgs(String arrString) {
            System.out.println(arrString + " eqweqw");
            if (arrString == null || arrString.length() == 0 || arrString.charAt(0) != '(') {
                return new String[] { arrString };
            }

            ArrayList<String> list = new ArrayList<>();

            int lastInd = 1;
            int bracketBalance = 0;
            for (int i = 1; i < arrString.length(); i++) {
                if (i == arrString.length() - 1) {
                    list.add(arrString.substring(lastInd, i));
                    break;
                }
                char cur = arrString.charAt(i);
                switch (cur) {
                    case '(':
                        bracketBalance++;
                        break;
                    case ')':
                        bracketBalance--;
                        break;
                    case ',':
                        if (bracketBalance != 0)
                            continue;
                        list.add(arrString.substring(lastInd, i));
                        lastInd = i + 2;
                        i++;
                }
            }

            return list.toArray(new String[0]);
        }
    }

    private static IShape createByName(String name, HashMap<String, String[]> args) throws Exception {
        switch(name){
            case "NGon":
                return createNShape(args, (p) -> new NGon(p));
            case "Polyline":
                return createNShape(args, (p) -> new Polyline(p));
            case "QGon":
                return createNShape(args, (p) -> new QGon(p));
            case "Rectangle":
                return createNShape(args, (p) -> new Rectangle(p));
            case "Segment":
                return createSegment(args);
            case "TGon":
                return createNShape(args, (p) -> new TGon(p));
            case "Trapeze":
                return createNShape(args, (p) -> new Trapeze(p));
            case "Circle":
                return createCircle(args);
            default:
                return null;
        }
    };

}
