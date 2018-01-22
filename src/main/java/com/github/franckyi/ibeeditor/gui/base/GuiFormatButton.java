package com.github.franckyi.ibeeditor.gui.base;

import com.github.franckyi.ibeeditor.IBEConfiguration;
import com.github.franckyi.ibeeditor.IBEEditor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;

public class GuiFormatButton extends GuiButton {

	private GuiTextField textField;

	public GuiFormatButton(int buttonId, int x, int y, GuiTextField textField) {
		super(buttonId, x, y, 20, 20, "");
		this.textField = textField;
	}

	@Override
	public boolean mousePressed(Minecraft mc, int mouseX, int mouseY) {
		if (mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width
				&& mouseY < this.y + this.height && this.enabled) {
			if (IBEConfiguration.formatCharacterAtTheEnd)
				textField.setText(textField.getText() + "§");
			else
				textField.setText(textField.getText().substring(0, textField.getCursorPosition()) + "§"
						+ textField.getText().substring(textField.getCursorPosition()));
			textField.setFocused(true);
		}
		return super.mousePressed(mc, mouseX, mouseY);
	}

	@Override
	public void drawButton(@Nonnull Minecraft mc, int mouseX, int mouseY) {
        if (this.visible) {
            mc.getTextureManager().bindTexture(BUTTON_TEXTURES);
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            this.hovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
            GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
            GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
            this.drawTexturedModalRect(this.x, this.y, 0, 46 + 20, this.width / 2, this.height);
            this.drawTexturedModalRect(this.x + this.width / 2, this.y, 200 - this.width / 2, 46 + 20, this.width / 2, this.height);
            this.mouseDragged(mc, mouseX, mouseY);
            mc.getTextureManager().bindTexture(new ResourceLocation(IBEEditor.MODID, "textures/gui/formatbuttonicon.png"));
            this.drawModalRectWithCustomSizedTexture(this.x + 2, this.y + 2, 0, 0, 16, 16, 16, 16, 2);
        }
	}

	private void drawModalRectWithCustomSizedTexture(int x, int y, float u, float v, int width, int height,
			float textureWidth, float textureHeight, double zLevel) {
		float f = 1.0F / textureWidth;
		float f1 = 1.0F / textureHeight;
		Tessellator tessellator = Tessellator.getInstance();
		VertexBuffer vertexbuffer = tessellator.getBuffer();
		vertexbuffer.begin(7, DefaultVertexFormats.POSITION_TEX);
		vertexbuffer.pos((double) x, (double) (y + height), zLevel)
				.tex((double) (u * f), (double) ((v + (float) height) * f1)).endVertex();
		vertexbuffer.pos((double) (x + width), (double) (y + height), zLevel)
				.tex((double) ((u + (float) width) * f), (double) ((v + (float) height) * f1)).endVertex();
		vertexbuffer.pos((double) (x + width), (double) y, zLevel)
				.tex((double) ((u + (float) width) * f), (double) (v * f1)).endVertex();
		vertexbuffer.pos((double) x, (double) y, zLevel).tex((double) (u * f), (double) (v * f1)).endVertex();
		tessellator.draw();
	}

}
