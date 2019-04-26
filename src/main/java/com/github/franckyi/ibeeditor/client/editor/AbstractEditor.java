package com.github.franckyi.ibeeditor.client.editor;

import com.github.franckyi.guapi.Scene;
import com.github.franckyi.guapi.group.HBox;
import com.github.franckyi.guapi.group.VBox;
import com.github.franckyi.guapi.math.Insets;
import com.github.franckyi.guapi.math.Pos;
import com.github.franckyi.guapi.node.Button;
import com.github.franckyi.guapi.node.Label;
import com.github.franckyi.guapi.node.ListExtended;
import com.github.franckyi.guapi.scene.IBackground;
import com.github.franckyi.ibeeditor.IBEEditorConfig;
import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.util.text.TextFormatting;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractEditor extends Scene {

    protected final VBox content;
    protected final Label header;
    protected final HBox body;
    protected final ListExtended<CategoryEntry> categories;
    protected final List<Category> propertiesList;
    protected final HBox footer;
    protected final Button doneButton;
    protected final Button cancelButton;
    protected final Button applyButton;
    protected int currentIndex;

    public AbstractEditor(String headerText) {
        super(new VBox());
        content = (VBox) this.getContent();
        header = new Label(headerText);
        header.setPrefHeight(30);
        header.setCentered(true);
        body = new HBox();
        categories = new ListExtended<>(20);
        categories.setOffset(new Insets(0, 10, 10, 10));
        propertiesList = new ArrayList<>();
        footer = new HBox(20);
        footer.setPrefHeight(20);
        doneButton = new Button(ChatFormatting.GREEN + "Done");
        doneButton.setPrefWidth(80);
        doneButton.getOnMouseClickedListeners().add(event -> {
            this.apply();
            this.close();
        });
        cancelButton = new Button(ChatFormatting.RED + "Close");
        cancelButton.setPrefWidth(80);
        cancelButton.getOnMouseClickedListeners().add(event -> this.close());
        applyButton = new Button(ChatFormatting.GREEN + "Apply");
        applyButton.setPrefWidth(80);
        applyButton.getOnMouseClickedListeners().add(event -> this.apply());
        body.getChildren().add(categories);
        footer.getChildren().add(doneButton);
        footer.getChildren().add(cancelButton);
        footer.getChildren().add(applyButton);
        footer.setAlignment(Pos.CENTER);
        content.getChildren().add(header);
        content.getChildren().add(body);
        content.getChildren().add(footer);
        this.setContentFullScreen();
        this.setBackground(IBackground.texturedBackground(1));
        this.getOnInitGuiListeners().add(e -> {
            this.setContentFullScreen();
            this.scaleChildrenSize();
        });
        this.setGuiPauseGame(IBEEditorConfig.CLIENT.doesGuiPauseGame.get());
    }

    protected void apply() {
        this.propertiesList.forEach(Category::apply);
    }

    protected void scaleChildrenSize() {
        header.setPrefWidth(content.getWidth());
        footer.setPrefWidth(content.getWidth());
        categories.setPrefSize(content.getWidth() / 3, content.getHeight() - 60);
        categories.getView().height = content.getHeight();
        if (propertiesList.isEmpty()) {
            categories.setVisible(false);
        } else {
            this.scalePropertiesSize(propertiesList.get(currentIndex));
        }
    }

    protected void scalePropertiesSize(Category category) {
        category.setPrefSize(2 * content.getWidth() / 3, content.getHeight() - 60);
        category.getView().height = content.getHeight();
        category.getChildren().forEach(p -> p.updateSize(propertiesList.get(currentIndex).getWidth()));
    }

    protected Category addCategory(String name) {
        return this.addCategory(name, new Category());
    }

    protected Category addCategory(String name, Category category) {
        this.categories.getChildren().add(new CategoryEntry(name));
        this.propertiesList.add(category);
        return category;
    }

    public void onCategoryClicked(CategoryEntry category) {
        int categoryIndex = categories.getChildren().indexOf(category);
        if (currentIndex != categoryIndex) {
            Label oldCategoryLabel = categories.getChildren().get(currentIndex).getNode();
            oldCategoryLabel.setText(oldCategoryLabel.getText().substring(2, oldCategoryLabel.getText().length() - 2));
            currentIndex = categoryIndex;
            Category propertyList = propertiesList.get(categoryIndex);
            body.getChildren().set(1, propertyList);
            this.scalePropertiesSize(propertyList);
            Label newCategoryLabel = categories.getChildren().get(currentIndex).getNode();
            newCategoryLabel.setText("> " + newCategoryLabel.getText() + " <");
        }
    }

    @Override
    public void show() {
        if (!propertiesList.isEmpty()) {
            currentIndex = 0;
            body.getChildren().add(propertiesList.get(0));
            Label categoryLabel = categories.getChildren().get(0).getNode();
            categoryLabel.setText("> " + categoryLabel.getText() + " <");
        }
        super.show();
    }

    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {
        body.render(mouseX, mouseY, partialTicks);
        header.render(mouseX, mouseY, partialTicks);
        footer.render(mouseX, mouseY, partialTicks);
        if (propertiesList.isEmpty()) {
            this.getScreen().drawCenteredString(mc.fontRenderer, "No parameters available !", this.getScreen().width / 2, body.getY() + body.getHeight() / 2 - 4, TextFormatting.DARK_RED.getColor());
        }
    }

    public class CategoryEntry extends ListExtended.NodeEntry<Label> {

        public CategoryEntry(String name) {
            super(new Label(name));
            this.getNode().setCentered(true);
            this.getOnMouseClickedListeners().add(e -> AbstractEditor.this.onCategoryClicked(this));
        }
    }
}
