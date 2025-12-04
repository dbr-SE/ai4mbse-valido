package com.hm.ai4mbse.plugin.interfaces;

import java.util.List;
import com.hm.ai4mbse.plugin.model.ReviewIssue;

/**
 * Schnittstelle für die Review-Logik.
 * Das UI ruft dies auf, die KI führt es aus.
 */
public interface IReviewService {
    /**
     * Führt ein Review durch.
     * @param reviewType Art des Reviews (z.B. "Muda", "Traceability")
     * @param scope Liste von Paket-Namen oder IDs, die geprüft werden sollen
     * @return Liste der gefundenen Probleme (für die Tabelle im UI)
     */
    List<ReviewIssue> performReview(String reviewType, List<String> scope);
}
