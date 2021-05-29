package net.medievalweapons.mixin;

import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.At;

import net.medievalweapons.init.TagInit;
import net.medievalweapons.item.Big_Axe_Item;
import net.medievalweapons.item.Long_Sword_Item;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.item.Item;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {
  @Shadow
  @Nullable
  public ClientPlayerEntity player;

  @Inject(method = "doAttack", at = @At(value = "HEAD"), cancellable = true)
  public void doAttackMixin(CallbackInfo info) {
    Item item = player.getMainHandStack().getItem();
    if (player != null
        && (item.isIn(TagInit.DOUBLE_HANDED_ITEMS) || item.isIn(TagInit.ACCROSS_DOUBLE_HANDED_ITEMS)
            || item instanceof Long_Sword_Item || item instanceof Big_Axe_Item)
        && (!player.getOffHandStack().isEmpty() || player.isSwimming() || player.hasVehicle())) {
      info.cancel();
    }
  }

  @Inject(method = "doItemUse", at = @At(value = "HEAD"), cancellable = true)
  private void doItemUseMixin(CallbackInfo info) {
    Item item = player.getMainHandStack().getItem();
    if (player != null
        && (item.isIn(TagInit.DOUBLE_HANDED_ITEMS) || item.isIn(TagInit.ACCROSS_DOUBLE_HANDED_ITEMS)
            || item instanceof Long_Sword_Item || item instanceof Big_Axe_Item)
        && (!player.getOffHandStack().isEmpty() || player.isSwimming() || player.hasVehicle())) {
      info.cancel();
    }
  }

  @Inject(method = "handleBlockBreaking", at = @At(value = "HEAD"), cancellable = true)
  private void handleBlockBreakingMixin(boolean bl, CallbackInfo info) {
    Item item = player.getMainHandStack().getItem();
    if (player != null
        && (item.isIn(TagInit.DOUBLE_HANDED_ITEMS) || item.isIn(TagInit.ACCROSS_DOUBLE_HANDED_ITEMS)
            || item instanceof Long_Sword_Item || item instanceof Big_Axe_Item)
        && (!player.getOffHandStack().isEmpty() || player.isSwimming() || player.hasVehicle())) {
      info.cancel();
    }
  }

}
