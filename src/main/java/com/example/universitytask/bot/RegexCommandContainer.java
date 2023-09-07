package com.example.universitytask.bot;

import java.util.HashMap;
import java.util.Map;

public enum RegexCommandContainer {
    START("/start"),
    HELP("/help"),
    SHOW_DEPARTMENTS("show all departments\\.?"),
    SHOW_EMPLOYEES("show all employees\\.?"),
    SHOW_STATISTICS("show (.+) statistics\\.?"),
    DEPARTMENT_HEAD("who is head of department (.+)\\.?"),
    AVERAGE_DEPARTMENT_SALARY("show the average salary for the department (.+)\\.?"),
    DEPARTMENT_EMPLOYEES_COUNT("show count of employee for (.+)\\.?"),
    GLOBAL_SEARCH_BY_TEMPLATE("global search by (.+)\\.?");
    private static final Map<String, RegexCommandContainer> VALUE_MAP = new HashMap<>();

    static {
        for (RegexCommandContainer e: values()) {
            VALUE_MAP.put(e.value, e);
        }
    }

    public final String value;

    private RegexCommandContainer(String value) {
        this.value = value;
    }

    public static RegexCommandContainer valueOfStringCommand(String command) {
        return VALUE_MAP.get(command);
    }
}
