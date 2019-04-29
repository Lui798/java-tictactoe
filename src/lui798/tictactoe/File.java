package lui798.tictactoe;

import java.nio.charset.Charset;
import java.nio.file.*;
import java.util.Arrays;
import java.util.List;

public class File {
    private FileSystem fs = FileSystems.getDefault();
    //private String cd = Paths.get("").toAbsolutePath().toString();
    private Path file = Paths.get("scores.txt");

    public void writeScore(String input) {
        List<String> line = Arrays.asList(input);
        Files.write(file, line, Charset.forName("UTF-8"));
    }
}
