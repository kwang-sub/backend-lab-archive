package org.example.ex04;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MessageRenderer implements IRenderer {
    private final List<IRenderer> subRenderers;

    public MessageRenderer() {
        List<IRenderer> tempRenderers = new ArrayList<>();
        tempRenderers.add(new HeaderRenderer());
        tempRenderers.add(new BodyRenderer());
        tempRenderers.add(new FooterRenderer());
        this.subRenderers = Collections.unmodifiableList(tempRenderers); // 읽기 전용 리스트로 설정
    }

    public List<IRenderer> getSubRenderers() {
        return subRenderers;
    }

    @Override
    public String render(Message message) {
        StringBuilder result = new StringBuilder();
        for (IRenderer renderer : subRenderers) {
            result.append(renderer.render(message));
        }
        return result.toString();
    }
}
