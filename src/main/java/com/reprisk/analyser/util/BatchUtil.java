package com.reprisk.analyser.util;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@UtilityClass
public class BatchUtil {

    /*
    This method basically partitions the list into given batch size so it can be processed parellelly.
     */
    public static <T> Stream<List<T>> stream(int batchSize, Iterator<T> iterator) {
        return StreamSupport.stream(
                Spliterators.spliteratorUnknownSize(
                        new NewsArticleIterator<>(batchSize, iterator), Spliterator.ORDERED), false);
    }

    @RequiredArgsConstructor(access = AccessLevel.PRIVATE)
    private class NewsArticleIterator<T> implements Iterator<List<T>> {

        private final int batchSize;
        private final Iterator<T> sourceIterator;
        private List<T> currentList;

        @Override
        public boolean hasNext() {
            return sourceIterator.hasNext();
        }

        @Override
        public List<T> next() {
            prepareCurrentList();
            return currentList;
        }

        private void prepareCurrentList() {
            currentList = new ArrayList<>(batchSize);
            while (sourceIterator.hasNext() && currentList.size() < batchSize) {
                currentList.add(sourceIterator.next());
            }
        }
    }
}
