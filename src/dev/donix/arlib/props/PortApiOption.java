package dev.donix.arlib.props;

import dev.donix.arlib.ARModel;
import dev.donix.arlib.ARProp;
import dev.donix.arlib.ARUtils;
import dev.donix.arlib.entities.SWPort;
import dev.donix.arlib.exceptions.EntityNotExistException;

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
