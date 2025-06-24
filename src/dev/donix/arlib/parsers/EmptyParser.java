package dev.donix.arlib.parsers;

import dev.donix.arlib.AREntity;
import dev.donix.arlib.ARProp;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

public final class EmptyParser implements IARTagParser
{

    @Override
    public IARTagParser factory()
    {
        return new EmptyParser();
    }

    @Override
    public void start(String path, String tag) {}

    @Override
    public void startElement(String tag, Attributes attributes) throws SAXException {}

    @Override
    public void endElement(String tag, String text) throws SAXException {}

    @Override
    public boolean shareInfo(String info, String value)
    {
        return false;
    }

    @Override
    public void passEntity(AREntity entity) throws SAXException {}

    @Override
    public void passProp(ARProp prop) throws SAXException {}

}
