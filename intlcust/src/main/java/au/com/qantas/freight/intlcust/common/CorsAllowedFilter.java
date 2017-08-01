package au.com.qantas.freight.intlcust.common;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CorsAllowedFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Not needed.
    }

    @Override
    public final void doFilter(final ServletRequest servletRequest,
                               final ServletResponse servletResponse,
                               final FilterChain chain)
            throws IOException, ServletException {
        if (servletRequest instanceof HttpServletRequest) {
            HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
            String origin = httpServletRequest.getHeader("Origin");
            if (!StringUtils.isEmpty(origin)) {
                HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
                httpServletResponse.setHeader("Access-Control-Allow-Origin","*");
                // TODO - determine why url encoding the hostname doesn't work
                //URLEncoder.encode(origin, StandardCharsets.UTF_8.toString()));
                //origin);
                httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
                //httpServletResponse.setHeader("Access-Control-Max-Age", "3600");
                httpServletResponse.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
                //httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true");
            }
        }
        chain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        // Not needed.
    }
}
