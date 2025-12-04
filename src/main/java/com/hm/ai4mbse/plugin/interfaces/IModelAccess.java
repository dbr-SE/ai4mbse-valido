package com.hm.ai4mbse.plugin.interfaces;

import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import java.util.List;

/**
 * Kapselt alle Zugriffe auf die MagicDraw Open API.
 * Marius implementiert das in modules/catiamsosa.
 */
public interface IModelAccess {
    /**
     * Gibt das Element zurück, das der Nutzer im Baum ausgewählt hat.
     */
    Element getSelectedElement();

    /**
     * Sammelt alle Elemente in einem Paket (rekursiv).
     */
    List<Element> getElementsInPackage(Element packageElement);

    /**
     * Prüft auf Stereotypen (wichtig für Filter).
     */
    boolean hasStereotype(Element element, String stereotypeName);
}
