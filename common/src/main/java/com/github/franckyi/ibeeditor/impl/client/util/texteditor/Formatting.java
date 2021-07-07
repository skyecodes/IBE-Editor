package com.github.franckyi.ibeeditor.impl.client.util.texteditor;

import com.github.franckyi.minecraft.api.common.text.Text;

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

    public abstract void apply(Text text);
}
