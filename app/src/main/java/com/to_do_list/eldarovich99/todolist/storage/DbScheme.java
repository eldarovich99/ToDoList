package com.to_do_list.eldarovich99.todolist.storage;

class DbScheme {
    static final class ToDoListTable{
        static final String NAME = "to_do_list";
        static final class Columns{
            static final String UUID = "uuid";
            static final String TITLE = "title";
            static final String TEXT = "text";
            static final String DATE = "date";
            static final String URI = "uri";
            static final String SOLVED = "solved";
        }
    }
}
