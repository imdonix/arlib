package arlib.parsers.entities;

import arlib.AREntity;
import arlib.ARModel;
import arlib.ARTag;
import arlib.entities.SWDelegationConnector;
import arlib.parsers.ARTagBinding;
import arlib.parsers.IAREntityParser;
import arlib.parsers.IARTagParser;
import arlib.parsers.SimpleParser;
import org.xml.sax.SAXException;

@ARTagBinding(ARTag.DELEGATION_SW_CONNECTOR)
public class SWDelegationConnectorParser extends SimpleParser implements IAREntityParser
{
    @Override
    public IARTagParser factory()
    {
        return new SWDelegationConnectorParser();
    }

    @Override
    protected void declareNeeds()
    {
        addTagPathNeed(ARTag.combine(ARTag.INNER_PORT_IREF, ARTag.P_PORT_IN_COMPOSITION_INSTANCE_REF, ARTag.CONTEXT_COMPONENT_REF));
        addTagPathNeed(ARTag.combine(ARTag.INNER_PORT_IREF, ARTag.P_PORT_IN_COMPOSITION_INSTANCE_REF, ARTag.TARGET_P_PORT_REF));

        addTagPathNeed(ARTag.combine(ARTag.INNER_PORT_IREF, ARTag.R_PORT_IN_COMPOSITION_INSTANCE_REF, ARTag.CONTEXT_COMPONENT_REF));
        addTagPathNeed(ARTag.combine(ARTag.INNER_PORT_IREF, ARTag.R_PORT_IN_COMPOSITION_INSTANCE_REF, ARTag.TARGET_R_PORT_REF));

        addTagPathNeed(ARTag.OUTER_PORT_REF);
    }

    @Override
    public AREntity end(ARModel model) throws SAXException
    {
        if(hasCaptured(ARTag.combine(ARTag.INNER_PORT_IREF, ARTag.P_PORT_IN_COMPOSITION_INSTANCE_REF, ARTag.CONTEXT_COMPONENT_REF)))
        {
            return new SWDelegationConnector(
                    model,
                    getCaptured(ARTag.ARLIB_PATH),
                    getCaptured(ARTag.SHORT_NAME),
                    getCaptured(ARTag.combine(ARTag.INNER_PORT_IREF, ARTag.P_PORT_IN_COMPOSITION_INSTANCE_REF, ARTag.CONTEXT_COMPONENT_REF)),
                    getCaptured(ARTag.combine(ARTag.INNER_PORT_IREF, ARTag.P_PORT_IN_COMPOSITION_INSTANCE_REF, ARTag.TARGET_P_PORT_REF)),
                    getCaptured(ARTag.OUTER_PORT_REF)
            );
        }
        else if(hasCaptured(ARTag.combine(ARTag.INNER_PORT_IREF, ARTag.R_PORT_IN_COMPOSITION_INSTANCE_REF, ARTag.CONTEXT_COMPONENT_REF)))
        {
            return new SWDelegationConnector(
                    model,
                    getCaptured(ARTag.ARLIB_PATH),
                    getCaptured(ARTag.SHORT_NAME),
                    getCaptured(ARTag.combine(ARTag.INNER_PORT_IREF, ARTag.R_PORT_IN_COMPOSITION_INSTANCE_REF, ARTag.CONTEXT_COMPONENT_REF)),
                    getCaptured(ARTag.combine(ARTag.INNER_PORT_IREF, ARTag.R_PORT_IN_COMPOSITION_INSTANCE_REF, ARTag.TARGET_R_PORT_REF)),
                    getCaptured(ARTag.OUTER_PORT_REF)
            );
        }

        throw new SAXException("Found an SWDelegationConnector but it was neither R_PORT_IN_COMPOSITION_INSTANCE_REF or P_PORT_IN_COMPOSITION_INSTANCE_REF");
    }

}
