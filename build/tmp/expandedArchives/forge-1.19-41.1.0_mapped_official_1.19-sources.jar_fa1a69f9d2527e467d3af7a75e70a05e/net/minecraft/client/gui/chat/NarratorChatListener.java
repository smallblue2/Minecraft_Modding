package net.minecraft.client.gui.chat;

import com.mojang.logging.LogUtils;
import com.mojang.text2speech.Narrator;
import javax.annotation.Nullable;
import net.minecraft.SharedConstants;
import net.minecraft.client.Minecraft;
import net.minecraft.client.NarratorStatus;
import net.minecraft.client.gui.components.toasts.SystemToast;
import net.minecraft.client.gui.components.toasts.ToastComponent;
import net.minecraft.network.chat.ChatSender;
import net.minecraft.network.chat.ChatType;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.slf4j.Logger;

@OnlyIn(Dist.CLIENT)
public class NarratorChatListener implements ChatListener {
   public static final Component NO_TITLE = CommonComponents.EMPTY;
   private static final Logger LOGGER = LogUtils.getLogger();
   public static final NarratorChatListener INSTANCE = new NarratorChatListener();
   private final Narrator narrator = Narrator.getNarrator();

   public void handle(ChatType p_232450_, Component p_232451_, @Nullable ChatSender p_232452_) {
      NarratorStatus narratorstatus = getStatus();
      if (narratorstatus != NarratorStatus.OFF) {
         if (!this.narrator.active()) {
            this.logNarratedMessage(p_232451_.getString());
         } else {
            p_232450_.narration().ifPresent((p_232448_) -> {
               if (narratorstatus.shouldNarrate(p_232448_.priority())) {
                  Component component = p_232448_.decorate(p_232451_, p_232452_);
                  String s = component.getString();
                  this.logNarratedMessage(s);
                  this.narrator.say(s, p_232448_.priority().interrupts());
               }

            });
         }
      }
   }

   public void sayNow(Component p_168786_) {
      this.sayNow(p_168786_.getString());
   }

   public void sayNow(String p_93320_) {
      NarratorStatus narratorstatus = getStatus();
      if (narratorstatus != NarratorStatus.OFF && narratorstatus != NarratorStatus.CHAT && !p_93320_.isEmpty()) {
         this.logNarratedMessage(p_93320_);
         if (this.narrator.active()) {
            this.narrator.clear();
            this.narrator.say(p_93320_, true);
         }
      }

   }

   private static NarratorStatus getStatus() {
      return Minecraft.getInstance().options.narrator().get();
   }

   private void logNarratedMessage(String p_168788_) {
      if (SharedConstants.IS_RUNNING_IN_IDE) {
         LOGGER.debug("Narrating: {}", (Object)p_168788_.replaceAll("\n", "\\\\n"));
      }

   }

   public void updateNarratorStatus(NarratorStatus p_93318_) {
      this.clear();
      this.narrator.say(Component.translatable("options.narrator").append(" : ").append(p_93318_.getName()).getString(), true);
      ToastComponent toastcomponent = Minecraft.getInstance().getToasts();
      if (this.narrator.active()) {
         if (p_93318_ == NarratorStatus.OFF) {
            SystemToast.addOrUpdate(toastcomponent, SystemToast.SystemToastIds.NARRATOR_TOGGLE, Component.translatable("narrator.toast.disabled"), (Component)null);
         } else {
            SystemToast.addOrUpdate(toastcomponent, SystemToast.SystemToastIds.NARRATOR_TOGGLE, Component.translatable("narrator.toast.enabled"), p_93318_.getName());
         }
      } else {
         SystemToast.addOrUpdate(toastcomponent, SystemToast.SystemToastIds.NARRATOR_TOGGLE, Component.translatable("narrator.toast.disabled"), Component.translatable("options.narrator.notavailable"));
      }

   }

   public boolean isActive() {
      return this.narrator.active();
   }

   public void clear() {
      if (getStatus() != NarratorStatus.OFF && this.narrator.active()) {
         this.narrator.clear();
      }
   }

   public void destroy() {
      this.narrator.destroy();
   }
}