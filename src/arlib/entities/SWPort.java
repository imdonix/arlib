package arlib.entities;

import arlib.AREntity;
import arlib.ARModel;
import arlib.exceptions.EntityNotExistException;

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
