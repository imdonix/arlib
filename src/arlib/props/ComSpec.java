package arlib.props;

import arlib.ARModel;
import arlib.ARProp;
import arlib.entities.InterfaceElement;
import arlib.exceptions.EntityNotExistException;

public abstract class ComSpec extends ARProp
{
    public ComSpec(ARModel model)
    {
        super(model);
    }

    public abstract InterfaceElement getUsedInterfaceElement() throws EntityNotExistException;

}
