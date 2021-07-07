package com.github.franckyi.minecraft.api.common.text.builder;

import com.github.franckyi.minecraft.api.common.text.Text;
import com.github.franckyi.minecraft.impl.common.text.AbstractText;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;

import static com.github.franckyi.guapi.GUAPIHelper.*;

public interface TextBuilder<T extends Text> {
    @SuppressWarnings("unchecked")
    default T with(Consumer<T> with) {
        T val = (T) this;
        with.accept(val);
        return val;
    }

    default T extra(Text... extra) {
        return extra(Lists.newArrayList(extra));
    }

    default T extra(Collection<? extends Text> extra) {
        return extra(new ArrayList<>(extra));
    }

    default T extra(List<Text> extra) {
        return with(t -> t.setExtra(extra));
    }

    default T color(String color) {
        return with(t -> t.setColor(color));
    }

    default T black() {
        return color(BLACK);
    }

    default T darkBlue() {
        return color(DARK_BLUE);
    }

    default T darkGreen() {
        return color(DARK_GREEN);
    }

    default T darkAqua() {
        return color(DARK_AQUA);
    }

    default T darkRed() {
        return color(DARK_RED);
    }

    default T darkPurple() {
        return color(DARK_PURPLE);
    }

    default T gold() {
        return color(GOLD);
    }

    default T gray() {
        return color(GRAY);
    }

    default T darkGray() {
        return color(DARK_GRAY);
    }

    default T blue() {
        return color(BLUE);
    }

    default T green() {
        return color(GREEN);
    }

    default T aqua() {
        return color(AQUA);
    }

    default T red() {
        return color(RED);
    }

    default T lightPurple() {
        return color(LIGHT_PURPLE);
    }

    default T yellow() {
        return color(YELLOW);
    }

    default T white() {
        return color(WHITE);
    }

    default T bold(boolean bold) {
        return with(t -> t.setBold(bold));
    }

    default T bold() {
        return bold(true);
    }

    default T italic(boolean italic) {
        return with(t -> t.setItalic(italic));
    }

    default T italic() {
        return italic(true);
    }

    default T underlined(boolean underlined) {
        return with(t -> t.setUnderlined(underlined));
    }

    default T underlined() {
        return underlined(true);
    }

    default T strikethrough(boolean strikethrough) {
        return with(t -> t.setStrikethrough(strikethrough));
    }

    default T strikethrough() {
        return strikethrough(true);
    }

    default T obfuscated(boolean obfuscated) {
        return with(t -> t.setObfuscated(obfuscated));
    }

    default T obfuscated() {
        return obfuscated(true);
    }

    default T click(AbstractText.Event clickEvent) {
        return with(t -> t.setClickEvent(clickEvent));
    }

    default T hover(AbstractText.Event hoverEvent) {
        return with(t -> t.setHoverEvent(hoverEvent));
    }
}
