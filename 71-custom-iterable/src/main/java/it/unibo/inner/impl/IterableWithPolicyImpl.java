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
        this(elem, new Predicate<T>() {
            public boolean test(final T elem) {
                return true;
            }
        });
    }

    public void setIterationPolicy(final Predicate<T> filter) {
        this.predicateBehavior = filter;
    }

    private class IteratorImpl implements Iterator<T> {

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
            for (int i = 0; this.current + i < this.array.length; i++) {
                if (IterableWithPolicyImpl.this.predicateBehavior.test(this.array[current + i])) {
                    return true;
                }
            }
            return false;
        }

        public T next() {
            while (this.hasNext()) {
                T actualElem = this.array[this.current];
                if (IterableWithPolicyImpl.this.predicateBehavior.test(actualElem)) {
                    this.current++;
                    return actualElem;
                } else {
                    this.current++;
                }
            }
            throw new java.util.NoSuchElementException();
        }
    }

    public Iterator<T> iterator() {
        return new IteratorImpl(arr);
    }

    public String toString() {
        return Arrays.deepToString(arr);
    }
}
