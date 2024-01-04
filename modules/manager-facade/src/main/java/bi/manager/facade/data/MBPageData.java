package bi.manager.facade.data;

import bi.uburaro.facade.data.ItemData;

import java.util.Collection;

public class MBPageData<ITEM extends ItemData>{
    private Collection<ITEM> content;
    private int pages;

    public Collection<ITEM> getContent() {
        return content;
    }

    public void setContent(Collection<ITEM> content) {
        this.content = content;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }
}
