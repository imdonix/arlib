package dev.donix.arlib.entities;

import dev.donix.arlib.ARModel;
import dev.donix.arlib.exceptions.EntityNotExistException;

public class SWAssemblyConnector extends SWConnector
{
    private final String _providerComponentRef;
    private final String _providerPortRef;
    private final String _requesterComponentRef;
    private final String _requesterPortRef;

    public SWAssemblyConnector(ARModel _model, String _path, String _shortName, String _providerComponentRef, String _providerPortRef, String _requesterComponentRef, String _requesterPortRef)
    {
        super(_model, _path, _shortName);
        this._providerComponentRef = _providerComponentRef;
        this._providerPortRef = _providerPortRef;
        this._requesterComponentRef = _requesterComponentRef;
        this._requesterPortRef = _requesterPortRef;
    }

    public SWComponent getProviderComponent() throws EntityNotExistException
    {
        return _model.get(SWComponent.class, _providerComponentRef);
    }

    public SWComponent getRequesterComponent() throws EntityNotExistException
    {
        return _model.get(SWComponent.class, _requesterComponentRef);
    }

    @Override
    public SWPort getRightPort() throws EntityNotExistException
    {
        return _model.get(SWPort.class, _requesterPortRef);
    }

    @Override
    public SWPort getLeftPort() throws EntityNotExistException
    {
        return _model.get(SWPort.class, _providerPortRef);
    }
}
