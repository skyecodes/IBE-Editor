package com.github.franckyi.ibeeditor;

import net.minecraftforge.common.config.Config;

@Config(modid = IBEEditor.MODID)
public class IBEConfiguration {

    @Config.Name("Format character at the end")
    @Config.Comment("If true, the formatting character will always be added at the end of the textfield.")
    public static boolean formatCharacterAtTheEnd = true;

    @Config.Name("Lore lines count")
    @Config.Comment("Represents the number of editable lore lines shown in the editor.")
    @Config.RangeInt(min = 1, max = 100)
    public static int loreLinesCount = 3;

    @Config.Name("Testing mode")
    @Config.Comment("If true, will show the testing editor.")
    public static boolean test = false;
}
