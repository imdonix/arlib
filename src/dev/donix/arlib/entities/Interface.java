package dev.donix.arlib.entities;

import dev.donix.arlib.AREntity;
import dev.donix.arlib.ARModel;
import dev.donix.arlib.ARUtils;

import java.util.List;


/**
 * Describles an Interface like Autosar element including:
 * <ul>
 *     <li>SENDER-RECEIVER-INTERFACE</li>
 *     <li>CLIENT-SERVER-INTERFACE</li>
 * <ul>
 */
public final class Interface extends AREntity
{

    private final String _isService;
    private final List<String> _elementsRefs;

    public Interface(ARModel _model, String _path, String _shortName, String isService, List<String> elementsRefs)
    {
        super(_model, _path, _shortName);
        this._isService = isService;
        this._elementsRefs = elementsRefs;
    }

    public boolean isService()
    {
        return ARUtils.isTrue(_isService);
    }

    public List<InterfaceElement> getElements()
    {
        return _model.getAllByReference(InterfaceElement.class, _elementsRefs);
    }

}
