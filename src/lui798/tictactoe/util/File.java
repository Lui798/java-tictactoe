package lui798.tictactoe.util;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class File {
    private Path file;

    public File() {
        //this.file = Paths.get(".file");
    }

    public File(String file) {
        this.file = Paths.get(file);
    }

    public Path getFile() {
        return file;
    }

    public List<String> readFile(Path file) {
        List<String> lines = new ArrayList<String>();
        try {
            lines = Files.readAllLines(file);
        }
        catch (IOException e) { }
        return lines;
    }

    public void writeFile(Path file, List<String> lines) {
        try {
            Files.write(file, lines, Charset.forName("UTF-8"));
        }
        catch (IOException e) { }
    }
}
