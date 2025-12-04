package com.hm.ai4mbse.plugin.core;

import com.nomagic.magicdraw.plugins.Plugin;

/**
 * Entry Point fuer MagicDraw.
 * Delegiert sofort an den Orchestrator.
 */
public class PluginLifecycle extends Plugin {
    
    private Orchestrator orchestrator;

    @Override
    public void init() {
        // Wir erstellen den Manager
        orchestrator = new Orchestrator();
        // Wir sagen dem Manager: "Leg los!"
        orchestrator.init();
        System.out.println("[AI4MBSE] Plugin initialized.");
    }

    @Override
    public boolean close() {
        // Aufräumen beim Beenden
        return true;
    }

    @Override
    public boolean isSupported() {
        return true;
    }
}
