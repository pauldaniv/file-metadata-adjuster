/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package com.pauldaniv.file.metadata;

import java.io.IOException;

public class App {
     public static void main(String[] args) {
        final ShiftAdjuster fileAdjuster = new ShiftAdjuster();
        try {
            fileAdjuster.adjust();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}