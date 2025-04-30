package filedecorators;

import java.io.*;

public abstract class FileDecorator {
    protected FileDecorator fileDecorator;

    public FileDecorator(FileDecorator fileDecorator) {
        this.fileDecorator = fileDecorator;
    }

    public abstract void decorate(File file) throws IOException;
}
