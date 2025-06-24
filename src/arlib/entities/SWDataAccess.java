package arlib.entities;

import arlib.AREntity;
import arlib.ARModel;
import arlib.exceptions.EntityNotExistException;

public abstract class SWDataAccess extends AREntity
{

    protected final String _portPrototypeRef;
    protected final String _targetDataRef;

    public SWDataAccess(ARModel _model, String _path, String _shortName, String _portPrototypeRef, String _targetDataRef)
    {
        super(_model, _path, _shortName);
        this._portPrototypeRef = _portPrototypeRef;
        this._targetDataRef = _targetDataRef;
    }

    public SWPort getPortRef() throws EntityNotExistException
    {
        return _model.get(SWPort.class, _portPrototypeRef);
    }

    public InterfaceElement getTargetDataRef() throws EntityNotExistException
    {
        return _model.get(InterfaceElement.class, _targetDataRef);
    }
}
