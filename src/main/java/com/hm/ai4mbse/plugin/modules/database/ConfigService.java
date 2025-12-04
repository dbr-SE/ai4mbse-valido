package com.hm.ai4mbse.plugin.modules.database;

public class ConfigService {
    public String getApiKey() {
        return System.getenv("GEMINI_API_KEY");
    }
}
