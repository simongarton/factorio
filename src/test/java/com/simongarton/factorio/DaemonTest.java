package com.simongarton.factorio;

import com.simongarton.factorio.model.ItemType;
import com.simongarton.factorio.model.Plan;
import com.simongarton.factorio.model.RecipeBook;
import com.simongarton.factorio.model.makers.Assembler1;
import com.simongarton.factorio.model.makers.Assembler2;
import com.simongarton.factorio.model.makers.Assembler3;
import org.assertj.core.data.Offset;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DaemonTest {

    private Daemon daemon;

    @BeforeEach
    void setup() {
        RecipeBook recipeBook = new RecipeBook();
        this.daemon = new Daemon(recipeBook);
    }

    @Test
    void getPlan_COPPER_CABLE_1_IN_1() {
        // given
        this.daemon.addMaker(new Assembler1());

        // when
        Plan plan = this.daemon.getPlan(ItemType.COPPER_CABLE, 1);

        // then
        assertThat(plan.getMakerType()).isEqualTo(ItemType.ASSEMBLING_MACHINE_1);
        assertThat(plan.getMakerCount()).isEqualTo(0.5);
    }

    @Test
    void getPlan_COPPER_CABLE_10_IN_1() {
        // given
        this.daemon.addMaker(new Assembler1());

        // when
        Plan plan = this.daemon.getPlan(ItemType.COPPER_CABLE, 10);

        // then
        assertThat(plan.getMakerType()).isEqualTo(ItemType.ASSEMBLING_MACHINE_1);
        assertThat(plan.getMakerCount()).isEqualTo(5);
    }

    @Test
    void getPlan_COPPER_CABLE_1_IN_2() {
        // given
        this.daemon.addMaker(new Assembler2());

        // when
        Plan plan = this.daemon.getPlan(ItemType.COPPER_CABLE, 1);

        // then
        assertThat(plan.getMakerType()).isEqualTo(ItemType.ASSEMBLING_MACHINE_2);
            assertThat(plan.getMakerCount()).isEqualTo(0.333, Offset.offset(0.01));
    }

    @Test
    void getPlan_COPPER_CABLE_1_IN_3() {
        // given
        this.daemon.addMaker(new Assembler3());

        // when
        Plan plan = this.daemon.getPlan(ItemType.COPPER_CABLE, 1);

        // then
        assertThat(plan.getMakerType()).isEqualTo(ItemType.ASSEMBLING_MACHINE_3);
        assertThat(plan.getMakerCount()).isEqualTo(0.2, Offset.offset(0.01));
    }

    @Test
    void getPlan_ELECTRONIC_CIRCUIT_IN_1() {
        // given
        this.daemon.addMaker(new Assembler1());

        // when
        Plan plan = this.daemon.getPlan(ItemType.ELECTRONIC_CIRCUIT, 1);

        // then
        assertThat(plan.getMakerType()).isEqualTo(ItemType.ASSEMBLING_MACHINE_1);
        assertThat(plan.getMakerCount()).isEqualTo(1, Offset.offset(0.01));
    }

    @Test
    void getPlan_EXPLOSIVE_ROCKET_3_IN_2() {
        // given
        this.daemon.addMaker(new Assembler2());

        // when
        Plan plan = this.daemon.getPlan(ItemType.EXPLOSIVE_ROCKET, 3);

        // then
        assertThat(plan.getMakerType()).isEqualTo(ItemType.ASSEMBLING_MACHINE_2);
        assertThat(plan.getMakerCount()).isEqualTo(32, Offset.offset(0.01));
    }

    @Test
    void getPlan_ARTILLERY_TURRET_5_IN_2() {
        // given
        this.daemon.addMaker(new Assembler2());

        // when
        Plan plan = this.daemon.getPlan(ItemType.ARTILLERY_TURRET, 5);

        // then
        assertThat(plan.getMakerType()).isEqualTo(ItemType.ASSEMBLING_MACHINE_2);
        assertThat(plan.getMakerCount()).isEqualTo(266.667, Offset.offset(0.01));
    }

    @Test
    void getPlan_PRODUCTION_SCIENCE_PACK_10SPM_IN_2() {
        // given
        this.daemon.addMaker(new Assembler2());

        // when
        Plan plan = this.daemon.getPlan(ItemType.PRODUCTION_SCIENCE_PACK, 10.0/60.0);

        // then
        assertThat(plan.getMakerType()).isEqualTo(ItemType.ASSEMBLING_MACHINE_2);
        assertThat(plan.getMakerCount()).isEqualTo(1.555, Offset.offset(0.01));
    }

    @Test
    void addMaker() {

    }
}