package dev.donix.arlib.props;

import dev.donix.arlib.ARModel;
import dev.donix.arlib.entities.InterfaceElement;
import dev.donix.arlib.exceptions.EntityNotExistException;

public class ModeSwitchComSpec extends ComSpec
{

    private final String _modeGroupRef;
    private final String _enhancedModeApi;
    private final String _supportAsynchronousModeSwitch;

    public ModeSwitchComSpec(ARModel model, String modeGroupRef, String enhancedModeApi, String supportAsynchronousModeSwitch)
    {
        super(model);
        this._modeGroupRef = modeGroupRef;
        this._enhancedModeApi = enhancedModeApi;
        this._supportAsynchronousModeSwitch = supportAsynchronousModeSwitch;
    }

    @Override
    public InterfaceElement getUsedInterfaceElement() throws EntityNotExistException
    {
        return _model.get(InterfaceElement.class, _modeGroupRef);
    }
}
