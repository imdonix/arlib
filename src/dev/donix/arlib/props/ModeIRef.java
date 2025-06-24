package dev.donix.arlib.props;

import dev.donix.arlib.ARModel;
import dev.donix.arlib.ARProp;

public class ModeIRef extends ARProp
{
    private final String _contextPortRef;
    private final String _contextModeDeclarationGroupPrototypeRef;
    private final String _targetModeDeclarationRef;

    public ModeIRef(ARModel model, String contextPortRef, String contextModeDeclarationGroupPrototypeRef, String targetModeDeclarationRef)
    {
        super(model);
        this._contextPortRef = contextPortRef;
        this._contextModeDeclarationGroupPrototypeRef = contextModeDeclarationGroupPrototypeRef;
        this._targetModeDeclarationRef = targetModeDeclarationRef;
    }
}
