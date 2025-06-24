package dev.donix.arlib.props;

import dev.donix.arlib.ARModel;
import dev.donix.arlib.ARProp;
import dev.donix.arlib.entities.InterfaceElement;
import dev.donix.arlib.exceptions.EntityNotExistException;

public abstract class ComSpec extends ARProp
{
    public ComSpec(ARModel model)
    {
        super(model);
    }

    public abstract InterfaceElement getUsedInterfaceElement() throws EntityNotExistException;

}
