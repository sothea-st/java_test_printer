/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hello.hello.printerUserPressButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.PrinterName;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author USER
 */
public class PrinterApp {
      private static final String PRINTER_NAME = "Your Printer Name";

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Print Receipt Example");
            JPanel panel = new JPanel();
            JButton printButton = new JButton("Print Receipt");

            printButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    printReceipt();
                }
            });

            panel.add(printButton);
            frame.add(panel);
            frame.setSize(300, 200);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }

    private static void printReceipt() {
        PrintService printService = findPrintService(PRINTER_NAME);
        if (printService != null) {
            try {
                String receiptContent = "Your receipt content goes here.";
                InputStream inputStream = new ByteArrayInputStream(receiptContent.getBytes());

                DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
                Doc doc = new SimpleDoc(inputStream, flavor, null);

                PrintRequestAttributeSet attributes = new HashPrintRequestAttributeSet();
                attributes.add(new PrinterName(PRINTER_NAME, null));

                DocPrintJob printJob = printService.createPrintJob();
                printJob.print(doc, attributes);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Printer not found.");
        }
    }

    private static PrintService findPrintService(String printerName) {
        PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, null);
        for (PrintService printService : printServices) {
            if (printService.getName().equalsIgnoreCase(printerName)) {
                return printService;
            }
        }
        return null;
    }
    
}
