package com.simongarton.factorio.model;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class Plan {

    final private ItemType itemType;
    final private Double neededItemsPerSecond;
    private ItemType makerType;
    private double makerCount;
    private Map<ItemType, Double> ingredients;
    private Map<ItemType, Plan> plans;
    private Map<String, Double> counts;
    private Map<String, Double> rawStreams;
    private Map<String, Double> combinedRawStreams;

    public Plan(final ItemType itemType, final double neededItemsPerSecond) {
        this.itemType = itemType;
        this.neededItemsPerSecond = neededItemsPerSecond;
    }

    public void displayToTerminal() {
        this.displayPlanToTerminal("", this);
    }

    private void displayPlanToTerminal(final String indent, final Plan plan) {
        if (plan.makerType == null) {
            System.out.println(indent + "consuming " + plan.itemType.name() + " at " + this.format(plan.neededItemsPerSecond) + "/s");
        } else {
            System.out.println(indent + "making " + plan.itemType.name() + " with " + this.format(plan.makerCount) + " x " + plan.makerType.name()  + " at " + this.format(plan.neededItemsPerSecond) + "/s");
        }
        if (plan.plans != null) {
            for (final Map.Entry<ItemType, Plan> entry : plan.plans.entrySet()) {
                this.displayPlanToTerminal(indent + "  ", entry.getValue());
            }
        }
    }

    private String format(final Double d) {
        return String.format("%,.2f", d);
    }

    public void summarizeToTerminal() {
        counts = new HashMap<>();
        combinedRawStreams = new HashMap<>();
        summarizePlanToTerminal(this);
        System.out.println("In total, you will need :");
        for (Map.Entry<String, Double> entry : counts.entrySet()) {
            System.out.println("  " + entry.getKey() + " : " + format(entry.getValue()));
        }
        System.out.println("Combined raw streams :");
        for (Map.Entry<String, Double> entry : combinedRawStreams.entrySet()) {
            if (isFluid(entry.getKey())) {
                System.out.println("  " + entry.getKey() + " : " + format(entry.getValue()) + "/s");
            } else {
                System.out.println("  " + entry.getKey() + " : " + format(entry.getValue()) + "/s = " + belts(entry.getValue()));
            }
        }
    }

    private boolean isFluid(String key) {
        return key.equalsIgnoreCase("water") ||
                key.equalsIgnoreCase("petroleum-gas");
    }

    private String belts(Double value) {
        if (value <= 7.5 ) {
            return "half a yellow belt";
        }
        if (value <= 15 ) {
            return "a yellow belt";
        }
        return format(value/15) + " yellow belts";
    }

    private String coloredBelts(Double value) {
        if (value <= 7.5 ) {
            return "half a yellow belt";
        }
        if (value <= 15 ) {
            return "a yellow belt, or half a red belt";
        }
        if (value <= 22.5 ) {
            return "a red belt, or half a blue belt";
        }
        if (value <= 30 ) {
            return "a red belt";
        }
        if (value <= 45 ) {
            return "a blue belt";
        }
        return "a blue belt, plus " + belts(value - 45);
    }

    private void summarizePlanToTerminal(Plan plan) {
        if (plan.makerType == null) {
            String key = plan.itemType.id();
            combinedRawStreams.put(key, combinedRawStreams.getOrDefault(key, 0D) + plan.neededItemsPerSecond);
            return;
        }
        String key = plan.makerType.id();
        counts.put(key, counts.getOrDefault(key, 0D) + plan.makerCount);
        key = key + "-rounded";
        counts.put(key, counts.getOrDefault(key, 0D) + Math.ceil(plan.makerCount));
        if (plan.plans != null) {
            for (final Map.Entry<ItemType, Plan> entry : plan.plans.entrySet()) {
                this.summarizePlanToTerminal(entry.getValue());
            }
        }
    }
}
