package filedecorators;

import java.io.*;
import java.nio.file.Files;

public class UTF8Decorator extends FileDecorator {

    public UTF8Decorator(FileDecorator fileDecorator) {
        super(fileDecorator);
    }

    @Override
    public void decorate(File file) throws IOException {
        if (fileDecorator != null) {
            fileDecorator.decorate(file);
        }

        // Convert the file content to UTF-8 encoding
        String content = new String(Files.readAllBytes(file.toPath()), "UTF-8");
        Files.write(file.toPath(), content.getBytes("UTF-8"));
        System.out.println("File content converted to UTF-8 encoding");
    }
}
