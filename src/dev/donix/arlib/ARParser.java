package dev.donix.arlib;

import arlib.parsers.*;
import arlib.parsers.entities.*;
import arlib.parsers.props.*;
import dev.donix.arlib.parsers.*;
import dev.donix.arlib.parsers.entities.*;
import dev.donix.arlib.parsers.props.*;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

final class ARParser
{

    enum ARXMLState
    {
        ROOT,
        AUTOSAR,
        PACKAGE_LIST,
        PACKAGE,
        ELEMENT_LIST,
        ELEMENT,
        DESC
    }

    private final static HashMap<String, IARTagParser> Parsers = new HashMap<>();

    static class ARXMLHandler extends DefaultHandler
    {
        private final EmptyParser EMPTY = new EmptyParser();

        private final ARModel _model;
        private final List<AREntity> _collected;
        private final List<FileParseProblem> _problems;

        private final Stack<String> _path = new Stack<>();
        private final Stack<String> _elements = new Stack<>();
        private final StringBuilder _textBuffer = new StringBuilder();


        private ARXMLState _state;
        private IARTagParser _elementParserCache;
        private ARXMLState _destLast;

        public ARXMLHandler(ARModel _model, List<AREntity> collected, List<FileParseProblem> problems)
        {
            this._model = _model;
            this._collected = collected;
            this._problems = problems;
        }

        @Override
        public void startDocument() throws SAXException
        {
            _state = ARXMLState.ROOT;
        }

