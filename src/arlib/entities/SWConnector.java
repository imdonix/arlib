package arlib.entities;

import arlib.AREntity;
import arlib.ARModel;
import arlib.exceptions.EntityNotExistException;

public abstract class SWConnector extends AREntity
{
    SWConnector(ARModel _model, String _path, String _shortName)
    {
        super(_model, _path, _shortName);
    }

    /**
     * Signals travel right to left
     * Delegation connector -> Outer port
     * Assembly connector -> Requester
     */
    public abstract SWPort getRightPort() throws EntityNotExistException;

    /**
     * Signals travel right to left
     * Delegation connector -> Inner port
     * Assembly connector -> Provider
     */
    public abstract SWPort getLeftPort() throws EntityNotExistException;
}
