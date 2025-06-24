package dev.donix.arlib.parsers;

public final class FileParseProblem
{
    private final String _message;
    private final boolean _isCritical;

    public FileParseProblem(String message, boolean isCritical)
    {
        this._message = message;
        this._isCritical = isCritical;
    }

    public String getMessage()
    {
        return _message;
    }

    public boolean isCritical()
    {
        return _isCritical;
    }

    @Override
    public String toString()
    {
        return "FileParseProblem{" +
                "message='" + _message + '\'' +
                '}';
    }
}
