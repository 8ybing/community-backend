package cn.bybing.api;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Jhonny
 * @Date: 2021/11/21/22:52
 * @Description:
 */
public interface IErrorCode {

    /**
     * -1:失败
     * 200：成功
     * @return 错误编码
     */
    Integer getCode();

    /**
     * 错误描述
     * @return
     */
    String getMessage();

}
