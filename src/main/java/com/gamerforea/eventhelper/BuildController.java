package com.gamerforea.eventhelper;

public class BuildController {
    public static final boolean isDummyBuild = BuildController.internalDummyBuildState();

    private static boolean internalDummyBuildState() {
        return true;
    }

    public static boolean isDummyBuild() {
        return isDummyBuild;
    }
}
