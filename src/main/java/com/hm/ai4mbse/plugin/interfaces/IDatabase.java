package com.hm.ai4mbse.plugin.interfaces;

import java.util.List;

/**
 * Speichert und lädt Regeln (Persistenz).
 * Moritz implementiert das in modules/database.
 */
public interface IDatabase {
    /**
     * Speichert eine neue Regel.
     * @param name Name der Regel (für die Liste)
     * @param systemPrompt Der technische Prompt für die KI
     */
    void saveRule(String name, String systemPrompt);

    /**
     * Lädt alle gespeicherten Regeln.
     */
    List<String> getAllRuleNames();
    
    /**
     * Holt den Prompt zu einem Namen.
     */
    String getRulePrompt(String name);
}
