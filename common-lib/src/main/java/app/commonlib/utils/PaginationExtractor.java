package app.commonlib.utils;

import org.springframework.web.bind.ServletRequestUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public final class PaginationExtractor {

    private PaginationExtractor() {
    }

    public static Pagination extract(final HttpServletRequest request) {

        final SortDirection sortDirection = Optional.ofNullable( request.getParameter( "sortDirection" ) )
                                                    .map( SortDirection::valueOf )
                                                    .orElse( SortDirection.DESC );

        return new Pagination( ServletRequestUtils.getIntParameter(request, "page", 1)
                             , ServletRequestUtils.getIntParameter(request, "size", 64)
                             , ServletRequestUtils.getStringParameter(request, "sortField", "ID")
                             , sortDirection);
    }
}