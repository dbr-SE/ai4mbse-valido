package com.hm.ai4mbse.plugin.interfaces;

/**
 * Logik, um aus menschlicher Sprache ("BrainDump") technische Regeln zu bauen.
 */
public interface IRuleGenerator {
    /**
     * Nimmt einen Freitext (z.B. "Alle Ports müssen typed sein")
     * und liefert einen technischen System-Prompt zurück.
     */
    String generateSystemPrompt(String brainDump);
}
