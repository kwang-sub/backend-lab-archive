package org.example.ex04;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MessageRendererTest {

    @Test
    void messageRenderer_uses_correct_sub_renders() {
        // given
        MessageRenderer sut = new MessageRenderer();
        // when
        List<IRenderer> renderers = sut.getSubRenderers();
        // then
        assertThat(renderers).hasSize(3);
        assertThat(renderers.get(0)).isInstanceOf(HeaderRenderer.class);
        assertThat(renderers.get(1)).isInstanceOf(BodyRenderer.class);
        assertThat(renderers.get(2)).isInstanceOf(FooterRenderer.class);
    }

    @Test
    void rendering_a_message() {
        // given
        MessageRenderer sut = new MessageRenderer();
        Message message = new Message("h", "b", "f");
        // when
        String html = sut.render(message);
        // then
        assertThat(html).isEqualTo("<h1>h</h1><b>b</b><i>f</i>");
    }
}