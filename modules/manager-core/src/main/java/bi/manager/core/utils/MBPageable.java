package bi.manager.core.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.AbstractPageRequest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

public class MBPageable extends PageRequest {
    /**
     * Creates a new {@link PageRequest} with sort parameters applied.
     *
     * @param page zero-based page index, must not be negative.
     * @param size the size of the page to be returned, must be greater than 0.
     * @param sortProperty must not be {@literal null}, use {@link Sort#unsorted()} instead.
     */
    public MBPageable(int page, int size, String sortProperty, String sortDirection) {
        super(page, size,  Sort.by(
                new Sort.Order(StringUtils.equals(sortDirection, "asc") ? Sort.Direction.ASC : Sort.Direction.DESC, sortProperty)
        ));
    }
}
