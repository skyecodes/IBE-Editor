package com.github.franckyi.ibeeditor.client;

import com.github.franckyi.guapi.Scene;
import com.github.franckyi.guapi.group.HBox;
import com.github.franckyi.guapi.group.VBox;
import com.github.franckyi.guapi.math.Insets;
import com.github.franckyi.guapi.math.Pos;
import com.github.franckyi.guapi.node.Button;
import com.github.franckyi.guapi.node.Label;
import com.github.franckyi.guapi.node.ListExtended;
import com.github.franckyi.guapi.scene.Background;
import com.github.franckyi.ibeeditor.config.IBEEditorConfig;
import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.event.GuiScreenEvent;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractEditor extends Scene {

    protected final VBox content;
    protected final Label header;
    protected final HBox editorBox;
    protected final ListExtended<CategoryEntry> categories;
    protected final List<Category> propertiesList;
    protected final HBox buttonBox;
    protected final Button doneButton;
    protected final Button cancelButton;
    protected final Button applyButton;
    protected int currentIndex;

    public AbstractEditor(String headerText) {
        super(new VBox());
        mc.mouseHelper.ungrabMouse();
        content = (VBox) this.getContent();
        header = new Label(headerText);
        header.setPrefHeight(30);
        header.setCentered(true);
        editorBox = new HBox();
        categories = new ListExtended<>(20);
        categories.setOffset(new Insets(0, 10, 10, 10));
        propertiesList = new ArrayList<>();
        buttonBox = new HBox(20);
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
        editorBox.getChildren().add(categories);
        buttonBox.getChildren().add(doneButton);
        buttonBox.getChildren().add(cancelButton);
        buttonBox.getChildren().add(applyButton);
        buttonBox.setAlignment(Pos.CENTER);
        content.getChildren().add(header);
        content.getChildren().add(editorBox);
        content.getChildren().add(buttonBox);
        this.setContentFullScreen();
        this.setBackground(Background.texturedBackground(1));
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
        header.setPrefWidth(this.getContent().getWidth());
        buttonBox.setPrefWidth(this.getContent().getWidth());
        categories.setPrefSize(this.getContent().getWidth() / 3, this.getContent().getHeight() - 60);
        categories.getView().height = this.getContent().getHeight();
        if (propertiesList.isEmpty()) {
            categories.setVisible(false);
        } else {
            this.scalePropertiesSize(propertiesList.get(currentIndex));
        }
    }

    protected void scalePropertiesSize(Category category) {
        category.setPrefSize(2 * this.getContent().getWidth() / 3, this.getContent().getHeight() - 60);
        category.getView().height = this.getContent().getHeight();
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
            editorBox.getChildren().set(1, propertyList);
            this.scalePropertiesSize(propertyList);
            Label newCategoryLabel = categories.getChildren().get(currentIndex).getNode();
            newCategoryLabel.setText("> " + newCategoryLabel.getText() + " <");
        }
    }

    @Override
    public void show() {
        if (!propertiesList.isEmpty()) {
            currentIndex = 0;
            editorBox.getChildren().add(propertiesList.get(0));
            Label categoryLabel = categories.getChildren().get(0).getNode();
            categoryLabel.setText("> " + categoryLabel.getText() + " <");
        }
        super.show();
    }

    @Override
    protected void onDrawScreen(GuiScreenEvent.DrawScreenEvent event) {
        super.onDrawScreen(event);
        if (propertiesList.isEmpty()) {
            this.getScreen().drawCenteredString(mc.fontRenderer, "No parameters available !", this.getScreen().width / 2, editorBox.getY() + editorBox.getHeight() / 2 - 4, TextFormatting.DARK_RED.getColor());
        }
    }

    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {
        editorBox.render(mouseX, mouseY, partialTicks);
        header.render(mouseX, mouseY, partialTicks);
        buttonBox.render(mouseX, mouseY, partialTicks);
    }

    public class CategoryEntry extends ListExtended.NodeEntry<Label> {

        public CategoryEntry(String name) {
            super(new Label(name));
            this.getNode().setCentered(true);
            this.getOnMouseClickedListeners().add(e -> AbstractEditor.this.onCategoryClicked(this));
        }
    }
}
