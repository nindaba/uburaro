package bi.manager.core.utils;

import bi.uburaro.core.types.ItemType;
import org.springframework.data.domain.Page;

import java.util.Collection;

public class MBPage<ITEM extends ItemType> {
    private Collection<ITEM> content;
    private int pages;

    public MBPage(Page<ITEM> page) {
        this.content = page.getContent();
        this.pages = page.getTotalPages();
    }

    public Collection<ITEM> getContent() {
        return content;
    }

    public int getPages() {
        return pages;
    }
}
