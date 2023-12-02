package com.pauldaniv.file.metadata;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class FileWalker {
    public void walk(String path) {
        try (Stream<Path> walk = Files.walk(Paths.get(path))) {
            walk
                    .filter(Files::isRegularFile)   // is a file
                    .filter(p -> p.getFileName().toString().endsWith(".jpg"))
                    .forEach(System.out::println);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
