package com.pauldaniv.file.metadata;

import com.pauldaniv.file.metadata.adjuster.Adjuster;
import com.pauldaniv.file.metadata.adjuster.ShiftAdjuster;

public class Main {
    public static void main(String[] args) {
        Adjuster adjuster = new ShiftAdjuster();
        final FileWalker fileWalker = new FileWalker();
        fileWalker.walk("src/main/resources", adjuster);
    }
}
