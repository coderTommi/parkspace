package com.parkspace;
/**
 * @Title: ChatObject.java
 * @Package com.parkspace
 * <p>Description:</p>
 * @author sunld
 * @version V1.0.0 
 * <p>CreateDate:2017年10月16日 下午2:27:08</p>
*/

public class ChatObject {
	private String userName;

    private String message;



    public ChatObject() {

    }



    public ChatObject(String userName, String message) {

        super();

        this.userName = userName;

        this.message = message;

    }



    public String getUserName() {

        return userName;

    }

    public void setUserName(String userName) {

        this.userName = userName;

    }



    public String getMessage() {

        return message;

    }

    public void setMessage(String message) {

        this.message = message;

    }
}
