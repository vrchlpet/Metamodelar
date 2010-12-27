

package org.cvut.MMN.model.exceptions;

/**
 *
 * @author Vrchli
 */
public class MMNException extends Exception{

    private String msg;

    public MMNException(String msg) {
        super(msg);
        this.msg = msg;
    }

    /**
     * @return the msg
     */
    public String getMsg() {
        return msg;
    }

    /**
     * @param msg the msg to set
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

}
