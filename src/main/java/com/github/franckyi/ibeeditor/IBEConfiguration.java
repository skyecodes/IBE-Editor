package com.github.franckyi.ibeeditor;

import net.minecraftforge.common.config.Config;

@Config(modid = IBEEditor.MODID)
public class IBEConfiguration {

    @Config.Comment("If true, the formatting character will always be added at the end of the textfield, " +
            "else, it's added after the cursor position, but it can cause some display problems if there's text after the cursor position.")
    public static boolean formatCharacterAtTheEnd = true;

    @Config.Comment("Represents the number of editable lore lines shown in the editor.")
    @Config.RangeInt(min = 1, max = 100)
    public static int loreLinesCount = 3;

    @Config.Comment("If true, will pause the game in Singleplayer while you're in the editor.")
    public static boolean pauseGame = true;

    @Config.Comment("If true, will show the testing editor. Don't use this.")
    public static boolean test = false;
}
