package com.mybareskinph.theBareskinApp.util;

import android.support.annotation.NonNull;

import java.util.List;

import rx.Observable;

/**
 * Created by paulolosbanos on 8/21/17.
 */

public class Observables {
    private static Boolean allTrue(@NonNull final Object[] args) {
        for (final Object o : args) {
            if (o instanceof Boolean) {
                final Boolean b = (Boolean) o;

                if (!b) {
                    return false;
                }
            } else {
                return false;
            }
        }
        return true;
    }

    public static Observable<Boolean> allLatestTrue(@NonNull final List<Observable<Boolean>> observables) {
        return Observable.combineLatest(observables, Observables::allTrue);
    }
}
