package bi.uburaro.web.filters;

import bi.uburaro.core.services.HotelService;
import bi.uburaro.core.services.TypeService;
import bi.uburaro.core.types.HotelType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.env.Environment;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class SessionInitFilter extends OncePerRequestFilter {
    private static final String PARAM_HOTEL = "query.param.hotel";
    private static final String SESSION_HOTEL = "session.current.hotel";
    private final HotelService hotelService;
    private final Environment environment;

    public SessionInitFilter(HotelService hotelService, Environment environment) {
        this.hotelService = hotelService;
        this.environment = environment;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        Optional.ofNullable(request.getParameter(environment.getProperty(PARAM_HOTEL)))
                .map(hotelService::getHotelByCode)
                .ifPresent(hotelType -> request.getSession().setAttribute(environment.getProperty(SESSION_HOTEL), hotelType));

        filterChain.doFilter(request, response);
    }
}
