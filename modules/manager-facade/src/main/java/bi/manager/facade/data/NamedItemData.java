package bi.manager.facade.data;

import bi.uburaro.facade.data.ItemData;

import java.util.Objects;

public class NamedItemData extends ItemData {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NamedItemData)) return false;

        NamedItemData that = (NamedItemData) o;

        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}
