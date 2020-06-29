package com.github.franckyi.ibeeditor.client.gui.editor.base;

import com.github.franckyi.guapi.Scene;
import com.github.franckyi.guapi.group.HBox;
import com.github.franckyi.guapi.group.VBox;
import com.github.franckyi.guapi.math.Insets;
import com.github.franckyi.guapi.math.Pos;
import com.github.franckyi.guapi.node.Button;
import com.github.franckyi.guapi.node.Label;
import com.github.franckyi.guapi.node.ListExtended;
import com.github.franckyi.guapi.scene.IBackground;
import com.github.franckyi.ibeeditor.common.IBEConfiguration;
import net.minecraft.util.text.TextFormatting;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public abstract class AbstractEditor extends Scene {

    protected final VBox content;
    protected final HBox header;
    protected final Label headerLabel;
    protected final HBox body;
    protected final ListExtended<CategoryEntry> categories;
    protected final List<AbstractCategory> propertiesList;
    protected final HBox footer;
    protected final Button doneButton;
    protected final Button cancelButton;
    protected final Button applyButton;
    protected int currentIndex;

    public AbstractEditor(String headerText) {
        super(new VBox());
        content = (VBox) this.getContent();
        header = new HBox(5);
        header.setPrefHeight(30);
        header.setAlignment(Pos.CENTER);
        headerLabel = new Label(TextFormatting.BOLD + headerText);
        body = new HBox();
        categories = new ListExtended<>(20);
        categories.setOffset(new Insets(0, 10, 10, 10));
        propertiesList = new ArrayList<>();
        footer = new HBox(20);
        footer.setPrefHeight(20);
        doneButton = new Button(TextFormatting.GREEN + "Done");
        doneButton.setPrefWidth(80);
        doneButton.getOnMouseClickedListeners().add(event -> {
            this.apply();
            this.close();
        });
        cancelButton = new Button(TextFormatting.RED + "Close");
        cancelButton.setPrefWidth(80);
        cancelButton.getOnMouseClickedListeners().add(event -> this.close());
        applyButton = new Button(TextFormatting.GREEN + "Apply");
        applyButton.setPrefWidth(80);
        applyButton.getOnMouseClickedListeners().add(event -> this.apply());
        header.getChildren().add(headerLabel);
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
        this.setGuiPauseGame(IBEConfiguration.CLIENT.doesGuiPauseGame.get());
    }

    protected void apply() {
        this.propertiesList.forEach(AbstractCategory::apply);
    }

    protected void scaleChildrenSize() {
        header.setPrefWidth(content.getWidth());
        footer.setPrefWidth(content.getWidth());
        categories.setPrefSize(content.getWidth() / 3, content.getHeight() - 60);
        categories.getView().setHeight(content.getHeight());
        if (propertiesList.isEmpty()) {
            categories.setVisible(false);
        } else {
            this.scalePropertiesSize(propertiesList.get(currentIndex));
        }
    }

    protected void scalePropertiesSize(AbstractCategory category) {
        category.setPrefSize(2 * content.getWidth() / 3, content.getHeight() - 60);
        category.getView().setHeight(content.getHeight());
        category.getChildren().forEach(p -> p.updateSize(propertiesList.get(currentIndex).getWidth()));
    }

    protected AbstractCategory addCategory(String name, AbstractCategory category) {
        this.categories.getChildren().add(new CategoryEntry(name));
        this.propertiesList.add(category);
        return category;
    }

    public void onCategoryClicked(CategoryEntry category) {
        int categoryIndex = categories.getChildren().indexOf(category);
        if (currentIndex != categoryIndex) {
            Label oldCategoryLabel = categories.getChildren().get(currentIndex).getNode();
            oldCategoryLabel.setText("  " + oldCategoryLabel.getText().substring(6));
            currentIndex = categoryIndex;
            AbstractCategory propertyList = propertiesList.get(categoryIndex);
            body.getChildren().set(1, propertyList);
            this.scalePropertiesSize(propertyList);
            Label newCategoryLabel = categories.getChildren().get(currentIndex).getNode();
            newCategoryLabel.setText(" " + TextFormatting.YELLOW + " " + TextFormatting.BOLD + newCategoryLabel.getText().substring(2));
        }
    }

    @Override
    public void show() {
        if (!propertiesList.isEmpty()) {
            currentIndex = 0;
            body.getChildren().add(propertiesList.get(0));
            Label categoryLabel = categories.getChildren().get(0).getNode();
            categoryLabel.setText(" " + TextFormatting.YELLOW + " " + TextFormatting.BOLD + categoryLabel.getText().substring(2));
        }
        super.show();
    }

    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {
        body.render(mouseX, mouseY, partialTicks);
        footer.render(mouseX, mouseY, partialTicks);
        header.render(mouseX, mouseY, partialTicks);
        if (propertiesList.isEmpty()) {
            this.getScreen().drawCenteredString(mc.fontRenderer, "No parameters available !", this.getScreen().width / 2, body.getY() + body.getHeight() / 2 - 4, TextFormatting.DARK_RED.getColor());
        }
    }

    protected <X> void applyConfigurations(List<? extends AbstractEditorConfiguration<X, Object>> configurations, X x) {
        configurations.forEach(configuration -> {
            if (configuration.getCondition().test(x)) {
                this.addCategory(configuration.getName(), configuration.getCategoryBuilder().apply(configuration.getCaster().apply(x)));
            }
        });
    }

    protected static abstract class AbstractEditorConfiguration<X, T> {

        private final Predicate<X> condition;
        private final Function<X, T> caster;
        private final String name;
        private final Function<T, AbstractCategory> categoryBuilder;

        protected AbstractEditorConfiguration(Predicate<X> condition, Function<X, T> caster, String name, Function<T, AbstractCategory> categoryBuilder) {
            this.condition = condition;
            this.caster = caster;
            this.name = name;
            this.categoryBuilder = categoryBuilder;
        }

        public Predicate<X> getCondition() {
            return condition;
        }

        public Function<X, T> getCaster() {
            return caster;
        }

        public String getName() {
            return name;
        }

        public Function<T, AbstractCategory> getCategoryBuilder() {
            return categoryBuilder;
        }
    }

    public class CategoryEntry extends ListExtended.NodeEntry<Label> {

        public CategoryEntry(String name) {
            super(new Label("  " + name));
            this.getNode().setCentered(true);
            this.getOnMouseClickedListeners().add(e -> AbstractEditor.this.onCategoryClicked(this));
        }
    }
}
