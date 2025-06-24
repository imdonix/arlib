package dev.donix.arlib.props;

import dev.donix.arlib.ARModel;

public class InitApplicationValueSpecification extends InitValue
{
    private final String _shortLabel;
    private final String _category;
    private final String _unitRef;
    private final String _swValuePhys;

    public InitApplicationValueSpecification(ARModel model, String shortLabel, String category, String unitRef, String swValuePhys)
    {
        super(model);
        this._shortLabel = shortLabel;
        this._category = category;
        this._unitRef = unitRef;
        this._swValuePhys = swValuePhys;
    }
}
