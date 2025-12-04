package com.hm.ai4mbse.plugin.modules.database;

import com.hm.ai4mbse.plugin.interfaces.IDatabase;
import java.util.Collections;
import java.util.List;

public class JsonDatabase implements IDatabase {
    @Override
    public void saveRule(String name, String systemPrompt) {
        // Hier speichert Moritz die Regel spÃ¤ter in eine JSON-Datei
        System.out.println("Speichere Regel: " + name);
    }

    @Override
    public List<String> getAllRuleNames() {
        return Collections.emptyList();
    }

    @Override
    public String getRulePrompt(String name) {
        return "";
    }
}
