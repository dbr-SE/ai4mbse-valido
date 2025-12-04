package com.hm.ai4mbse.plugin.modules.catiamsosa;

import com.hm.ai4mbse.plugin.interfaces.IModelAccess;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Element;
import java.util.Collections;
import java.util.List;

public class ModelAccess implements IModelAccess {
    @Override
    public Element getSelectedElement() {
        // Hier kommt Marius' Logik rein, um das selektierte Element aus Catia zu holen
        return null; 
    }

    @Override
    public List<Element> getElementsInPackage(Element packageElement) {
        return Collections.emptyList();
    }

    @Override
    public boolean hasStereotype(Element element, String stereotypeName) {
        return false;
    }
}
