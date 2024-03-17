/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hello.hello;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.MediaPrintableArea;
import javax.print.attribute.standard.OrientationRequested;
import javax.print.attribute.standard.PrintQuality;
import javax.print.attribute.standard.PrinterResolution;

/**
 *
 * @author USER
 */
public class ReceiptPrinter {

    public static void printReceipt(String printerName, String receiptContent) {
        PrintService printService = PrinterService.findPrintService(printerName);
        if (printService != null) {
            try {
                InputStream inputStream = new ByteArrayInputStream(receiptContent.getBytes());
                DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
                Doc doc = new SimpleDoc(inputStream, flavor, null);
                DocPrintJob printJob = printService.createPrintJob();

                PrintRequestAttributeSet attributes = new HashPrintRequestAttributeSet();
                attributes.add(OrientationRequested.PORTRAIT);
                attributes.add(new PrinterResolution(203, 203, PrinterResolution.DPI));
                attributes.add(PrintQuality.NORMAL);
                attributes.add(new MediaPrintableArea(0, 0, 54, 297, MediaPrintableArea.MM));

                printJob.print(doc, attributes);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {

            System.out.println("Printer not found.");
        }
    }

    public static void main(String[] args) {
        String printerName = "Your Printer Name";
        String receiptContent = "Your receipt content goes here.";
        printReceipt(printerName, receiptContent);
    }
}
