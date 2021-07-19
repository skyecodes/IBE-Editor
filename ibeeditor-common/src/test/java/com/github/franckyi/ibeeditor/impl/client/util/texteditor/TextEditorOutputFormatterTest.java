package com.github.franckyi.ibeeditor.impl.client.util.texteditor;

import com.github.franckyi.minecraft.api.common.text.PlainText;
import com.github.franckyi.minecraft.api.common.text.Text;
import com.google.gson.JsonArray;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static com.github.franckyi.guapi.GUAPIHelper.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TextEditorOutputFormatterTest {
    @ParameterizedTest
    @MethodSource("provideTestFormat")
    public void testFormat(PlainText rootText, String text, int firstCharacterIndex, List<Formatting> formattings, String expected) {
        TextEditorOutputFormatter formatter = new TextEditorOutputFormatter(rootText);
        formatter.format(text, firstCharacterIndex, formattings);
        assertEquals(Text.Serializer.GSON.fromJson(expected, JsonArray.class), Text.Serializer.GSON.toJsonTree(formatter.getText()));
    }

    private static Stream<Arguments> provideTestFormat() {
        return Stream.of(
                Arguments.of(
                        text(),
                        "abcd", 0,
                        Arrays.asList(
                                new StyleFormatting(0, 2, StyleType.BOLD),
                                new ColorFormatting(1, 3, "yellow")
                        ),
                        "[{\"text\":\"a\",\"bold\":true},{\"text\":\"b\",\"color\":\"yellow\",\"bold\":true},{\"text\":\"c\",\"color\":\"yellow\"},{\"text\":\"d\"}]"
                ),
                Arguments.of(
                        text(),
                        "abcd", 2,
                        Arrays.asList(
                                new StyleFormatting(0, 2, StyleType.BOLD),
                                new ColorFormatting(1, 3, "yellow")
                        ),
                        "[{\"text\":\"a\",\"color\":\"yellow\"},{\"text\":\"bcd\"}]"
                ),
                Arguments.of(
                        text().extra(text("").italic(false)),
                        "abcdef", 0,
                        Arrays.asList(
                                new StyleFormatting(1, 3, StyleType.ITALIC),
                                new StyleFormatting(2, 5, StyleType.UNDERLINED),
                                new ColorFormatting(1, 3, "red"),
                                new ColorFormatting(3, 4, "blue")
                        ),
                        "[{\"text\":\"\",\"italic\":false},{\"text\":\"a\"},{\"text\":\"b\",\"color\":\"red\",\"italic\":true},{\"text\":\"c\",\"color\":\"red\",\"italic\":true,\"underlined\":true},{\"text\":\"d\",\"color\":\"blue\",\"underlined\":true},{\"text\":\"e\",\"underlined\":true},{\"text\":\"f\"}]"
                ),
                Arguments.of(
                        text().extra(text("").italic(false)),
                        "abcdef", 3,
                        Arrays.asList(
                                new StyleFormatting(1, 3, StyleType.ITALIC),
                                new StyleFormatting(2, 5, StyleType.UNDERLINED),
                                new ColorFormatting(1, 3, "red"),
                                new ColorFormatting(3, 4, "blue")
                        ),
                        "[{\"text\":\"\",\"italic\":false},{\"text\":\"a\",\"color\":\"blue\",\"underlined\":true},{\"text\":\"b\",\"underlined\":true},{\"text\":\"cdef\"}]"
                )
        );
    }
}