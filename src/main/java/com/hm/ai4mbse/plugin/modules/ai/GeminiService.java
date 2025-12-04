package com.hm.ai4mbse.plugin.modules.ai;

import com.hm.ai4mbse.plugin.interfaces.IAIService;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class GeminiService implements IAIService {

    // UPDATE: Nutzung von Gemini 3 Pro (Preview)
    // Release-Stand: November 2025
    private static final String MODEL_ID = "gemini-3-pro-preview";
    private static final String API_URL = "https://generativelanguage.googleapis.com/v1beta/models/" + MODEL_ID + ":generateContent";
    private static final int TIMEOUT = 60000;
    
    private String apiKey;

    public GeminiService(String apiKey) {
        this.apiKey = apiKey;
    }

    @Override
    public boolean validateApiKey(String key) {
        return key != null && key.length() > 10;
    }

    @Override
    public String generateResponse(String prompt) {
        if (apiKey == null || apiKey.isEmpty()) {
            return "Fehler: Kein API Key konfiguriert.";
        }
        try {
            return callGeminiAPI(prompt, apiKey);
        } catch (Exception e) {
            e.printStackTrace();
            return "Fehler bei der KI-Anfrage (Gemini 3): " + e.getMessage();
        }
    }

    private String callGeminiAPI(String prompt, String key) throws IOException {
        HttpURLConnection conn = null;
        try {
            URL url = new URL(API_URL + "?key=" + key);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setConnectTimeout(TIMEOUT);
            conn.setReadTimeout(TIMEOUT);
            conn.setDoOutput(true);

            // JSON-Escaping für Anführungszeichen und Zeilenumbrüche
            String escapedPrompt = prompt.replace("\"", "\\"").replace("\n", "\n");
            
            // Payload-Struktur für Gemini 3 ist kompatibel mit v1beta
            String jsonPayload = 
                "{ \"contents\": [{ \"parts\": [{ \"text\": \"" + escapedPrompt + "\" }] }] }";

            try (OutputStreamWriter w = new OutputStreamWriter(conn.getOutputStream(), StandardCharsets.UTF_8)) {
                w.write(jsonPayload);
            }

            int code = conn.getResponseCode();
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                code < 400 ? conn.getInputStream() : conn.getErrorStream(), StandardCharsets.UTF_8));
            
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            
            if (code >= 400) {
                throw new IOException("API Error (" + code + "): " + response.toString());
            }

            return extractTextFromJson(response.toString());

        } finally {
            if (conn != null) conn.disconnect();
        }
    }

    /**
     * Extrahiert den Text aus der JSON-Antwort.
     * Funktioniert auch für Gemini 3 Struktur.
     */
    private String extractTextFromJson(String json) {
        String marker = "\"text\": \"";
        int start = json.indexOf(marker);
        if (start == -1) return json;
        
        start += marker.length();
        int end = json.indexOf("\"", start);
        
        if (end == -1) return json;
        
        String text = json.substring(start, end);
        // Einfaches Un-Escaping
        return text.replace("\n", "\n").replace("\\"", "\"");
    }
}
