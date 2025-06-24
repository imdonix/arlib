package dev.donix.arlib.props;

import dev.donix.arlib.ARModel;

import java.util.List;

public class InitArrayValueSpecification extends InitValue
{
    private final List<InitApplicationValueSpecification> _values;

    public InitArrayValueSpecification(ARModel model, List<InitApplicationValueSpecification> values)
    {
        super(model);
        _values = values;
    }
}
