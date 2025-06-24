package arlib.props;

import arlib.ARModel;
import arlib.entities.InterfaceElement;
import arlib.exceptions.EntityNotExistException;

public final class NonqueuedComSpec extends ComSpec
{
    private final String _dataElementRef;
    private final String _handleOutOfRange;
    private final String _handleOutOfRangeStatus;
    private final String _usesEndToEndProtection;
    private final String _aliveTimeout;
    private final String _enableUpdate;
    private final String _handleNeverReceived;
    private final String _handleTimeoutTime;
    private final InitValue _initValue;

    public NonqueuedComSpec(ARModel model, String dataElementRef, String handleOutOfRange, String handleOutOfRangeStatus, String usesEndToEndProtection, String aliveTimeout, String enableUpdate, String handleNeverReceived, String handleTimeoutTime, InitValue initValue)
    {
        super(model);
        this._dataElementRef = dataElementRef;
        this._handleOutOfRange = handleOutOfRange;
        this._handleOutOfRangeStatus = handleOutOfRangeStatus;
        this._usesEndToEndProtection = usesEndToEndProtection;
        this._aliveTimeout = aliveTimeout;
        this._enableUpdate = enableUpdate;
        this._handleNeverReceived = handleNeverReceived;
        this._handleTimeoutTime = handleTimeoutTime;
        this._initValue = initValue;
    }

    @Override
    public String toString()
    {
        return super.toString() + " " + _dataElementRef;
    }

    @Override
    public InterfaceElement getUsedInterfaceElement() throws EntityNotExistException
    {
        return _model.get(InterfaceElement.class, _dataElementRef);
    }
}
