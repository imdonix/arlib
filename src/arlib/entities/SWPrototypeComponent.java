package arlib.entities;

import arlib.AREntity;
import arlib.ARModel;
import arlib.exceptions.EntityNotExistException;

public class SWPrototypeComponent extends AREntity
{
    private final String _componentRef;

    public SWPrototypeComponent(ARModel _model, String _path, String _shortName, String componentRef)
    {
        super(_model, _path, _shortName);
        this._componentRef = componentRef;
    }

    public SWComponent getComponent() throws EntityNotExistException
    {
        return _model.get(SWComponent.class, _componentRef);
    }
}
