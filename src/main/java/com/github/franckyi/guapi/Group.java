package com.github.franckyi.guapi;

import com.github.franckyi.guapi.gui.AbstractGuiView;
import com.github.franckyi.guapi.math.Pos;
import com.github.franckyi.ibeeditor.IBEEditorMod;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

public abstract class Group extends Node<Group.GuiGroupView> implements Parent {

    private final List<Node> children;
    private Pos alignment;

    public Group(GuiGroupView view) {
        super(view);
        children = new GroupChildren();
        alignment = Pos.TOP_LEFT;
    }

    public Group() {
        this(new GuiGroupView());
    }

    @Override
    public List<Node> getChildren() {
        return children;
    }

    public Pos getAlignment() {
        return alignment;
    }

    public void setAlignment(Pos alignment) {
        if (this.getAlignment() != alignment) {
            this.alignment = alignment;
            this.updateChildrenPos();
        }
    }

    @Override
    public void setX(int x) {
        super.setX(x);
        this.updateChildrenPos();
    }

    @Override
    public void setY(int y) {
        super.setY(y);
        this.updateChildrenPos();
    }

    @Override
    public void setPosition(int x, int y) {
        super.setX(x);
        super.setY(y);
        this.updateChildrenPos();
    }

    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {
        super.render(mouseX, mouseY, partialTicks);
        this.getChildren().stream().filter(Node::isVisible).forEach(node -> node.render(mouseX, mouseY, partialTicks));
    }

    static class GuiGroupView extends AbstractGuiView {

        @Override
        public void render(int mouseX, int mouseY, float partialTicks) {
            /*drawRect(this.getX() - 1, this.getY() - 1, this.getX() + this.getWidth() + 1, this.getY() + this.getHeight() + 1, 0xffa0a0a0);
            drawRect(this.getX(), this.getY(), this.getX() + this.getWidth(), this.getY() + this.getHeight(), 0xff000000);*/
        }
    }

    private class GroupChildren extends ArrayList<Node> {

        @Override
        public Node set(int index, Node element) throws IllegalArgumentException {
            checkNode(element);
            Node old = super.set(index, element);
            if (element != null) {
                element.setParent(Group.this);
            }
            if (old != null) {
                old.setParent(null);
            }
            return old;
        }

        @Override
        public boolean add(Node node) throws IllegalArgumentException {
            checkNode(node);
            super.add(node);
            if (node != null) {
                node.setParent(Group.this);
            }
            return true;
        }

        @Override
        public void add(int index, Node element) throws IllegalArgumentException {
            checkNode(element);
            super.add(index, element);
            if (element != null) {
                element.setParent(Group.this);
            }
        }

        @Override
        public Node remove(int index) {
            Node old = super.remove(index);
            if (old != null) {
                old.setParent(null);
            }
            return old;
        }

        @Override
        public boolean remove(Object o) {
            boolean res = super.remove(o);
            if (o instanceof Node) {
                ((Node) o).setParent(null);
            }
            return res;
        }

        @Override
        public void clear() {
            this.stream()
                    .filter(Objects::nonNull)
                    .forEach(node -> node.setParent(null));
            super.clear();
        }

        @Override
        public boolean addAll(Collection<? extends Node> c) throws IllegalArgumentException {
            c.forEach(this::checkNode);
            boolean res = super.addAll(c);
            if (res) {
                c.stream()
                        .filter(this::contains)
                        .filter(Objects::nonNull)
                        .forEach(node -> node.setParent(null));
            }
            return res;
        }

        @Override
        public boolean addAll(int index, Collection<? extends Node> c) throws IllegalArgumentException {
            c.forEach(this::checkNode);
            boolean res = super.addAll(index, c);
            if (res) {
                c.stream()
                        .filter(this::contains)
                        .filter(Objects::nonNull)
                        .forEach(node -> node.setParent(null));
            }
            return res;
        }

        @Override
        protected void removeRange(int fromIndex, int toIndex) {
            List<Node> subList = this.subList(fromIndex, toIndex);
            super.removeRange(fromIndex, toIndex);
            subList.stream()
                    .filter(node -> !this.contains(node))
                    .filter(Objects::nonNull)
                    .forEach(node -> node.setParent(null));
        }

        @Override
        public boolean removeAll(Collection<?> c) {
            boolean res = super.removeAll(c);
            if (res) {
                c.stream()
                        .filter(node -> !this.contains(node))
                        .filter(o -> o instanceof Node)
                        .map(o -> (Node) o)
                        .forEach(node -> node.setParent(null));
            }
            return res;
        }

        @Override
        public boolean retainAll(Collection<?> c) {
            Stream<Node> removed = this.stream()
                    .filter(node -> !c.contains(node));
            return checkResultAndRemoveParent(removed, super.retainAll(c));
        }

        @Override
        public boolean removeIf(Predicate<? super Node> filter) {
            Stream<Node> removed = this.stream()
                    .filter(filter);
            return checkResultAndRemoveParent(removed, super.removeIf(filter));
        }

        @Override
        public void replaceAll(UnaryOperator<Node> operator) {
            super.replaceAll(operator);
            this.forEach(node -> node.setParent(Group.this));
        }

        private void checkNode(Node node) throws IllegalArgumentException {
            if (node != null && node.getParent() != null)
                IBEEditorMod.LOGGER.error("Argument node " + node + " already has a parent " + node.getParent());
            //throw new IllegalArgumentException("Argument node already has a parent");
        }

        private boolean checkResultAndRemoveParent(Stream<Node> removed, boolean res) {
            if (res) {
                removed
                        .filter(node -> !this.contains(node))
                        .filter(Objects::nonNull)
                        .forEach(node -> node.setParent(null));
            }
            return res;
        }
    }
}
