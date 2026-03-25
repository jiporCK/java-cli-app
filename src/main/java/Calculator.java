import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.util.concurrent.Callable;

@Command(name = "calc", mixinStandardHelpOptions = true, version = "1.0",
        description = "A simple CLI calculator built with picocli.")
public class Calculator implements Callable<Double> {

    @Parameters(index = "0", description = "The first number")
    private double n1;

    @Parameters(index = "1", description = "The second number")
    private double n2;

    @Option(names = {"-o", "--operator"}, description = "Operation: add, sub, mul, div", defaultValue = "add")
    private String operator;

    @Override
    public Double call() throws Exception {

        double result = 0.0;

        switch (operator.toLowerCase()) {
            case "add" -> result = n1 + n2;
            case "sub" -> result = n1 - n2;
            case "mul" -> result = n1 * n2;
            case "div" -> {
                if (n2 == 0) {
                    System.err.println("Error: Cannot divide by zero!");
                    return 1.0;
                }
                result = n1 / n2;
            }
            default -> {
                System.err.println("Unknown operator: " + operator);
            }
        }

        System.out.printf("%.2f %c %.2f = %.2f%n", n1,
                switch (operator) {
                    case "add" -> '+';
                    case "sub" -> '-';
                    case "mul" -> '*';
                    case "div" -> '/';
                    default -> '?';
                },
                n2, result);
        return 0.0;
    }

}
