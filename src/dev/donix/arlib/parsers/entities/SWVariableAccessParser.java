package dev.donix.arlib.parsers.entities;

import dev.donix.arlib.AREntity;
import dev.donix.arlib.ARModel;
import dev.donix.arlib.ARTag;
import dev.donix.arlib.entities.SWVariableAccess;
import dev.donix.arlib.parsers.ARTagBinding;
import dev.donix.arlib.parsers.IAREntityParser;
import dev.donix.arlib.parsers.IARTagParser;
import dev.donix.arlib.parsers.SimpleParser;
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
