package com.pauldaniv.file.metadata;

import com.pauldaniv.file.metadata.adjuster.Adjuster;
import com.pauldaniv.file.metadata.adjuster.ShiftAdjuster;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Adjuster adjuster = new ShiftAdjuster();
        try {
            final FileWalker fileWalker = new FileWalker();
            fileWalker.walk("src/main/resources");
            adjuster.adjust("src/main/resources/20160719_105224.jpg","src/main/resources/out");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
