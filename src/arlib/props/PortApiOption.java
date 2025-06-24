package arlib.props;

import arlib.ARModel;
import arlib.ARProp;
import arlib.ARUtils;
import arlib.entities.SWPort;
import arlib.exceptions.EntityNotExistException;

public class PortApiOption extends ARProp
{

    private final String portRef;
    private final String enableTakeAddress;
    private final String indirectApi;

    public PortApiOption(ARModel model, String portRef, String enableTakeAddress, String indirectApi)
    {
        super(model);
        this.portRef = portRef;
        this.enableTakeAddress = enableTakeAddress;
        this.indirectApi = indirectApi;
    }

    public boolean isEnableTakeAddress()
    {
        return ARUtils.isTrue(enableTakeAddress);
    }

    public boolean isIndirectApi()
    {
        return ARUtils.isTrue(indirectApi);
    }

    public SWPort getPortRef() throws EntityNotExistException
    {
        return _model.get(SWPort.class, portRef);
    }

}
