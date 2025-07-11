package dev.donix.arlib.entities;

import dev.donix.arlib.AREntity;
import dev.donix.arlib.ARModel;
import dev.donix.arlib.exceptions.EntityNotExistException;

public abstract class SWPort extends AREntity
{

    protected final String _interfaceRef;

    SWPort(ARModel _model, String _path, String _shortName, String interfaceRef)
    {
        super(_model, _path, _shortName);
        _interfaceRef = interfaceRef;
    }

    public Interface getInterface() throws EntityNotExistException
    {
        return _model.get(Interface.class, _interfaceRef);
    }
}
