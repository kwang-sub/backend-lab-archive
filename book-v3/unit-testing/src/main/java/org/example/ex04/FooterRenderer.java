package org.example.ex04;

class FooterRenderer implements IRenderer {
    @Override
    public String render(Message message) {
        return "<i>" + message.getFooter() + "</i>";
    }
}