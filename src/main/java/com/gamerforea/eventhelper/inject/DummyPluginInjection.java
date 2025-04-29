package com.gamerforea.eventhelper.inject;

public class DummyPluginInjection implements PluginInjection {
    private static final DummyPluginInjection instance = new DummyPluginInjection();

    private DummyPluginInjection() {
        // NO-OP
    }

    public static DummyPluginInjection getInstance() {
        return instance;
    }

    @Override
    public boolean isInPrivate(Object world, int x, int y, int z) {
        return false;
    }

    @Override
    public boolean isPrivateMember(Object player, int x, int y, int z) {
        return true;
    }

    @Override
    public boolean isPrivateOwner(Object player, int x, int y, int z) {
        return true;
    }

}
