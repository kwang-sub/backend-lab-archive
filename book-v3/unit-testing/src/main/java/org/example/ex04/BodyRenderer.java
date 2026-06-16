package org.example.ex04;

class BodyRenderer implements IRenderer {
    @Override
    public String render(Message message) {
        return "<b>" + message.getBody() + "</b>";
    }
}