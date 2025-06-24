package arlib.parsers;

import arlib.AREntity;

import java.nio.file.Path;
import java.util.List;

public final class FileParseResult
{
    private final Path _path;
    private final List<FileParseProblem> _problems;
    private final List<AREntity> _collected;

    public FileParseResult(Path path, List<FileParseProblem> problems, List<AREntity> collected)
    {
        this._path = path;
        this._problems = problems;
        this._collected = collected;
    }

    public boolean isSuccessful()
    {
        for (FileParseProblem problem : _problems)
        {
            if(problem.isCritical())
            {
                return false;
            }
        }

        return true;
    }

    public Path getPath()
    {
        return _path;
    }

    public List<FileParseProblem> getProblems()
    {
        return _problems;
    }

    public List<AREntity> getCollected()
    {
        return _collected;
    }
}
