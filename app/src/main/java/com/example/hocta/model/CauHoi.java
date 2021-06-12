package com.example.hocta.model;

import java.util.List;

public class CauHoi {
    private  int num;
    private String content;
    private List<TraLoi> list;

    public CauHoi() {
    }

    public CauHoi(int num, String content, List<TraLoi> list) {
        this.num = num;
        this.content = content;
        this.list = list;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<TraLoi> getList() {
        return list;
    }

    public void setList(List<TraLoi> list) {
        this.list = list;
    }
}