        @Override
        public void endDocument() throws SAXException
        {
            if (_state != ARXMLState.ROOT)
            {
                throw new SAXException("Invalid structure found, parsing not finished in ROOT state");
            }
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException
        {
            _textBuffer.append(ch, start, length);
        }

        @Override
        public void startElement(String ignored1, String ignored2, String tag, Attributes attributes) throws SAXException
        {

            // Reset text buffer
            _textBuffer.setLength(0);

            // Check for Element
            if (_state == ARXMLState.ELEMENT_LIST || _state == ARXMLState.ELEMENT)
            {
                if(ARParser.Parsers.containsKey(tag))
                {
                    _elementParserCache = ARParser.Parsers.get(tag);
                    _elements.push(tag);

                    _elementParserCache.start(String.join("/", _path), tag);

                    _state = ARXMLState.ELEMENT;
                }
            }

            // Start Tag processing
            if(_elements.empty())
            {
                switch (tag)
                {
                    case ARTag.AUTOSAR:
                        if (_state == ARXMLState.ROOT)
                        {
                            _state = ARXMLState.AUTOSAR;
                        }
                        else
                        {
                            throw new SAXException(ARTag.AUTOSAR + " tag found outside of ROOT | " + _state.toString());
                        }
                        break;

                    case ARTag.SUB_PACKAGES:
                    case ARTag.TOP_LEVEL_PACKAGES:
                    case ARTag.PACKAGE_LIST:
                        if (_state == ARXMLState.PACKAGE || _state == ARXMLState.AUTOSAR)
                        {
                            _state = ARXMLState.PACKAGE_LIST;
                        }
                        else
                        {
                            throw new SAXException(ARTag.PACKAGE_LIST + " tag found outside of " + ARTag.AUTOSAR + " or " + ARTag.PACKAGE + " | " + _state.toString());
                        }
                        break;

                    case ARTag.PACKAGE:
                        if (_state == ARXMLState.PACKAGE_LIST)
                        {
                            _state = ARXMLState.PACKAGE;
                        }
                        else
                        {
                            throw new SAXException(ARTag.PACKAGE + " tag found outside " + ARTag.PACKAGE_LIST + " | " + _state.toString());
                        }
                        break;

                    case ARTag.ELEMENT_LIST:
                        if (_state == ARXMLState.PACKAGE)
                        {
                            _state = ARXMLState.ELEMENT_LIST;
                        }
                        else
                        {
                            throw new SAXException(ARTag.ELEMENT_LIST + " tag found outside " +  ARTag.PACKAGE + " | " + _state.toString());
                        }
                        break;

                    case ARTag.SHORT_NAME:
                        // Do nothing, only prevent default branch for SHORT-NAME
                        break;

                    case ARTag.LONG_NAME:
                    case ARTag.CATEGORY:
                    case ARTag.INTRODUCTION:
                    case ARTag.DESC:
                    case ARTag.REFERENCE_BASES:
                    case ARTag.ADMIN_DATA:

                        _destLast = _state;
                        _state = ARXMLState.DESC;
                        break;

                    default:
                        ARParser.Parsers.put(tag, EMPTY);
                        _elementParserCache = ARParser.Parsers.get(tag);
                        _elements.push(tag);
                        _state = ARXMLState.ELEMENT;
                }
            }
            else
            {
                _elementParserCache.startElement(tag, attributes);
            }
        }

        @Override
        public void endElement(String uri, String localName, String tag) throws SAXException
        {
            // Start Tag processing
            if(_elements.empty())
            {

                // Inside structure
                switch (tag)
                {
                    case ARTag.AUTOSAR:
                        if (_state == ARXMLState.AUTOSAR)
                        {
                            _state = ARXMLState.ROOT;
                        }
                        else
                        {
                            throw new SAXException(ARTag.AUTOSAR + " close found but in wrong state " + _state.toString());
                        }
                        break;

                    case ARTag.SUB_PACKAGES:
                    case ARTag.TOP_LEVEL_PACKAGES:
                    case ARTag.PACKAGE_LIST:
                        if (_state == ARXMLState.PACKAGE_LIST)
                        {
                            if(_path.empty())
                            {
                                _state = ARXMLState.AUTOSAR;
                            }
                            else
                            {
                                _state = ARXMLState.PACKAGE;
                            }
                        }
                        else
                        {
                            throw new SAXException(ARTag.PACKAGE_LIST + " close tag found but in wrong state | " + _state.toString());
                        }
                        break;

                    case ARTag.PACKAGE:
                        if (_state == ARXMLState.PACKAGE)
                        {
                            _path.pop();
                            _state = ARXMLState.PACKAGE_LIST;
                        }
                        else
                        {
                            throw new SAXException(ARTag.PACKAGE + " close tag found but in wrong state | " + _state.toString());
                        }
                        break;

                    case ARTag.ELEMENT_LIST:
                        if (_state == ARXMLState.ELEMENT_LIST)
                        {
                            _state = ARXMLState.PACKAGE;
                        }
                        else
                        {
                            throw new SAXException(ARTag.ELEMENT_LIST + " close tag found but in wrong state | " + _state.toString());
                        }
                        break;

                    case ARTag.LONG_NAME:
                    case ARTag.CATEGORY:
                    case ARTag.INTRODUCTION:
                    case ARTag.DESC:
                    case ARTag.REFERENCE_BASES:
                    case ARTag.ADMIN_DATA:
                        _state = _destLast;
                        break;

                    case ARTag.SHORT_NAME:
                        if (_state == ARXMLState.PACKAGE)
                        {
                            _path.push(_textBuffer.toString());
                        }
                }
            }

            // Inside element
            else
            {
                // Check if end of element found
                if(tag.equals(_elements.peek()))
                {
                    _elements.pop();
                    _elementParserCache.shareInfo(ARTag.ARLIB_SELF, _textBuffer.toString());

                    if (_elementParserCache instanceof IAREntityParser)
                    {
                        _path.pop();

                        AREntity entity = ((IAREntityParser) _elementParserCache).end(_model);

                        // Check sub parsers in stack
                        if (_elements.empty())
                        {
                            _elementParserCache = null;
                            _state = ARXMLState.ELEMENT_LIST;
                        }
                        else
                        {
                            _elementParserCache = ARParser.Parsers.get(_elements.peek());
                            _elementParserCache.passEntity(entity);
                        }

                        _collected.add(entity);
                    }
                    else if (_elementParserCache instanceof IARPropParser)
                    {
                        ARProp prop = ((IARPropParser) _elementParserCache).end(_model);

                        // Check sub parsers in stack
                        if (_elements.empty())
                        {
                            throw new SAXException("A Prop was parsed but it could not be assigned to an entity");
                        }
                        else
                        {
                            _elementParserCache = ARParser.Parsers.get(_elements.peek());
                            _elementParserCache.passProp(prop);
                        }
                    }
                    else if (_elementParserCache instanceof EmptyParser)
                    {
                        if (_elements.empty())
                        {
                            _elementParserCache = null;
                            _state = ARXMLState.ELEMENT_LIST;
                        }
                        else
                        {
                            _elementParserCache = ARParser.Parsers.get(_elements.peek());
                        }
                    }
                    else
                    {
                        throw new SAXException("Currently used ElementParser is not IAREntityParser or IARPropParser");
                    }
                }
                else
                {
                    String shortName = _textBuffer.toString();
                    if (tag.equals(ARTag.SHORT_NAME))
                    {

                        if (_elementParserCache instanceof IAREntityParser)
                        {
                            IAREntityParser current = (IAREntityParser) _elementParserCache;
                            if(current.shareInfo(ARTag.SHORT_NAME, shortName))
                            {
                                _path.push(shortName);
                            }
                        }
                    }

                    _elementParserCache.endElement(tag, shortName);
                }
            }
        }
    }

