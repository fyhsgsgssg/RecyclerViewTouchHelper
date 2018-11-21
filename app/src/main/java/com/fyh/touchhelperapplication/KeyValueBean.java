package com.fyh.touchhelperapplication;

/**
 * @author by nate_fu on 2018/11/20.
 * @version vision 1.0
 * @Email: fuyonghui@zjhcsoft.com
 */
public class KeyValueBean {
    private String key;
    private String value;
    private Boolean isChecked=false;

    public KeyValueBean(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Boolean getChecked() {
        return isChecked;
    }

    public void setChecked(Boolean checked) {
        isChecked = checked;
    }
}
