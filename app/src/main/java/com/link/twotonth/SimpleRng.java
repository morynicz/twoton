package com.link.twotonth;

/**
 * Created by link on 27.05.17.
 */

class SimpleRng implements RNG {
    @Override
    public double generate() {
        return Math.random();
    }
}
