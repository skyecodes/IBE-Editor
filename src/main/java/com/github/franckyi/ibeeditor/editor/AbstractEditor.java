package com.github.franckyi.ibeeditor.editor;

import com.github.franckyi.guapi.Scene;
import com.github.franckyi.guapi.scene.Background;
import com.github.franckyi.ibeeditor.editor.gui.EditorNode;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@OnlyIn(Dist.CLIENT)
public abstract class AbstractEditor {

    private final List<EditorCategory> categories;
    private Scene scene;

    public AbstractEditor() {
        categories = new ArrayList<>();
    }

    public void apply() {
        categories.stream().flatMap(category -> category.getProperties().stream()).forEach(AbstractProperty::apply);
        this.close();
    }

    public List<EditorCategory> getCategories() {
        return categories;
    }

    protected EditorCategory category(String name, AbstractProperty... properties) {
        EditorCategory category = new EditorCategory(name);
        category.getProperties().addAll(Arrays.asList(properties));
        return category;
    }

    protected void build(EditorCategory... categories) {
        this.categories.addAll(Arrays.asList(categories));
    }

    public void open() {
        scene = new Scene();
        EditorNode node = new EditorNode(this);
        scene.setContent(node);
        scene.setContentFullScreen();
        scene.getOnInitGuiListeners().add(e -> {
            scene.setContentFullScreen();
            node.scaleChildrenSize();
        });
        scene.setBackground(Background.texturedBackground(1));
        scene.show();
    }

    public void close() {
        scene.close();
    }
}
