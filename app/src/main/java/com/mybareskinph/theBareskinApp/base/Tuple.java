package com.mybareskinph.theBareskinApp.base;

/**
 * Created by paulolosbanos on 9/2/17.
 */

public interface Tuple {

    class Tuple2<T1, T2> {

        private final T1 t1;
        private final T2 t2;

        public Tuple2(T1 t1, T2 t2) {
            this.t1 = t1;
            this.t2 = t2;
        }

        public T1 _1() {
            return t1;
        }

        public T2 _2() {
            return t2;
        }
    }
}
