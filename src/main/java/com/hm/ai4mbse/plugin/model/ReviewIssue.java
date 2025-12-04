package com.hm.ai4mbse.plugin.model;

/**
 * DTO (Data Transfer Object) für ein gefundenes Problem im Review.
 * Wird von der KI erstellt und im UI angezeigt.
 * Basiert auf Moritz' Prototyp.
 */
public class ReviewIssue {

    private String suggestion;       // Was schlägt die KI vor?
    private String detectedProblem;  // Was ist falsch?
    private int confidence;          // Wie sicher ist die KI? (0-100)
    private String elementId;        // ID des betroffenen Elements (für Navigation)

    // Leerer Konstruktor für Frameworks
    public ReviewIssue() {
    }

    public ReviewIssue(String suggestion, String detectedProblem, int confidence) {
        this.suggestion = suggestion;
        this.detectedProblem = detectedProblem;
        this.confidence = confidence;
    }

    // --- Getter & Setter ---

    public String getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion;
    }

    public String getDetectedProblem() {
        return detectedProblem;
    }

    public void setDetectedProblem(String detectedProblem) {
        this.detectedProblem = detectedProblem;
    }

    public int getConfidence() {
        return confidence;
    }

    public void setConfidence(int confidence) {
        this.confidence = confidence;
    }

    public String getElementId() {
        return elementId;
    }

    public void setElementId(String elementId) {
        this.elementId = elementId;
    }

    @Override
    public String toString() {
        return "ReviewIssue: " + detectedProblem + " (" + confidence + "%)";
    }
}
