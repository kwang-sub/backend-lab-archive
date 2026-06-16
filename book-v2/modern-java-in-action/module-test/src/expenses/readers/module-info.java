module expenses.reader {
    requires java.base;
    requires kotlin.stdlib;

    exports com.example.expenses.readers;
    exports com.example.expenses.readers.file;
    exports com.example.expenses.readers.http;
}