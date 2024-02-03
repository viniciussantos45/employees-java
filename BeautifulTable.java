import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// Classe auxiliar para imprimir tabelas formatadas no console.
public class BeautifulTable {

    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_PURPLE = "\u001B[35m";

    public void printTable(String title, List<String> headers, List<List<String>> data) {
        List<Integer> columnWidths = headers.stream()
                .mapToInt(String::length)
                .boxed()
                .collect(Collectors.toList());

        for (List<String> row : data) {
            for (int i = 0; i < row.size(); i++) {
                columnWidths.set(i, Math.max(columnWidths.get(i), row.get(i).length()));
            }
        }

        int width = columnWidths.stream().mapToInt(i -> i).sum() + columnWidths.size() * 3 - 1;

        printTitle(title, width);

        printLine(headers, columnWidths, ANSI_GREEN);
        printDivider(columnWidths);

        for (List<String> row : data) {
            printLine(row, columnWidths, ANSI_YELLOW);
        }
    }

    private void printLine(List<String> items, List<Integer> widths, String color) {
        String line = IntStream.range(0, items.size())
                .mapToObj(i -> String.format(color + " %-" + widths.get(i) + "s " + ANSI_RESET, items.get(i)))
                .collect(Collectors.joining("|"));

        System.out.println("|" + line + "|");
    }

    private void printDivider(List<Integer> widths) {
        String divider = widths.stream()
                .map(width -> ANSI_PURPLE + "-".repeat(width + 2) + ANSI_RESET)
                .collect(Collectors.joining("+"));

        System.out.println("+" + divider + "+");
    }

    private void printTitle(String title, int width) {
        int titleWidth = title.length();
        int padding = 4;
        int totalWidth = Math.max(width, titleWidth + padding);
        String spaces = ANSI_BLUE + "-".repeat((totalWidth - titleWidth) / 2);
        System.out.println("+" + spaces + ANSI_RED + title + ANSI_RESET + spaces
                + ((totalWidth - titleWidth) % 2 == 0 ? "+" : "-+"));
    }
}
