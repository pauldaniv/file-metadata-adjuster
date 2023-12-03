package com.pauldaniv.file.metadata.adjuster;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class LastModifiedToFileNameAdjuster implements Adjuster {

    public void adjust(final String inputFile, final String outputDir) {
        try {
            Path file = Paths.get(inputFile);
            BasicFileAttributes attr = Files.readAttributes(file, BasicFileAttributes.class);
            final String extension = FilenameUtils.getExtension(inputFile);
            final FileTime fileTime = attr.lastModifiedTime();
            final Instant lastModifiedTimeInstant = fileTime.toInstant();
            final DateTimeFormatter datePattern = DateTimeFormatter.ofPattern("yyyyMMdd_hhmmss")
                    .withZone(ZoneId.systemDefault());
            final String newFileName = datePattern.format(lastModifiedTimeInstant);
            FileUtils.copyFile(
                    FileUtils.getFile(inputFile),
                    FileUtils.getFile("%s/%s.%s".formatted(outputDir, newFileName, extension)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
