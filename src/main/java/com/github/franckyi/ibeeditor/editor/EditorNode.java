package com.github.franckyi.ibeeditor.editor;

import com.github.franckyi.guapi.Node;
import com.github.franckyi.guapi.group.HBox;
import com.github.franckyi.guapi.group.VBox;
import com.github.franckyi.guapi.math.Insets;
import com.github.franckyi.guapi.math.Pos;
import com.github.franckyi.guapi.node.Button;
import com.github.franckyi.guapi.node.Label;
import com.github.franckyi.guapi.node.ListExtended;
import com.mojang.realmsclient.gui.ChatFormatting;

import java.util.ArrayList;
import java.util.List;

public class EditorNode extends VBox {

    private final HBox editorBox;
    private final ListExtended<ListExtended.NodeEntry<Label>> categories;
    private final List<ListExtended<ListExtended.NodeEntry<Node>>> propertiesList;
    private final HBox buttonBox;
    private final Button applyButton;
    private final Button cancelButton;
    private AbstractEditor editor;
    private int currentIndex;

    public EditorNode(AbstractEditor editor) {
        this.editor = editor;
        editorBox = new HBox();
        categories = new ListExtended<>(20);
        categories.setOffset(new Insets(10));
        propertiesList = new ArrayList<>();
        buttonBox = new HBox(20);
        applyButton = new Button(ChatFormatting.GREEN + "Apply");
        applyButton.setPrefWidth(100);
        applyButton.getOnMouseClickedListeners().add(event -> editor.apply());
        cancelButton = new Button(ChatFormatting.RED + "Cancel");
        cancelButton.setPrefWidth(100);
        cancelButton.getOnMouseClickedListeners().add(event -> editor.close());
        editor.getCategories().forEach(category -> {
            Label label = new Label(category.getName());
            label.setCentered(true);
            ListExtended.NodeEntry<Label> categoryEntry = new ListExtended.NodeEntry<>(label);
            categories.getChildren().add(categoryEntry);
            categoryEntry.getOnMouseClickedListeners().add(e -> this.onCategoryClicked(categories.getChildren().indexOf(categoryEntry)));
            ListExtended<ListExtended.NodeEntry<Node>> properties = new ListExtended<>(25);
            properties.setOffset(new Insets(10));
            category.getProperties().forEach(properties.getChildren()::add);
            propertiesList.add(properties);
        });
        editorBox.getChildren().add(categories);
        buttonBox.getChildren().add(applyButton);
        buttonBox.getChildren().add(cancelButton);
        buttonBox.setAlignment(Pos.TOP);
        this.getChildren().add(editorBox);
        this.getChildren().add(buttonBox);
        if (!editor.getCategories().isEmpty()) {
            currentIndex = 0;
            editorBox.getChildren().add(propertiesList.get(0));
            Label categoryLabel = categories.getChildren().get(0).getNode();
            categoryLabel.setText("> " + categoryLabel.getText() + " <");
        }
    }

    public ListExtended<ListExtended.NodeEntry<Label>> getCategories() {
        return categories;
    }

    public List<ListExtended<ListExtended.NodeEntry<Node>>> getPropertiesList() {
        return propertiesList;
    }

    private void onCategoryClicked(int categoryIndex) {
        if (currentIndex != categoryIndex) {
            Label oldCategoryLabel = categories.getChildren().get(currentIndex).getNode();
            oldCategoryLabel.setText(oldCategoryLabel.getText().substring(2, oldCategoryLabel.getText().length() - 2));
            currentIndex = categoryIndex;
            ListExtended<ListExtended.NodeEntry<Node>> properties = propertiesList.get(categoryIndex);
            editorBox.getChildren().set(1, properties);
            this.scalePropertiesSize(properties);
            Label newCategoryLabel = categories.getChildren().get(currentIndex).getNode();
            newCategoryLabel.setText("> " + newCategoryLabel.getText() + " <");
        }
    }

    public void scaleChildrenSize() {
        buttonBox.setPrefWidth(this.getWidth());
        categories.setPrefSize(this.getWidth() / 3, this.getHeight() - 30);
        this.scalePropertiesSize(propertiesList.get(currentIndex));
    }

    protected void scalePropertiesSize(ListExtended properties) {
        properties.setPrefSize(2 * this.getWidth() / 3, this.getHeight() - 30);
    }
}
