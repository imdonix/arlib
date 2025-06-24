package arlib.parsers.entities;

import arlib.AREntity;
import arlib.ARModel;
import arlib.ARTag;
import arlib.entities.SWVariableAccess;
import arlib.parsers.ARTagBinding;
import arlib.parsers.IAREntityParser;
import arlib.parsers.IARTagParser;
import arlib.parsers.SimpleParser;
import org.xml.sax.SAXException;

@ARTagBinding(ARTag.VARIABLE_ACCESS)
public class SWVariableAccessParser extends SimpleParser implements IAREntityParser
{
    @Override
    public IARTagParser factory()
    {
        return new SWVariableAccessParser();
    }

    @Override
    protected void declareNeeds()
    {
        addTagPathNeed(ARTag.combine(ARTag.ACCESSED_VARIABLE, ARTag.AUTOSAR_VARIABLE_IREF, ARTag.PORT_PROTOTYPE_REF));
        addTagPathNeed(ARTag.combine(ARTag.ACCESSED_VARIABLE, ARTag.AUTOSAR_VARIABLE_IREF, ARTag.TARGET_DATA_PROTOTYPE_REF));
    }

    @Override
    public AREntity end(ARModel model) throws SAXException
    {
        return new SWVariableAccess(
                model,
                getCaptured(ARTag.ARLIB_PATH),
                getCaptured(ARTag.SHORT_NAME),
                getCaptured(ARTag.combine(ARTag.ACCESSED_VARIABLE, ARTag.AUTOSAR_VARIABLE_IREF, ARTag.PORT_PROTOTYPE_REF)),
                getCaptured(ARTag.combine(ARTag.ACCESSED_VARIABLE, ARTag.AUTOSAR_VARIABLE_IREF, ARTag.TARGET_DATA_PROTOTYPE_REF))
        );
    }
}
