package dev.donix.arlib.entities;

import dev.donix.arlib.ARModel;
import dev.donix.arlib.exceptions.EntityNotExistException;

public class SWDelegationConnector extends SWConnector
{

    private final String _innerComponentRef;
    private final String _innerPortRef;
    private final String _outerPortRef;

    public SWDelegationConnector(ARModel _model, String _path, String _shortName, String _innerComponentRef, String _innerPortRef, String _outerPortRef)
    {
        super(_model, _path, _shortName);
        this._innerComponentRef = _innerComponentRef;
        this._innerPortRef = _innerPortRef;
        this._outerPortRef = _outerPortRef;
    }

    public SWComponent getComponent() throws EntityNotExistException
    {
        return _model.get(SWComponent.class, _innerComponentRef);
    }

    @Override
    public SWPort getRightPort() throws EntityNotExistException
    {
        return _model.get(SWPort.class, _innerPortRef);
    }

    @Override
    public SWPort getLeftPort() throws EntityNotExistException
    {
        return _model.get(SWPort.class, _outerPortRef);
    }
}
