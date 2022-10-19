package bi.uburaro.core.mergers;

import java.util.Collection;
import java.util.List;

public interface Merger<TARGET,TYPE> {
    void setAdd(TYPE type);
    void setRemove(TYPE type);
    void merge();
}
