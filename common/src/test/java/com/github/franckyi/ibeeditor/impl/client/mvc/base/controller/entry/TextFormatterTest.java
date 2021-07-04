package com.github.franckyi.ibeeditor.impl.client.mvc.base.controller.entry;

import com.github.franckyi.minecraft.api.common.text.Text;
import com.google.gson.JsonObject;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static com.github.franckyi.guapi.GUAPIHelper.*;
import static org.junit.jupiter.api.Assertions.*;

class TextFormatterTest {
    @ParameterizedTest
    @MethodSource("provideTestFormat")
    public void testFormat(Text rootText, String text, int firstCharacterIndex, List<TextEditorEntryController.Formatting> formattings, String expected) {
        TextEditorEntryController.TextFormatter formatter = new TextEditorEntryController.TextFormatter(rootText);
        formatter.format(text, firstCharacterIndex, formattings);
        assertEquals(Text.Serializer.GSON.fromJson(expected, JsonObject.class), Text.Serializer.GSON.toJsonTree(formatter.getText()));
    }

    private static Stream<Arguments> provideTestFormat() {
        return Stream.of(
                Arguments.of(
                        Text.createPlainText(null),
                        "abcd", 0,
                        Arrays.asList(
                                new TextEditorEntryController.StyleFormatting(0, 2, TextEditorEntryController.StyleFormatting.Type.BOLD),
                                new TextEditorEntryController.ColorFormatting(1, 3, "yellow")
                        ),
                        "{\"extra\":[{\"text\":\"a\",\"bold\":true},{\"text\":\"b\",\"color\":\"yellow\",\"bold\":true},{\"text\":\"c\",\"color\":\"yellow\"},{\"text\":\"d\"}]}"
                ),
                Arguments.of(
                        Text.createPlainText(null),
                        "abcd", 2,
                        Arrays.asList(
                                new TextEditorEntryController.StyleFormatting(0, 2, TextEditorEntryController.StyleFormatting.Type.BOLD),
                                new TextEditorEntryController.ColorFormatting(1, 3, "yellow")
                        ),
                        "{\"extra\":[{\"text\":\"a\",\"color\":\"yellow\"},{\"text\":\"bcd\"}]}"
                ),
                Arguments.of(
                        text(null).italic(false),
                        "abcdef", 0,
                        Arrays.asList(
                                new TextEditorEntryController.StyleFormatting(1, 3, TextEditorEntryController.StyleFormatting.Type.ITALIC),
                                new TextEditorEntryController.StyleFormatting(2, 5, TextEditorEntryController.StyleFormatting.Type.UNDERLINED),
                                new TextEditorEntryController.ColorFormatting(1, 3, "red"),
                                new TextEditorEntryController.ColorFormatting(3, 4, "blue")
                        ),
                        "{\"extra\":[{\"text\":\"a\"},{\"text\":\"b\",\"color\":\"red\",\"italic\":true},{\"text\":\"c\",\"color\":\"red\",\"italic\":true,\"underlined\":true},{\"text\":\"d\",\"color\":\"blue\",\"underlined\":true},{\"text\":\"e\",\"underlined\":true},{\"text\":\"f\"}],\"italic\":false}"
                ),
                Arguments.of(
                        text(null).italic(false),
                        "abcdef", 3,
                        Arrays.asList(
                                new TextEditorEntryController.StyleFormatting(1, 3, TextEditorEntryController.StyleFormatting.Type.ITALIC),
                                new TextEditorEntryController.StyleFormatting(2, 5, TextEditorEntryController.StyleFormatting.Type.UNDERLINED),
                                new TextEditorEntryController.ColorFormatting(1, 3, "red"),
                                new TextEditorEntryController.ColorFormatting(3, 4, "blue")
                        ),
                        "{\"extra\":[{\"text\":\"a\",\"color\":\"blue\",\"underlined\":true},{\"text\":\"b\",\"underlined\":true},{\"text\":\"cdef\"}],\"italic\":false}"
                )
        );
    }
}