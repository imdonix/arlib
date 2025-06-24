package dev.donix.arlib.props;

import dev.donix.arlib.ARModel;
import dev.donix.arlib.ARProp;
import dev.donix.arlib.entities.SWPort;
import dev.donix.arlib.exceptions.EntityNotExistException;

public class RoleBasedPortAssignment extends ARProp
{

    private final String _portPrototypeRef;
    private final String _role;

    public RoleBasedPortAssignment(ARModel model, String portPrototypeRef, String role)
    {
        super(model);

        this._portPrototypeRef = portPrototypeRef;
        this._role = role;
    }

    public SWPort getPort() throws EntityNotExistException
    {
        return _model.get(SWPort.class, _portPrototypeRef);
    }

    public String getRole()
    {
        return _role;
    }
}
