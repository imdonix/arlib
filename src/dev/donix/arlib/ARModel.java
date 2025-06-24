package dev.donix.arlib;

import dev.donix.arlib.exceptions.EntityNotExistException;
import dev.donix.arlib.parsers.FileParseResult;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public final class ARModel
{

    private final HashMap<String, AREntity> _entityReferences = new HashMap<>();
    private final HashMap<Path, FileParseResult> _files = new HashMap<>();

    ARModel(List<Path> arxmls)
    {
        for (Path arxml : arxmls)
        {
            FileParseResult result = ARParser.ParseARXML(this, arxml);
            if (result.isSuccessful())
            {
                for (AREntity entity : result.getCollected())
                {
                    if (!_entityReferences.containsKey(entity.getReference()))
                    {
                        _entityReferences.put(entity.getReference(), entity);
                    }
                }
            }
            _files.put(arxml, result);
        }
    }

    @SuppressWarnings("unchecked")
    public <T extends AREntity> List<T> getAll(Class<T> clazz)
    {
        return (List<T>) _entityReferences.values().stream().filter(clazz::isInstance).collect(Collectors.toList());
    }

    @SuppressWarnings("unchecked")
    public <T extends AREntity> T get(Class<T> clazz, String reference) throws EntityNotExistException
    {
        if(!reference.startsWith("/"))
        {
            throw new EntityNotExistException("AR paths must start with '/' character");
        }

        AREntity entity = _entityReferences.get(reference);

        if(entity == null)
        {
            throw new EntityNotExistException("Entity with reference '" + reference + "' does not exist in the model");
        }

        if(!clazz.isInstance(entity))
        {
            throw new EntityNotExistException("Entity was found with this reference '" + reference + "' but its not assignable to the given class");
        }

        return (T) entity;
    }

    @SuppressWarnings("unchecked")
    public <T extends AREntity> List<T> getAllByReference(Class<T> clazz, List<String> refs)
    {
        List<T> tmp = new ArrayList<>(refs.size());
        for (String ref : refs)
        {
            T elem = (T) _entityReferences.get(ref);
            if(elem != null)
            {
                tmp.add(elem);
            }
        }
        return tmp;
    }

    public List<FileParseResult> getFileParseResults()
    {
        return new ArrayList<>(_files.values());
    }
}
