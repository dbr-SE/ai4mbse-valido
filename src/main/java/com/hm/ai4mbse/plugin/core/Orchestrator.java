package com.hm.ai4mbse.plugin.core;

import com.nomagic.actions.AMConfigurator;
import com.nomagic.actions.ActionsCategory;
import com.nomagic.actions.ActionsManager;
import com.nomagic.magicdraw.actions.ActionsConfiguratorsManager;
import com.nomagic.magicdraw.actions.ActionsID;
import com.nomagic.magicdraw.actions.MDAction;
import com.nomagic.magicdraw.core.Application;
import com.nomagic.magicdraw.core.Project;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;

// Importiere unsere eigenen Interfaces und Module
import com.hm.ai4mbse.plugin.interfaces.IReviewService;
import com.hm.ai4mbse.plugin.modules.ui.ReviewDashboard;
// import com.hm.ai4mbse.plugin.modules.ai.GeminiService; // Spaeter einkommentieren!

/**
 * Der Orchestrator verbindet alle Module (Dependency Injection).
 */
public class Orchestrator {

    private static final String ACTION_ID = "AI4MBSE_ReviewAction";
    private static final String ACTION_NAME = "Start AI Review (AI4MBSE)";
    
    public void init() {
        ActionsConfiguratorsManager.getInstance().addMainMenuConfigurator(new AMConfigurator() {
            @Override
            public void configure(ActionsManager manager) {
                ActionsCategory tools = (ActionsCategory) manager.getActionFor(ActionsID.TOOLS);
                if (tools != null) {
                    tools.addAction(createReviewAction());
                }
            }
            @Override
            public int getPriority() { return AMConfigurator.MEDIUM_PRIORITY; }
        });
    }

    private MDAction createReviewAction() {
        return new MDAction(ACTION_ID, ACTION_NAME, null, "Tools") {
            @Override
            public void actionPerformed(ActionEvent e) {
                runReviewProcess();
            }
        };
    }

    /**
     * Startet den Workflow und oeffnet das JDialog-Fenster.
     */
    private void runReviewProcess() {
        Project project = Application.getInstance().getProject();
        if (project == null) {
            JOptionPane.showMessageDialog(null, "Kein Projekt geoeffnet!");
            return;
        }

        // 1. Das MagicDraw-Hauptfenster als "Elternteil" holen
        // Damit bleibt unser Dialog immer im Vordergrund der Anwendung
        Frame mdMainFrame = Application.getInstance().getMainFrame();

        // 2. Den Service erstellen (Dependency Injection)
        // Aktuell null. Spaeter: IReviewService service = new GeminiService();
        IReviewService service = null; 

        // 3. Das Dashboard erstellen und anzeigen (JDialog)
        // ACHTUNG: Die folgenden Zeilen erst einkommentieren, wenn Moritz
        // die Datei ReviewDashboard.java mit Inhalt befuellt hat!
        
        /* ReviewDashboard dashboard = new ReviewDashboard(mdMainFrame, service);
        dashboard.setVisible(true);
        */
        
        // Vorlaeufiger Platzhalter, damit man sieht, dass der Button geht:
        JOptionPane.showMessageDialog(mdMainFrame, 
            "Orchestrator bereit!\n" +
            "Bitte ReviewDashboard.java und GeminiService.java implementieren,\n" +
            "dann die Kommentare in Orchestrator.java entfernen."
        );
    }
}
