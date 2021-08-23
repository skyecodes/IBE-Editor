package com.github.franckyi.ibeeditor.base.client.util.texteditor;

import com.github.franckyi.gameadapter.api.common.text.IText;

import java.util.Objects;

public abstract class Formatting {
    private int start, end;

    public Formatting(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public abstract void apply(IText text);

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Formatting that = (Formatting) o;
        return start == that.start && end == that.end;
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end);
    }
}
