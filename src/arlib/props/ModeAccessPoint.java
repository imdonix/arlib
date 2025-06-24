package arlib.props;

import arlib.ARModel;
import arlib.ARProp;
import arlib.entities.InterfaceElement;
import arlib.entities.SWPort;
import arlib.exceptions.EntityNotExistException;

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
