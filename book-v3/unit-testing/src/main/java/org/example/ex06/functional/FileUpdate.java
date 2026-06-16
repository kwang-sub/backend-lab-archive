package org.example.ex06.functional;

import java.util.Objects;

class FileUpdate {
    public final String fileName;
    public final String newContent;

    public FileUpdate(String fileName, String newContent) {
        this.fileName = fileName;
        this.newContent = newContent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FileUpdate that = (FileUpdate) o;
        return Objects.equals(fileName, that.fileName) &&
                Objects.equals(newContent, that.newContent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fileName, newContent);
    }
}
