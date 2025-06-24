package arlib.props;

import arlib.ARModel;
import arlib.entities.InterfaceElement;
import arlib.exceptions.EntityNotExistException;

public class CSComSpec extends ComSpec
{

    private final String _operationRef;
    private final String _queueLength;


    public CSComSpec(ARModel model, String operationRef, String queueLength)
    {
        super(model);
        this._operationRef = operationRef;
        this._queueLength = queueLength;
    }

    @Override
    public InterfaceElement getUsedInterfaceElement() throws EntityNotExistException
    {
        return _model.get(InterfaceElement.class, _operationRef);
    }

    public String getQueueLength()
    {
        return _queueLength;
    }
}
