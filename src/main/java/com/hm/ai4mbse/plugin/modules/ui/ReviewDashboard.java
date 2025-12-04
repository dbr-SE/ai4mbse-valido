package com.hm.ai4mbse.plugin.modules.ui;

import com.hm.ai4mbse.plugin.interfaces.IReviewService;
import com.hm.ai4mbse.plugin.model.ReviewIssue;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ReviewDashboard extends JDialog {

    private IReviewService service; // Der Service (wird von Simon injiziert)

    public ReviewDashboard(Frame owner, IReviewService service) {
        super(owner, "AI4MBSE - Assistant", false); // false = nicht-modal (MagicDraw bleibt bedienbar)
        this.service = service;

        setSize(1000, 700);
        setLocationRelativeTo(owner);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Model Review", createReviewPanel());
        tabbedPane.addTab("Regeln verwalten", createRulesPanel());

        add(tabbedPane);
    }

    // --- TAB 1: MODEL REVIEW ---
    private JPanel createReviewPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // OBERER BEREICH: Buttons und Scope
        JPanel topSection = new JPanel(new GridLayout(1, 2, 20, 0));

        // Links: Review Typen
        JPanel buttonPanel = new JPanel(new GridLayout(4, 1, 5, 5));
        buttonPanel.setBorder(BorderFactory.createTitledBorder("Review Type"));

        JButton btnTrace = new JButton("Traceability Review");
        JButton btnQual = new JButton("Quality Review");
        JButton btnMuda = new JButton("Muda Detection"); 
        JButton btnAtomic = new JButton("Atomic Check");

        btnMuda.setBackground(new Color(255, 230, 230)); 

        buttonPanel.add(btnTrace);
        buttonPanel.add(btnQual);
        buttonPanel.add(btnMuda);
        buttonPanel.add(btnAtomic);

        // Rechts: Scope
        JPanel scopePanel = new JPanel();
        scopePanel.setBorder(BorderFactory.createTitledBorder("Scope Selection"));
        scopePanel.setLayout(new BoxLayout(scopePanel, BoxLayout.Y_AXIS));

        JCheckBox chkSys = new JCheckBox("System Level Elements");
        JCheckBox chkReq = new JCheckBox("Requirements");
        JCheckBox chkLog = new JCheckBox("Logical Structure");
        chkSys.setSelected(true); 

        scopePanel.add(chkSys);
        scopePanel.add(chkReq);
        scopePanel.add(chkLog);

        topSection.add(buttonPanel);
        topSection.add(scopePanel);

        // UNTERER BEREICH: Ergebnisbericht
        String[] columns = {"Vorschläge (AI)", "% Confidence", "Erkanntes Problem", "Hilfe"};
        DefaultTableModel tableModel = new DefaultTableModel(columns, 0);
        JTable table = new JTable(tableModel);
        JScrollPane tableScroll = new JScrollPane(table);
        tableScroll.setBorder(BorderFactory.createTitledBorder("Ergebnisbericht"));

        // EVENTS (Logik verbinden)
        // Beispiel für den "Muda Detection" Button
        btnMuda.addActionListener(e -> {
            // Lade-Bildschirm
            JDialog loading = new JDialog(this, "Review in Progress", true);
            loading.add(new JLabel("  Modell arbeitet... Bitte warten.  ", SwingConstants.CENTER));
            loading.setSize(300, 100);
            loading.setLocationRelativeTo(this);

            // Timer simuliert Asynchronität
            new Thread(() -> {
                try {
                    // Service aufrufen (holt echte Daten oder Mock)
                    List<ReviewIssue> results = service.performReview("Muda Detection", List.of("Sys", "Req"));

                    SwingUtilities.invokeLater(() -> {
                        loading.dispose(); // Dialog schließen
                        // Tabelle füllen
                        tableModel.setRowCount(0); // Reset
                        for (ReviewIssue issue : results) {
                            tableModel.addRow(new Object[]{
                                    issue.getSuggestion(),
                                    issue.getConfidence() + "%",
                                    issue.getDetectedProblem(),
                                    "?"
                            });
                        }
                    });
                } catch (Exception ex) { ex.printStackTrace(); }
            }).start();

            loading.setVisible(true); // Blockiert bis loading.dispose()
        });

        panel.add(topSection, BorderLayout.NORTH);
        panel.add(tableScroll, BorderLayout.CENTER);

        return panel;
    }

    // --- TAB 2: REGELN VERWALTEN ---
    private JPanel createRulesPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        DefaultListModel<String> listModel = new DefaultListModel<>();
        listModel.addElement("Regel 1: Keine zyklischen Abhängigkeiten");
        JList<String> list = new JList<>(listModel);
        JScrollPane listScroll = new JScrollPane(list);
        listScroll.setBorder(BorderFactory.createTitledBorder("Aktive Regeln"));

        JPanel bottomPanel = new JPanel(new BorderLayout(5, 5));
        JTextField inputField = new JTextField();
        inputField.setBorder(BorderFactory.createTitledBorder("Neue Regel beschreiben"));
        JButton btnAdd = new JButton("Generieren & Hinzufügen (+)");

        btnAdd.addActionListener(e -> {
            String txt = inputField.getText();
            if (!txt.isEmpty()) {
                // TODO: Später an IRuleGenerator anbinden
                // service.createRule(txt); 
                listModel.addElement("Regel (Simuliert): " + txt);
                inputField.setText("");
            }
        });

        bottomPanel.add(inputField, BorderLayout.CENTER);
        bottomPanel.add(btnAdd, BorderLayout.EAST);

        panel.add(listScroll, BorderLayout.CENTER);
        panel.add(bottomPanel, BorderLayout.SOUTH);

        return panel;
    }
}
