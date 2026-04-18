package com.nameless.fatchicken;

import net.minecraft.client.model.ChickenModel;
import net.minecraft.client.model.geom.ModelPart;

public class FattestChickenModel extends ChickenModel<FattestChickenEntity> {
    private final ModelPart head, beak, redThing, body, rightWing, leftWing;

    public FattestChickenModel(ModelPart root) {
        super(root);
        this.head = root.getChild("head");
        this.beak = root.getChild("beak");
        this.redThing = root.getChild("red_thing");
        this.body = root.getChild("body");
        this.rightWing = root.getChild("right_wing");
        this.leftWing = root.getChild("left_wing");
    }

    @Override
    public void setupAnim(FattestChickenEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);


        float state2 = 10.5F;

        this.head.y = 15.0F - (state2 * 2.0F);
        this.head.z = -4.0F - state2;
        this.beak.y = 15.0F - (state2 * 2.0F);
        this.beak.z = -4.0F - state2;
        this.redThing.y = 15.0F - (state2 * 2.0F);
        this.redThing.z = -4.0F - state2;

        this.body.xScale = (6.0F + 2.0F * state2) / 6.0F;
        this.body.yScale = (8.0F + 2.0F * state2) / 8.0F;
        this.body.zScale = (6.0F + 2.0F * state2) / 6.0F;
        this.body.y = 16.0F - state2;

        this.rightWing.x = -4.0F - state2;
        this.rightWing.y = 13.0F - (state2 * 2.0F);
        this.leftWing.x = 4.0F + state2;
        this.leftWing.y = 13.0F - (state2 * 2.0F);
    }
}