package dev.donix.arlib.parsers.entities;

import dev.donix.arlib.AREntity;
import dev.donix.arlib.ARModel;
import dev.donix.arlib.ARTag;
import dev.donix.arlib.entities.SWSynchronousServerCallPoint;
import dev.donix.arlib.parsers.ARTagBinding;
import dev.donix.arlib.parsers.IAREntityParser;
import dev.donix.arlib.parsers.IARTagParser;
import dev.donix.arlib.parsers.SimpleParser;
import org.xml.sax.SAXException;

@ARTagBinding(ARTag.SYNCHRONOUS_SERVER_CALL_POINT)
public class SWSynchronousServerCallPointParsers extends SimpleParser implements IAREntityParser
{

    @Override
    public IARTagParser factory()
    {
        return new SWSynchronousServerCallPointParsers();
    }

    @Override
    protected void declareNeeds()
    {
        addTagPathNeed(ARTag.combine(ARTag.OPERATION_IREF, ARTag.CONTEXT_R_PORT_REF));
        addTagPathNeed(ARTag.combine(ARTag.OPERATION_IREF, ARTag.TARGET_REQUIRED_OPERATION_REF));
    }

    @Override
    public AREntity end(ARModel model) throws SAXException
    {
        return new SWSynchronousServerCallPoint(
                model,
                getCaptured(ARTag.ARLIB_PATH),
                getCaptured(ARTag.SHORT_NAME),
                getCaptured(ARTag.combine(ARTag.OPERATION_IREF, ARTag.CONTEXT_R_PORT_REF)),
                getCaptured(ARTag.combine(ARTag.OPERATION_IREF, ARTag.TARGET_REQUIRED_OPERATION_REF))
        );
    }


}
