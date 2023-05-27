package com.example.pagenumbersreducer.service;

import com.example.pagenumbersreducer.exception.PageException;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class PageServiceImpl implements PageService {
    @Override
    public Set<List<Integer>> reducePageNumber(List<Integer> pages) {
        validatePages(pages);

        List<Integer> uniqueSortedPages = new ArrayList<>(new HashSet<>(pages));
        uniqueSortedPages.sort(Comparator.naturalOrder());

        return reducePagesFromSortedList(uniqueSortedPages);
    }

    private void validatePages(List<Integer> pages) {
        if (CollectionUtils.isEmpty(pages)) {
            throw new PageException(String.format("Pages must not be null or empty, found %s", pages));
        }

        if (CollectionUtils.containsInstance(pages, null)) {
            throw new PageException("Pages must not contain null page number");
        }

        Optional<Integer> notValidPageNumber = pages.stream().filter(page -> page <= 0).findAny();
        notValidPageNumber.ifPresent(pageNumber -> {
            throw new PageException(String.format("Pages must not contain negative or zero page numbers, found %s", pageNumber));
        });
    }

    private Set<List<Integer>> reducePagesFromSortedList(List<Integer> pages) {
        Set<List<Integer>> reducedPages = new LinkedHashSet<>();
        List<Integer> list = null;

        for (Integer page : pages) {
            if (CollectionUtils.isEmpty(list)) {
                list = new ArrayList<>(2);
                list.add(page);
                reducedPages.add(list);
            } else if (page - list.get(list.size() - 1) > 1) {
                List<Integer> newList = new ArrayList<>(2);
                newList.add(page);
                list = newList;
                reducedPages.add(list);
            } else if (page - list.get(list.size() - 1) == 1) {
                if (list.size() > 1) {
                    list.set(1, page);
                } else {
                    list.add(page);
                }
            }
        }
        return reducedPages;
    }
}
