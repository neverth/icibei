package fun.neverth.icibei.authentication.wrapper;

import lombok.Getter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * HttpServletRequest包装类，因为需要包含url和方法
 *
 * @author neverth.li
 * @date 2020/9/29 15:13
 */
@Getter
public class HttpServletRequestAuthWrapper extends HttpServletRequestWrapper {

    private final String url;
    private final String method;

    public HttpServletRequestAuthWrapper(HttpServletRequest request, String url, String method) {
        super(request);
        this.url = url;
        this.method = method;
    }
}
