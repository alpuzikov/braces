import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyClass {

    public static String input;

    public static void main(String[] args) {

//        input = args[0];
        input = "())(()())(()";
        validateInput();
        removeIllegalBraces();

        StringBuilder left = new StringBuilder();
        StringBuilder right = new StringBuilder();

        while (input.length() > 1) {
            String afterTheFirst = input.substring(1,2);
            String beforeTheLast = input.substring(input.length() - 2, input.length() -1);
            if (afterTheFirst.equals(")")) {
                left.append("()");
                input = input.substring(2);
            } else if (beforeTheLast.equals("(")) {
                right.insert(0, "()");
                input = input.substring(0, input.length() - 2);
            }
            else {
                left.append("(");
                right.insert(0, ")");
                input = input.substring(1, input.length() - 1);
            }
            removeIllegalBraces();
        }

        String result = left.append(right).toString();
        System.out.println(result.length() + " - " + result);
    }

    public static void removeIllegalBraces() {
        boolean removedAtTheStart = false;
        boolean removedAtTheEnd = false;
        boolean complete = false;

        while (!complete) {
            if (input.startsWith(")")) {
                input = input.substring(1);
            } else {
                removedAtTheStart = true;
            }

            if (input.endsWith("(")) {
                input = input.substring(0, input.length() - 1);
            } else {
                removedAtTheEnd = true;
            }

            complete = removedAtTheStart && removedAtTheEnd;
        }
    }

    public static void validateInput() {
        Pattern pattern = Pattern.compile("[\\(|\\)]*");
        Matcher matcher = pattern.matcher(input);
        if (!matcher.matches()) {
            System.out.println("Incorrect input data!");
            System.exit(1);
        }
    }
}
