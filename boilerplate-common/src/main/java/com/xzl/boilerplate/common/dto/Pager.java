package com.xzl.boilerplate.common.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;

@Data
public class Pager<DATA> {
    int index;
    int size;
    int total;
    List<DATA> list;

    public Pager(int index, int size) {
        this.index = index;
        this.size = size;
    }

    @JsonIgnore
    public int getStart() {
        return index * size;
    }

    @JsonIgnore
    public int getEnd() {
        return (index + 1) * size;
    }

    public int setTotal(int count) {
        return (count - 1) / size + 1;
    }
}
