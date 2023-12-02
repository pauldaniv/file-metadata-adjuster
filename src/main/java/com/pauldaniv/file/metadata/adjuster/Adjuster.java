package com.pauldaniv.file.metadata.adjuster;

import java.io.IOException;

public interface Adjuster {
    void adjust(String inputFile, String outputDir) throws IOException;
}
