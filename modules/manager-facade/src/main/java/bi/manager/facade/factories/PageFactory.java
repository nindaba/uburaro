package bi.manager.facade.factories;

import bi.manager.core.utils.MBPageable;
import bi.manager.facade.data.MBPageableData;

public class PageFactory {
    public static MBPageable createPage(MBPageableData page) {
        return  new MBPageable(
                page.getCurrentPage(),
                page.getPageSize(),
                page.getSort(),
                page.getSortOrder()
        );
    }
    public static MBPageableData createPage(int pageSize, int currentPage, String sort, String sortOrder) {
        MBPageableData page = new MBPageableData();
        page.setCurrentPage(currentPage);
        page.setPageSize(pageSize);
        page.setSort(sort);
        page.setSortOrder(sortOrder);
        return page;
    }
}
