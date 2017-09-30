package com.parkspace.common.exception;

import java.io.Serializable;

public class PackspaceServiceException extends RuntimeException implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*
     * 异常代码
     */
    /** The msg code. */
    private String msgCode = null;

    /** The msg. */
    private String msg = null;

    /**
     * Instantiates a new cloud business exception.
     * @param msgCode the msg code
     * @param msg the msg
     * @param cause the cause
     */
    public PackspaceServiceException(String msgCode, String msg, Throwable cause) {
        super(msg, cause);
        this.msgCode = msgCode;
        this.msg = msg;
    }

    /**
     * Instantiates a new cloud business exception.
     * @param msgCode the msg code
     * @param cause the cause
     */
    public PackspaceServiceException(String msgCode, Throwable cause) {

        super(cause.getMessage(), cause);
        this.msgCode = msgCode;
        this.msg = cause.getMessage();
    }

    /**
     * Instantiates a new cloud business exception.
     * @param msgCode the msg code
     * @param msg the msg
     */
    public PackspaceServiceException(String msgCode, String msg) {
        super(msg);
        this.msgCode = msgCode;
        this.msg = msg;
    }

    /**
     * Instantiates a new cloud business exception.
     * @param msgCode the msg code
     */
    public PackspaceServiceException(String msgCode) {
        super(msgCode);
        this.msgCode = msgCode;
    }
    /**
     * .
     * @return .
     * */
    public String getMessage() {
        return msg;
    }

    /**
     * Gets the message code.
     * @return the message code
     */
    public String getMessageCode() {
        return msgCode;
    }

}
