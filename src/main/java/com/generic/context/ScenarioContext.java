package com.generic.context;

import io.cucumber.spring.ScenarioScope;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@ScenarioScope
public class ScenarioContext {

    private final Map<Context, Object> context = new ConcurrentHashMap<>();
    private final Map<String, Object> dynamicContext = new ConcurrentHashMap<>();

    public <V> void set(Context key, V value) {
        context.put(key, value);
    }

    public <V> void set(String key, V value) {
        dynamicContext.put(key, value);
    }

    @SuppressWarnings("unchecked")
    public <V> V get(Context key) {
        return (V) context.get(key);
    }

    @SuppressWarnings("unchecked")
    public <V> V get(String key) {
        return (V) dynamicContext.get(key);
    }

    public void remove(Context key) {
        context.remove(key);
    }

    public void clear() {
        context.clear();
        dynamicContext.clear();
    }
}