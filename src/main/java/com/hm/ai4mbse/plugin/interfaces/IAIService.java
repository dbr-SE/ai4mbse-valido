package com.hm.ai4mbse.plugin.interfaces;

/**
 * Schnittstelle zur KI (Google Gemini).
 * Michi implementiert das in modules/ai.
 */
public interface IAIService {
    /**
     * Sendet einen beliebigen Prompt an die KI und gibt den Text zurück.
     * Nutze dies für Reviews, BrainDumps oder Chat.
     */
    String generateResponse(String prompt);

    /**
     * Prüft, ob der API-Key gültig ist (aus dem alten Repo übernommen).
     */
    boolean validateApiKey(String key);
}
