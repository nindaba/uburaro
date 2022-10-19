package bi.uburaro.web.filters;

import bi.uburaro.core.services.TypeService;
import bi.uburaro.core.types.HotelType;
import org.springframework.core.env.Environment;
import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SessionInitFilter extends OncePerRequestFilter {
    private static final String PARAM_HOTEL = "query.param.hotel";
    private static final String SESSION_HOTEL = "session.current.hotel";
    private final TypeService typeService;
    private final Environment environment;

    public SessionInitFilter(TypeService typeService, Environment environment) {
        this.typeService = typeService;
        this.environment = environment;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String hotelCode = request.getParameter(environment.getProperty(PARAM_HOTEL));

        if(!ObjectUtils.isEmpty(hotelCode)){
            HotelType hotelType = typeService.findItemByCode(hotelCode, HotelType.class);
            request.getSession().setAttribute(environment.getProperty(SESSION_HOTEL),hotelType);
        }

        filterChain.doFilter(request,response);
    }
}
