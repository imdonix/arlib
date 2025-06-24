package dev.donix.arlib.props;

import dev.donix.arlib.ARModel;
import dev.donix.arlib.ARProp;
import dev.donix.arlib.entities.InterfaceElement;
import dev.donix.arlib.entities.SWPort;
import dev.donix.arlib.exceptions.EntityNotExistException;

public class ModeAccessPoint extends ARProp
{

    private final String _portPrototypeRef;
    private final String _targetRequiredOperationRef;

    public ModeAccessPoint(ARModel model, String portPrototypeRef, String targetRequiredOperationRef)
    {
        super(model);
        this._portPrototypeRef = portPrototypeRef;
        this._targetRequiredOperationRef = targetRequiredOperationRef;
    }

    public SWPort getPortRef() throws EntityNotExistException
    {
        return _model.get(SWPort.class, _portPrototypeRef);
    }

    public InterfaceElement getTargetRequiredOperationRef() throws EntityNotExistException
    {
        return _model.get(InterfaceElement.class, _targetRequiredOperationRef);
    }
}
