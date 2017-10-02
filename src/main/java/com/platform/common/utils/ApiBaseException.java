package com.platform.common.utils;

/**
 * 名称：ApiBaseException <br>
 * 描述：自定义异常<br>
 *
 * @author 李鹏军
 * @version 1.0
 * @since 1.0.0
 */
public class ApiBaseException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private String errmsg;
    private int errno = 500;

    public ApiBaseException(String errmsg) {
        super(errmsg);
        this.errmsg = errmsg;
    }

    public ApiBaseException(String errmsg, Throwable e) {
        super(errmsg, e);
        this.errmsg = errmsg;
    }

    public ApiBaseException(String errmsg, int errno) {
        super(errmsg);
        this.errmsg = errmsg;
        this.errno = errno;
    }

    public ApiBaseException(String errmsg, int errno, Throwable e) {
        super(errmsg, e);
        this.errmsg = errmsg;
        this.errno = errno;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public int getErrno() {
        return errno;
    }

    public void setErrno(int errno) {
        this.errno = errno;
    }
}
