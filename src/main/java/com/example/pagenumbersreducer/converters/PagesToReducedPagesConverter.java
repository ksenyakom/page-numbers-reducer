package com.example.pagenumbersreducer.converters;

import com.example.pagenumbersreducer.controller.ReducedPagesView;
import com.example.pagenumbersreducer.model.Pages;
import org.springframework.core.convert.converter.Converter;

import java.util.stream.Collectors;

public class PagesToReducedPagesConverter implements Converter<Pages, ReducedPagesView> {
    @Override
    public ReducedPagesView convert(Pages source) {
        ReducedPagesView reducedPagesView = new ReducedPagesView();

        String original = source.getOriginal().stream()
                .map(Object::toString)
                .collect(Collectors.joining(","));

        reducedPagesView.setOriginal(original);

        String reduced = source.getReduced().stream()
                .map(list -> {
                    if (list.size() == 1) {
                        return list.get(0).toString();
                    }
                    if (list.size() == 2) {
                        return list.get(0) + "-" + list.get(1);
                    }
                    return "";
                })
                .collect(Collectors.joining(","));

        reducedPagesView.setReduced(reduced);
        reducedPagesView.setBookId(source.getBookId().toString());

        return reducedPagesView;
    }
}
