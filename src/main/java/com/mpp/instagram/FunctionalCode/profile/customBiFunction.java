package com.mpp.instagram.FunctionalCode.profile;

import java.util.Optional;

@FunctionalInterface
public interface customBiFunction<F, S, R>{
    public Optional<Integer> apply(F first, S second);
}
