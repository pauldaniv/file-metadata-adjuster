package com.pauldaniv.file.metadata;

import com.pauldaniv.file.metadata.adjuster.Adjuster;
import org.apache.commons.io.FilenameUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;
import java.util.stream.Stream;

public class FileWalker {
    public static final Set<String> SUPPORTED_EXTENSIONS = Set.of("jpg", "jpeg", "png", "mp4");

    public void walk(String path, Adjuster adjuster) {
        try (Stream<Path> walk = Files.walk(Paths.get(path))) {
            walk.filter(p -> !p.toString().contains("out"))
                    .filter(Files::isRegularFile)   // is a file
                    .filter(p -> FilenameUtils.isExtension(p.getFileName().toString(), SUPPORTED_EXTENSIONS))
                    .forEach(file -> adjuster.adjust(file.toString(), "src/main/resources/out"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
