package dev.donix.arlib.props;

import dev.donix.arlib.ARModel;

public class InitConstantReference extends InitValue
{

    private final String _constantSpecificationRef;

    public InitConstantReference(ARModel model, String constantSpecificationRef)
    {
        super(model);
        this._constantSpecificationRef = constantSpecificationRef;
    }
}
