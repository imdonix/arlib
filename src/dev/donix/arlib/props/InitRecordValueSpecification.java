package dev.donix.arlib.props;

import dev.donix.arlib.ARModel;

import java.util.ArrayList;
import java.util.List;

public class InitRecordValueSpecification extends InitValue
{
    private List<InitValue> _fields;

    public InitRecordValueSpecification(ARModel model, List<InitApplicationValueSpecification> values, List<InitArrayValueSpecification> arrays, List<InitConstantReference> consts)
    {
        super(model);

        this._fields = new ArrayList<>();
        _fields.addAll(values);
        _fields.addAll(arrays);
        _fields.addAll(consts);
    }
}
