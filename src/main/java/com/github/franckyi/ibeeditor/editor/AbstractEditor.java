package com.github.franckyi.ibeeditor.editor;

import com.github.franckyi.guapi.Scene;
import com.github.franckyi.guapi.group.HBox;
import com.github.franckyi.guapi.group.VBox;
import com.github.franckyi.guapi.math.Insets;
import com.github.franckyi.guapi.math.Pos;
import com.github.franckyi.guapi.node.Button;
import com.github.franckyi.guapi.node.Label;
import com.github.franckyi.guapi.node.ListExtended;
import com.github.franckyi.guapi.scene.Background;
import com.mojang.realmsclient.gui.ChatFormatting;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractEditor extends Scene {

    protected final VBox content;
    protected final HBox editorBox;
    protected final ListExtended<CategoryEntry> categories;
    protected final List<PropertyList> propertiesList;
    protected final HBox buttonBox;
    protected final Button applyButton;
    protected final Button cancelButton;
    protected int currentIndex;

    public AbstractEditor() {
        super(new VBox());
        content = (VBox) this.getContent();
        editorBox = new HBox();
        categories = new ListExtended<>(15);
        categories.setOffset(new Insets(10));
        propertiesList = new ArrayList<>();
        buttonBox = new HBox(20);
        applyButton = new Button(ChatFormatting.GREEN + "Apply");
        applyButton.setPrefWidth(100);
        applyButton.getOnMouseClickedListeners().add(event -> this.apply());
        cancelButton = new Button(ChatFormatting.RED + "Cancel");
        cancelButton.setPrefWidth(100);
        cancelButton.getOnMouseClickedListeners().add(event -> this.close());
        editorBox.getChildren().add(categories);
        buttonBox.getChildren().add(applyButton);
        buttonBox.getChildren().add(cancelButton);
        buttonBox.setAlignment(Pos.TOP);
        content.getChildren().add(editorBox);
        content.getChildren().add(buttonBox);
        this.setContentFullScreen();
        this.setBackground(Background.texturedBackground(1));
        this.getOnInitGuiListeners().add(e -> {
            this.setContentFullScreen();
            this.scaleChildrenSize();
        });
    }

    protected void apply() {
        this.propertiesList.forEach(PropertyList::apply);
        this.close();
    }

    protected void scaleChildrenSize() {
        buttonBox.setPrefWidth(this.getContent().getWidth());
        categories.setPrefSize(this.getContent().getWidth() / 3, this.getContent().getHeight() - 30);
        this.scalePropertiesSize(propertiesList.get(currentIndex));
    }

    protected void scalePropertiesSize(ListExtended properties) {
        properties.setPrefSize(2 * this.getContent().getWidth() / 3, this.getContent().getHeight() - 30);
    }

    protected ListExtended<AbstractProperty> addCategory(String name) {
        return this.addCategory(name, new PropertyList());
    }

    protected ListExtended<AbstractProperty> addCategory(String name, PropertyList propertyList) {
        this.categories.getChildren().add(new CategoryEntry(this, name));
        propertyList.setOffset(new Insets(10));
        this.propertiesList.add(propertyList);
        return propertyList;
    }

    public void onCategoryClicked(CategoryEntry category) {
        int categoryIndex = categories.getChildren().indexOf(category);
        if (currentIndex != categoryIndex) {
            Label oldCategoryLabel = categories.getChildren().get(currentIndex).getNode();
            oldCategoryLabel.setText(oldCategoryLabel.getText().substring(2, oldCategoryLabel.getText().length() - 2));
            currentIndex = categoryIndex;
            ListExtended<AbstractProperty> properties = propertiesList.get(categoryIndex);
            editorBox.getChildren().set(1, properties);
            this.scalePropertiesSize(properties);
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

}
