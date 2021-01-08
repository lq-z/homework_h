package com.homeworkh.usermanagementserver.entity;

import java.io.Serializable;

/**
 * @author lq
 * @date 2021/1/6 14:31
 */
public class AlarmDevice implements Serializable {
    private String number;
    private String isOpen;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getIsOpen() {
        return isOpen;
    }

    public void setIsOpen(String isOpen) {
        this.isOpen = isOpen;
    }
}
