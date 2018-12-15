package com.to_do_list.eldarovich99.todolist.storage;

public class DbScheme {
    public static final class ToDoListTable{
        public static final String NAME = "to_do_list";
        public static final class Columns{
            public static final String UUID = "uuid";
            public static final String TITLE = "title";
            public static final String TEXT = "text";
            public static final String DATE = "date";
            public static final String URI = "uri";
            public static final String SOLVED = "solved";
        }
    }
}
