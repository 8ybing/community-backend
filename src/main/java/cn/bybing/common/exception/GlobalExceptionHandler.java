package cn.bybing.common.exception;

import cn.bybing.api.ApiResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Jhonny
 * @Date: 2021/11/28/18:54
 * @Description:
 */
public class GlobalExceptionHandler {
    /**
     * 捕获自定义异常
     */
    @ResponseBody
    @ExceptionHandler(value = ApiException.class)
    public ApiResult<Map<String, Object>> handle(ApiException e) {
        if (e.getErrorCode() != null) {
            return ApiResult.failed(e.getErrorCode());
        }
        return ApiResult.failed(e.getMessage());
    }
}
