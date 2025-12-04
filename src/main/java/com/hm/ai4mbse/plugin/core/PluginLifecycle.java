package com.hm.ai4mbse.plugin.core;
import com.nomagic.magicdraw.plugins.Plugin;
public class PluginLifecycle extends Plugin {
    private Orchestrator orchestrator;
    @Override
    public void init() {
        orchestrator = new Orchestrator();
        orchestrator.init();
    }
    @Override public boolean close() { return true; }
    @Override public boolean isSupported() { return true; }
}
