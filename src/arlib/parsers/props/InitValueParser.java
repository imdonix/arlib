package arlib.parsers.props;

import arlib.ARModel;
import arlib.ARProp;
import arlib.ARTag;
import arlib.parsers.ARTagBinding;
import arlib.parsers.IARPropParser;
import arlib.parsers.IARTagParser;
import arlib.parsers.SimpleParser;
import arlib.props.InitApplicationValueSpecification;
import arlib.props.InitArrayValueSpecification;
import arlib.props.InitConstantReference;
import arlib.props.InitRecordValueSpecification;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import java.util.ArrayList;
import java.util.List;

@ARTagBinding(ARTag.APPLICATION_VALUE_SPECIFICATION)
@ARTagBinding(ARTag.CONSTANT_REFERENCE)
@ARTagBinding(ARTag.ARRAY_VALUE_SPECIFICATION)
@ARTagBinding(ARTag.RECORD_VALUE_SPECIFICATION)
public class InitValueParser extends SimpleParser implements IARPropParser
{

    private List<InitApplicationValueSpecification> _values;
    private List<InitArrayValueSpecification> _arrays;
    private List<InitConstantReference> _constans;
    private List<InitRecordValueSpecification> _records;

    @Override
    public IARTagParser factory()
    {
        return new InitValueParser();
    }

    @Override
    protected void declareNeeds()
    {
        addTagPathNeed(ARTag.CONSTANT_REF);
        addTagPathNeed(ARTag.SHORT_LABEL);
        addTagPathNeed(ARTag.CATEGORY);
        addTagPathNeed(ARTag.combine(ARTag.SW_VALUE_CONT, ARTag.UNIT_REF));
        addTagPathNeed(ARTag.combine(ARTag.SW_VALUE_CONT, ARTag.SW_VALUES_PHYS, ARTag.V));
    }

    @Override
    public void startElement(String tag, Attributes attributes) throws SAXException
    {
        super.startElement(tag, attributes);

        _values = new ArrayList<>();
        _arrays = new ArrayList<>();
        _constans = new ArrayList<>();
        _records = new ArrayList<>();
    }

    @Override
    public ARProp end(ARModel model) throws SAXException
    {
        if (getCaptured(ARTag.ARLIB_TAG).equals(ARTag.APPLICATION_VALUE_SPECIFICATION))
        {
            return new InitApplicationValueSpecification(
                    model,
                    getCaptured(ARTag.SHORT_LABEL),
                    getCaptured(ARTag.CATEGORY),
                    getCaptured(ARTag.combine(ARTag.SW_VALUE_CONT, ARTag.UNIT_REF)),
                    getCaptured(ARTag.combine(ARTag.SW_VALUE_CONT, ARTag.SW_VALUES_PHYS, ARTag.V))
            );
        }
        else if (getCaptured(ARTag.ARLIB_TAG).equals(ARTag.CONSTANT_REFERENCE))
        {
            return new InitConstantReference(
                    model,
                    getCaptured(ARTag.CONSTANT_REF)
            );
        }
        else if (getCaptured(ARTag.ARLIB_TAG).equals(ARTag.ARRAY_VALUE_SPECIFICATION))
        {
            return new InitArrayValueSpecification(
                    model,
                    _values
            );
        }
        else if (getCaptured(ARTag.ARLIB_TAG).equals(ARTag.RECORD_VALUE_SPECIFICATION))
        {
            return new InitRecordValueSpecification(
                    model,
                    _values,
                    _arrays,
                    _constans
            );
        }
        else
        {
            throw new SAXException("Init type could not be recognized " + getCaptured(ARTag.ARLIB_TAG));
        }
    }

    @Override
    public void passProp(ARProp prop) throws SAXException
    {
        if (prop instanceof InitApplicationValueSpecification)
        {
            _values.add((InitApplicationValueSpecification) prop);
        }
        else if (prop instanceof  InitArrayValueSpecification)
        {
            _arrays.add((InitArrayValueSpecification) prop);
        }
        else if (prop instanceof InitConstantReference)
        {
            _constans.add((InitConstantReference) prop);
        }
        else if (prop instanceof InitRecordValueSpecification)
        {
            _records.add((InitRecordValueSpecification) prop);
        }
        else
        {
            throw new SAXException("Illegal prop type was passed to InitParser {" + prop.getClass().getName() + "}");
        }
    }
}
