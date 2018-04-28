package com.github.franckyi.ibeeditor;

import net.minecraftforge.common.config.Config;

@Config(modid = IBEEditor.MODID)
public class IBEConfiguration {

    @Config.Comment("If true, the formatting character will always be added at the end of the textfield, " +
            "else, it's added after the cursor position, but it can cause some display problems if there's text after the cursor position.")
    public static boolean formatCharacterAtTheEnd = true;

    @Config.Comment("If true, it will pause the game in Singleplayer while you're in the editor.")
    public static boolean pauseGame = true;

}
