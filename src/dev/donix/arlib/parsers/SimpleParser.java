package dev.donix.arlib.parsers;

import dev.donix.arlib.ARTag;
import dev.donix.arlib.AREntity;
import dev.donix.arlib.ARProp;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;

public abstract class SimpleParser implements IARTagParser
{

    private final HashMap<String, String> _capturedText = new HashMap<>();
    private final HashMap<String, Attributes> _capturedAttribute = new HashMap<>();

    private final HashSet<String> _needs = new HashSet<>();
    private final Stack<String> _localPath = new Stack<>();

    @Override
    public void start(String path, String tag)
    {
        _needs.clear();
        _localPath.clear();
        _capturedText.clear();
        _capturedAttribute.clear();

        _capturedText.put(ARTag.ARLIB_PATH, path);
        _capturedText.put(ARTag.ARLIB_TAG, tag);

        declareNeeds();
    }

    @Override
    public boolean shareInfo(String info, String value)
    {
        if(!_capturedText.containsKey(info))
        {
            _capturedText.put(info, value);
            return true;
        }

        return false;
    }

    @Override
    public void startElement(String tag, Attributes attributes) throws SAXException
    {
        if (!_capturedText.get(ARTag.ARLIB_TAG).equals(tag))
        {
            _localPath.push(tag);
        }

        String tmpLocalPath = parserPath();
        if (_needs.contains(tmpLocalPath))
        {
            Attributes old = _capturedAttribute.put(tmpLocalPath, attributes);
            if(old != null)
            {
                throw new SAXException("'" + tag + "' attributes was captured but it was already found");
            }
        }
    }

    @Override
    public void endElement(String tag, String text) throws SAXException
    {

        if(!_localPath.empty())
        {
            String tmpLocalPath = parserPath();
            String popped = _localPath.pop();

            if (!tag.equals(popped))
            {
                throw new SAXException("An invalid close tag found without open '" + popped + "'");
            }

            if (_needs.contains(tmpLocalPath))
            {
                String old = _capturedText.put(tmpLocalPath, text);
                if(old != null)
                {
                    throw new SAXException("'" + tag + "'text was captured but it was already found as '" + old + "' -> '" + text + "'");
                }
            }
        }

    }

    @Override
    public void passEntity(AREntity entity) throws SAXException
    {
        throw new SAXException("For {" + getClass().getName() + "} no entity can be passed [" + entity.getClass().getName() + "]");
    }

    @Override
    public void passProp(ARProp prop) throws SAXException
    {
        throw new SAXException("For {" + getClass().getName() + "} no props can be passed. [" + prop.getClass().getName() + "]");
    }

    protected String parserPath()
    {
        return String.join("|", _localPath);
    }

    protected void addTagPathNeed(String tagPath)
    {
        _needs.add(tagPath);
    }

    public String getCaptured(String tagPath)
    {
        return _capturedText.get(tagPath);
    }

    public boolean hasCaptured(String tagPath)
    {
        return _capturedText.containsKey(tagPath);
    }

    public Attributes getAttributes(String tagPath)
    {
        return _capturedAttribute.get(tagPath);
    }

    protected abstract void declareNeeds();

}
