package it.unibo.inner.impl;

import java.util.Arrays;
import java.util.Iterator;
import it.unibo.inner.api.*;

public class IterableWithPolicyImpl<T> implements IterableWithPolicy<T> {

    private final T[] arr;
    private Predicate<T> predicateBehavior;

    public IterableWithPolicyImpl(final T[] elem, final Predicate<T> predicate) {
        this.arr = elem;
        this.predicateBehavior = predicate;
    }

    public IterableWithPolicyImpl(final T[] elem) {
        this(elem, null);
    }

    public void setIterationPolicy(Predicate<T> filter) {
    }

    private class IteratorImpl<T> implements Iterator<T> {

        private final T[] array;
        private int current;

        public IteratorImpl(final T[] array) {
            if (array == null) {
                throw new java.lang.IllegalArgumentException();
            }
            this.array = array;
            this.current = 0;
        }

        public boolean hasNext() {
            return this.current < (this.array.length);
        }

        public T next() {
            if (this.hasNext()) {
                return this.array[this.current++];
            } else {
                throw new java.util.NoSuchElementException();
            }
        }
    }

    public Iterator<T> iterator() {
        return new IteratorImpl<>(arr);
    }

    public String toString() {
        return Arrays.deepToString(arr);
    }
}