    static FileParseResult ParseARXML(ARModel model, Path path)
    {
        List<AREntity> collected = new ArrayList<>();
        List<FileParseProblem> problems = new ArrayList<>();

        try
        {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            ARXMLHandler handler = new ARXMLHandler(model, collected, problems);
            saxParser.parse(path.toAbsolutePath().toString(), handler);
        }
        catch (ParserConfigurationException | SAXException exception)
        {
            problems.add(new FileParseProblem(String.format("Parsing failed due to: " + exception.getMessage()), true));
        }
        catch (IOException exception)
        {
            problems.add(new FileParseProblem(String.format("File cannot be opened: %s", exception.getMessage()), true));
        }
        catch (Exception exception)
        {
            problems.add(new FileParseProblem(String.format("Parsing failed due to runtime error: " + exception.getMessage()), true));
        }

        return new FileParseResult(path, problems, collected);
    }

    static {

        List<IARTagParser> parsers = new ArrayList<>();

        // Add parsers here
        parsers.add(new SWComponentParser());
        parsers.add(new SWPortParser());
        parsers.add(new SWInternalBehaviourParser());
        parsers.add(new NonqueuedComSpecParser());
        parsers.add(new InitValueParser());
        parsers.add(new SWImplementationParser());
        parsers.add(new ComSpecParser());
        parsers.add(new ModeSwitchComSpecParser());
        parsers.add(new DataTypeMappingParser());
        parsers.add(new ModeIRefParser());
        parsers.add(new SWModeSwitchEventParser());
        parsers.add(new SWTimingEventParser());
        parsers.add(new PortApiOptionParser());
        parsers.add(new ModeAccessPointParser());
        parsers.add(new SWVariableAccessParser());
        parsers.add(new SWSynchronousServerCallPointParsers());
        parsers.add(new SWRunnableEntityParser());
        parsers.add(new RoleBasedPortAssignmentParser());
        parsers.add(new SWNVBlockNeedsParser());
        parsers.add(new SWServiceDependencyParser());
        parsers.add(new InterfaceParser());
        parsers.add(new InterfaceElementParser());
        parsers.add(new SWDataReceivedEventParser());
        parsers.add(new SWParameterDataParser());
        parsers.add(new SWDelegationConnectorParser());
        parsers.add(new SWPrototypeComponentParser());
        parsers.add(new SWCompositionParser());
        parsers.add(new SWAssemblyConnectorParser());

        //

        for (IARTagParser parser : parsers)
        {
            ARTagBindingContainer container = parser.getClass().getAnnotation(ARTagBindingContainer.class);
            if(container != null)
            {
                for (ARTagBinding elem : container.value())
                {
                    Parsers.put(elem.value(), parser.factory());
                }
            }
            else
            {
                ARTagBinding parserAnnotation = parser.getClass().getAnnotation(ARTagBinding.class);
                Parsers.put(parserAnnotation.value(), parser.factory());
            }


        }
    }

}
