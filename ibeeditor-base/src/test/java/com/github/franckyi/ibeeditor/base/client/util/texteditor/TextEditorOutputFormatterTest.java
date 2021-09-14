package com.github.franckyi.ibeeditor.base.client.util.texteditor;

import com.github.franckyi.gameadapter.api.common.text.IPlainText;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static com.github.franckyi.guapi.api.GuapiHelper.text;

class TextEditorOutputFormatterTest {
    @ParameterizedTest
    @MethodSource("provideTestFormat")
    @Disabled
    void testFormat(IPlainText rootText, String text, int firstCharacterIndex, List<Formatting> formattings, String expected) {
        /*TextEditorOutputFormatter formatter = new TextEditorOutputFormatter(rootText);
        formatter.format(text, firstCharacterIndex, formattings);
        assertEquals(TextHandler.getSerializer().fromJson(expected, JsonArray.class), TextHandler.getSerializer().toJsonTree(formatter.getText()));*/
    }

    static Stream<Arguments> provideTestFormat() {
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