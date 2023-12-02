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
import java.time.temporal.ChronoUnit;

public class ShiftAdjuster implements Adjuster {

    public void adjust(String inputFileName, String outputDir) throws IOException {
        final String name = FilenameUtils.getName(inputFileName);
        final Path inputFile = Paths.get(inputFileName);
        final BasicFileAttributes attr = Files.readAttributes(inputFile, BasicFileAttributes.class);
        final Instant lastModified = attr.lastModifiedTime().toInstant();

        final String outputFile = "%s/%s".formatted(outputDir, name);
        FileUtils.copyFile(
                FileUtils.getFile(inputFileName),
                FileUtils.getFile(outputFile));

        final Instant newLastModified = lastModified.atZone(ZoneId.systemDefault()).plusYears(2).toInstant();
        // convert LocalDate to instant, need time zone
        Files.setLastModifiedTime(Paths.get(outputFile), FileTime.from(newLastModified));
    }
}
